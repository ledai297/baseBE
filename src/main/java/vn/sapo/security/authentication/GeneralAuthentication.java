package vn.sapo.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.sapo.security.AuthenticationType;
import vn.sapo.security.AuthoritiesConstants;
import vn.sapo.security.LoginProvider;
import vn.sapo.security.client.model.AuthorizeResultModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralAuthentication implements Authentication {
    private AuthenticationType authenticationType;
    private Collection<GrantedAuthority> authorities;
    private String clientId;
    private Long userId;
    private LoginProvider loginProvider;
    private String providerLoginId;
    private boolean authenticated;

    public GeneralAuthentication(
        AuthorizeResultModel authorization
    ){
        this.authenticationType = authorization.getAuthenticationType();
        this.clientId = authorization.getClientId();
        this.userId = authorization.getUserId();
        this.loginProvider = authorization.getLoginProvider();
        this.providerLoginId = authorization.getProviderLoginId();
        this.authorities = authorization.getAuthorities().stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        authenticate();
    }

    private void authenticate(){
        if(authenticationType == null){
            this.authenticated = false;
            return;
        }
        if(authenticationType.equals(AuthenticationType.API_KEY)){
            if(this.clientId != null){
                this.authenticated = true;
                return;
            }
        }
        else if (authenticationType.equals(AuthenticationType.JWT)){
            if(this.userId != null){
                this.authenticated = true;
                return;
            }
        }
        this.authenticated = false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return "Chưa biết name là gì";
    }



    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
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
}
