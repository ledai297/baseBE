package vn.sapo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "external.google")
public class GoogleIdentityClientConfiguration {

    private List<GoogleIdentityClient> identities = new ArrayList<>();

    public List<GoogleIdentityClient> getIdentities() {
        return identities;
    }

    public void setIdentities(List<GoogleIdentityClient> identities) {
        this.identities = identities;
    }

    public static class GoogleIdentityClient {
        private int id;
        private String clientType;
        private String clientId;
        private String clientSecret;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
}
