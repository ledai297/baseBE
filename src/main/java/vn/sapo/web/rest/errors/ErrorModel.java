package vn.sapo.web.rest.errors;

import java.util.ArrayList;
import java.util.List;

public class ErrorModel {
    private int statusCode;
    private String errorCode;
    private List<String> messages;

    public ErrorModel(int statusCode, List<String> messages, String errorCode){
        this(statusCode, messages);
        this.errorCode = errorCode;
    }
    public ErrorModel(int statusCode, List<String> messages){
        this.statusCode = statusCode;
        this.messages = messages;
    }
    public ErrorModel(int statusCode, String message, String errorCode){
        this(statusCode, message);
        this.errorCode = errorCode;
    }
    public ErrorModel(int statusCode, String message){
        this.statusCode = statusCode;
        this.messages = new ArrayList<>();
        this.messages.add(message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }


}
