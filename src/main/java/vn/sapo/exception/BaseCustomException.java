package vn.sapo.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public abstract class BaseCustomException extends RuntimeException {
    public abstract HttpStatus getHttpStatus();

    private List<String> messages;
    private String errorCode;

    public BaseCustomException(String... messages) {
        this.messages = Arrays.asList(messages);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
