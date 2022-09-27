package vn.sapo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import vn.sapo.config.model.ProjectClient;

import java.io.Serializable;
import java.util.List;

/**
 * Properties specific to Monolithic.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {
    private final MediaConfig media = new MediaConfig();
    private final UrlsConfig urls = new UrlsConfig();
    private List<ProjectClient> projects;

    public MediaConfig getMedia() {
        return media;
    }

    public UrlsConfig getUrls() {
        return urls;
    }

    public List<ProjectClient> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectClient> projects) {
        this.projects = projects;
    }

    public static class MediaConfig {
        private String prefix;
        private AwsConfig aws;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public AwsConfig getAws() {
            return aws;
        }

        public void setAws(AwsConfig aws) {
            this.aws = aws;
        }

        @Override
        public String toString() {
            return "MediaConfig{" +
                "prefix='" + prefix + '\'' +
                ", aws=" + aws +
                '}';
        }

        public static class AwsConfig implements Serializable {
            private String baseUrl;
            private int timeout;
            private String bucketName;
            private String accessKey;
            private String secretKey;

            public int getTimeout() {
                return timeout;
            }

            public void setTimeout(int timeout) {
                this.timeout = timeout;
            }

            public String getBucketName() {
                return bucketName;
            }

            public void setBucketName(String bucketName) {
                this.bucketName = bucketName;
            }

            public String getAccessKey() {
                return accessKey;
            }

            public void setAccessKey(String accessKey) {
                this.accessKey = accessKey;
            }

            public String getSecretKey() {
                return secretKey;
            }

            public void setSecretKey(String secretKey) {
                this.secretKey = secretKey;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            @Override
            public String toString() {
                return "AwsConfig{" +
                    "timeout=" + timeout +
                    ", bucketName='" + bucketName + '\'' +
                    ", accessKey='" + accessKey + '\'' +
                    ", secretKey='" + secretKey + '\'' +
                    '}';
            }
        }
    }
    public static class UrlsConfig {
        private String market;
        private String admin;
        private String web;

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }
    }
}
