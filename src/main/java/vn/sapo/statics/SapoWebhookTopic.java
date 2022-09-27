package vn.sapo.statics;

public enum SapoWebhookTopic {
    APP_UNINSTALLED("app/uninstalled");

    public String name;

    private SapoWebhookTopic(String name){
        this.name = name;
    }
}
