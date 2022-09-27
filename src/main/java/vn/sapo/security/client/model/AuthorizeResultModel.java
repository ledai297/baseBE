package vn.sapo.security.client.model;

import vn.sapo.security.AuthenticationType;
import vn.sapo.security.LoginProvider;

import java.util.List;


public class AuthorizeResultModel {
    private AuthenticationType authenticationType;
    private String clientId;
    private Long userId;
    private LoginProvider loginProvider;
    private String providerLoginId;
    private List<String> authorities;
    private Boolean allowed;

    public AuthorizeResultModel(){

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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public LoginProvider getLoginProvider() {
        return loginProvider;
    }

    public void setLoginProvider(LoginProvider loginProvider) {
        this.loginProvider = loginProvider;
    }

    public String getProviderLoginId() {
        return providerLoginId;
    }

    public void setProviderLoginId(String providerLoginId) {
        this.providerLoginId = providerLoginId;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }
}
