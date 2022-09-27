package vn.sapo.api.sapocore.model.webhook;

import vn.sapo.statics.SapoWebhookTopic;

public class SpCreateWebhookRequest {
    private String address;
    private String format;
    private String topic;

    public SpCreateWebhookRequest(){

    }
    public SpCreateWebhookRequest(String address, SapoWebhookTopic topic){
        this.address = address;
        this.format = "json";
        this.topic = topic.name;
    }
    public String getAddress() {
        return address;
    }

    public String getFormat() {
        return format;
    }

    public String getTopic() {
        return topic;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
