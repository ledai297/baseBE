package vn.sapo.security.client.model;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import vn.sapo.statics.RequestHeaderName;
import vn.sapo.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorizeModel {
    private String jwt;
    private String apiKey;
    private Long forcedUserId;
    private String method;
    private String path;
    private String matchedPattern;

    public AuthorizeModel() {

    }

    public AuthorizeModel(HttpServletRequest request, RequestMappingHandlerMapping mappingHandlerMapping) {
        this.method = request.getMethod();

        try {
            mappingHandlerMapping.getHandler(request);
            this.matchedPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        } catch (Exception e) {
            e.printStackTrace();
            List<String> a = mappingHandlerMapping.getHandlerMethods().keySet().stream()
                .map(handlerMethod -> handlerMethod.getPatternsCondition().getMatchingCondition(request))
                .filter(Objects::nonNull)
                .flatMap(patternsRequestCondition -> patternsRequestCondition.getPatterns().stream())
                .collect(Collectors.toList());
            this.matchedPattern = a.stream()
                .findFirst()
                .orElse(null);
        }

        this.path = request.getServletPath();
        Map<String, Object> headers = RequestUtils.getHeaders(request);
        this.jwt = headers.keySet().stream()
            .filter(k -> k.equalsIgnoreCase(RequestHeaderName.AUTHORIZATION))
            .map(k -> (String) headers.get(k))
            .filter(v -> v.startsWith("Bearer "))
            .map(v -> v.substring(7))
            .findFirst()
            .orElse(null);
        this.apiKey = headers.keySet().stream()
            .filter(k -> k.equals(RequestHeaderName.API_KEY))
            .map(k -> (String) headers.get(k))
            .findFirst()
            .orElse(null);
        this.forcedUserId = headers.keySet().stream()
            .filter(k -> k.equals(RequestHeaderName.USER_ID))
            .map(k -> Long.valueOf((String) headers.get(k)))
            .findFirst()
            .orElse(null);

    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Long getForcedUserId() {
        return forcedUserId;
    }

    public void setForcedUserId(Long forcedUserId) {
        this.forcedUserId = forcedUserId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMatchedPattern() {
        return matchedPattern;
    }

    public void setMatchedPattern(String matchedPattern) {
        this.matchedPattern = matchedPattern;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
