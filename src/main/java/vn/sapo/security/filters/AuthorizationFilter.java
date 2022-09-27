package vn.sapo.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import ma.glasnost.orika.MapperFacade;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import vn.sapo.security.authentication.GeneralAuthentication;
import vn.sapo.security.client.AuthorizationApiClient;
import vn.sapo.security.client.exception.RemoteAuthorizationException;
import vn.sapo.security.client.model.AuthorizeModel;
import vn.sapo.security.client.model.AuthorizeResultModel;
import vn.sapo.utils.RequestUtils;
import vn.sapo.web.rest.errors.ErrorModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
//    private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    private final AuthorizationApiClient authorizationApiClient;
    private final RequestMappingHandlerMapping handlerMapping;
    private final ObjectMapper jsonConverter;
    private final MapperFacade mapper;

    public AuthorizationFilter(AuthorizationApiClient authorizationApiClient,
                               RequestMappingHandlerMapping handlerMapping,
                               ObjectMapper objectMapper,
                               MapperFacade mapper) {
        this.authorizationApiClient = authorizationApiClient;
        this.handlerMapping = handlerMapping;
        this.jsonConverter = objectMapper;
        this.mapper = mapper;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        AuthorizeModel authorizeModel = new AuthorizeModel(httpServletRequest, handlerMapping);
        if (authorizeModel.getMatchedPattern() == null) {
            ErrorModel errorModel = new ErrorModel(
                HttpStatus.NOT_FOUND_404,
                "Endpoint không chính xác",
                null
            );
            RequestUtils.writeJsonResponse(httpServletResponse, jsonConverter, errorModel, HttpStatus.NOT_FOUND_404);
            return;
        }
//        StopWatch watch = new StopWatch("authorize");
//        watch.start();

        AuthorizeResultModel authorizeResult;
        try {
            authorizeResult = authorizationApiClient.authorizeRequest(authorizeModel);
        } catch (RemoteAuthorizationException e) {
            vn.sapo.security.client.model.ErrorModel securityErrorModel = e.getErrorDetail();
            ErrorModel errorModel = mapper.map(securityErrorModel, ErrorModel.class);
            RequestUtils.writeJsonResponse(httpServletResponse, jsonConverter, errorModel, errorModel.getStatusCode());
            return;
        } catch (FeignException e) {
            ErrorModel errorModel = new ErrorModel(
                e.status(),
                "Không có quyền truy cập hoặc thao tác",
                null
            );
            RequestUtils.writeJsonResponse(httpServletResponse, jsonConverter, errorModel, e.status());
            return;
        } catch (Exception e) {
            ErrorModel errorModel = new ErrorModel(
                HttpStatus.UNAUTHORIZED_401,
                "Không có quyền truy cập hoặc thao tác",
                null
            );
            RequestUtils.writeJsonResponse(httpServletResponse, jsonConverter, errorModel, HttpStatus.UNAUTHORIZED_401);
            return;
        }

//        watch.stop();
//        logger.info(watch.prettyPrint());

        if (!authorizeResult.getAllowed()) {
            ErrorModel errorModel = new ErrorModel(
                HttpStatus.UNAUTHORIZED_401,
                "Không có quyền truy cập hoặc thao tác",
                null
            );
            RequestUtils.writeJsonResponse(httpServletResponse, jsonConverter, errorModel, HttpStatus.UNAUTHORIZED_401);
            return;
        }

        GeneralAuthentication generalAuthentication = new GeneralAuthentication(authorizeResult);
        SecurityContextHolder.getContext().setAuthentication(generalAuthentication);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
