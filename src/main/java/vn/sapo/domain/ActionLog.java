package vn.sapo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "action_log")
public class ActionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String path;
    private String query;
    private String matchedUriPattern;
    private String headers;
    private String requestBody;
    private String responseBody;
    private Integer statusCode;
    private String clientId;
    private Long userId;
    private String userAgent;
    private Date arrivedAt;
    private Long elapsedTimeInMils;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getArrivedAt() {
        return arrivedAt;
    }

    public void setArrivedAt(Date arrivedAt) {
        this.arrivedAt = arrivedAt;
    }

    public Long getElapsedTimeInMils() {
        return elapsedTimeInMils;
    }

    public void setElapsedTimeInMils(Long elapsedTimeInMils) {
        this.elapsedTimeInMils = elapsedTimeInMils;
    }

    public String getMatchedUriPattern() {
        return matchedUriPattern;
    }

    public void setMatchedUriPattern(String matchedUriPattern) {
        this.matchedUriPattern = matchedUriPattern;
    }
}
