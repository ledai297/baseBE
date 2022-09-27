package vn.sapo.web.servletfilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
import vn.sapo.config.ActionLogConfiguration;
import vn.sapo.domain.ActionLog;
import vn.sapo.security.SecurityUtils;
import vn.sapo.service.ActionLogService;
import vn.sapo.utils.RequestUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
@Order(0)
public class LogActionFilter implements Filter {
    @Autowired
    private ObjectMapper jsonConverter;
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private ActionLogConfiguration actionLogConfiguration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (
            !actionLogConfiguration.getMethods().contains(httpServletRequest.getMethod())
                || actionLogConfiguration.getIgnoredPaths().contains(httpServletRequest.getRequestURI())
        ) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            HttpServletRequest requestToCache = new ContentCachingRequestWrapper(httpServletRequest);
            HttpServletResponse responseToCache = new ContentCachingResponseWrapper(httpServletResponse);

            ActionLog actionLog = initActionLog(requestToCache);

            chain.doFilter(requestToCache, responseToCache);

            recordResponse(actionLog, requestToCache, responseToCache);
            ignoreData(actionLog, requestToCache, responseToCache);
            boolean isIgnore = ignoreSave(requestToCache, responseToCache);
            if (!isIgnore) {
                actionLogService.save(actionLog);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private ActionLog initActionLog(HttpServletRequest request) throws IOException {
        Map<String, Object> headers = RequestUtils.getHeaders(request);
        String body = getRequestData(request);
        String userAgent = request.getHeader("User-Agent");

        ActionLog actionLog = new ActionLog();
        actionLog.setMethod(request.getMethod());
        actionLog.setPath(request.getRequestURI());
        actionLog.setQuery(request.getQueryString());
        actionLog.setHeaders(jsonConverter.writeValueAsString(headers));
        actionLog.setRequestBody(body);
        actionLog.setUserAgent(userAgent);
        actionLog.setArrivedAt(new Date());

        return actionLog;
    }

    private void recordResponse(ActionLog actionLog, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientId = SecurityUtils.getCurrentClientId();
        Long userId = SecurityUtils.getCurrentUserId().orElse(null);
        int statusCode = response.getStatus();
        Long elapsedTimeInMils = new Date().getTime() - actionLog.getArrivedAt().getTime();
        String matchedPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        String requestData = getRequestData(request);
        String responseData = getResponseData(response);

        actionLog.setClientId(clientId);
        actionLog.setUserId(userId);
        actionLog.setStatusCode(statusCode);
        actionLog.setElapsedTimeInMils(elapsedTimeInMils);
        actionLog.setRequestBody(requestData);
        actionLog.setResponseBody(responseData);
        actionLog.setMatchedUriPattern(matchedPattern);
    }

    private static String getResponseData(final HttpServletResponse response) throws IOException {
        String payload = null;
        ContentCachingResponseWrapper wrapper =
            WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, StandardCharsets.UTF_8);
                wrapper.copyBodyToResponse();
            }
        }
        return payload;
    }

    private static String getRequestData(final HttpServletRequest request) throws UnsupportedEncodingException {
        String payload = null;

        if (RequestUtils.getHeader(request, HttpHeaders.CONTENT_TYPE) == null)
            return null;

        MediaType mediaType = MediaType.valueOf(RequestUtils.getHeader(request, HttpHeaders.CONTENT_TYPE));
        if (!(
            mediaType.getType().equals(MediaType.APPLICATION_JSON.getType())
            && mediaType.getSubtype().equals(MediaType.APPLICATION_JSON.getSubtype())
        ))
            return null;

        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
            }
        }
        return payload;
    }

    private void ignoreData(ActionLog actionLog, final HttpServletRequest request, final HttpServletResponse response) {
        if (HttpMethod.GET.name().equals(request.getMethod())) {
            actionLog.setResponseBody(null);
        }
    }

    private boolean ignoreSave(final HttpServletRequest request, final HttpServletResponse response) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if ("/api/authorize".equals(path) && HttpStatus.OK.value() == response.getStatus()) {
            return true;
        }
        return false;
    }
}
