package vn.sapo.config.model;

import vn.sapo.statics.ProjectClientType;

public class ProjectClient {
    private String id;
    private String name;
    private String alias;
    private String apiKey;
    private String apiSecret;
    private ProjectClientType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public ProjectClientType getType() {
        return type;
    }

    public void setType(ProjectClientType type) {
        this.type = type;
    }
}
