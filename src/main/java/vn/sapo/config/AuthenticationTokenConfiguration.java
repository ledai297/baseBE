package vn.sapo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.security.authentication")
public class AuthenticationTokenConfiguration {

    private String identityClientWeb = "";
    private String identityClientAndroid = "";
    private String identityClientIos = "";
    private TokenConfiguration jwt = new TokenConfiguration();
    private RefreshTokenConfiguration refreshToken = new RefreshTokenConfiguration();

    public String getIdentityClientWeb() {
        return identityClientWeb;
    }

    public void setIdentityClientWeb(String identityClientWeb) {
        this.identityClientWeb = identityClientWeb;
    }

    public String getIdentityClientAndroid() {
        return identityClientAndroid;
    }

    public void setIdentityClientAndroid(String identityClientAndroid) {
        this.identityClientAndroid = identityClientAndroid;
    }

    public String getIdentityClientIos() {
        return identityClientIos;
    }

    public void setIdentityClientIos(String identityClientIos) {
        this.identityClientIos = identityClientIos;
    }

    public TokenConfiguration getJwt() {
        return jwt;
    }

    public void setJwt(TokenConfiguration jwt) {
        this.jwt = jwt;
    }

    public RefreshTokenConfiguration getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshTokenConfiguration refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static class TokenConfiguration {
        private long mobileTokenValidityInSeconds;
        private long mobileTokenValidityInSecondsForRememberMe;

        public long getMobileTokenValidityInSeconds() {
            return mobileTokenValidityInSeconds;
        }

        public void setMobileTokenValidityInSeconds(long mobileTokenValidityInSeconds) {
            this.mobileTokenValidityInSeconds = mobileTokenValidityInSeconds;
        }

        public long getMobileTokenValidityInSecondsForRememberMe() {
            return mobileTokenValidityInSecondsForRememberMe;
        }

        public void setMobileTokenValidityInSecondsForRememberMe(long mobileTokenValidityInSecondsForRememberMe) {
            this.mobileTokenValidityInSecondsForRememberMe = mobileTokenValidityInSecondsForRememberMe;
        }
    }

    public static class RefreshTokenConfiguration {
        private String base64Secret;
        private long tokenValidityInSeconds;
        private long mobileTokenValidityInSeconds;

        public String getBase64Secret() {
            return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
            this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
            return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
            this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getMobileTokenValidityInSeconds() {
            return mobileTokenValidityInSeconds;
        }

        public void setMobileTokenValidityInSeconds(long mobileTokenValidityInSeconds) {
            this.mobileTokenValidityInSeconds = mobileTokenValidityInSeconds;
        }
    }
}
