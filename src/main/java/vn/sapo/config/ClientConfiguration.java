package vn.sapo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@ConfigurationProperties(prefix = "external")
public class ClientConfiguration {
    private List<ExternalClient> clients;

    public List<ExternalClient> getClients() {
        return clients;
    }

    public void setClients(List<ExternalClient> clients) {
        this.clients = clients;
    }

    public static class ExternalClient{
        private int id;
        private String name;
        private String alias;
        private String accessToken;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
