package vn.sapo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseCustomException{
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
