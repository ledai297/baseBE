package vn.sapo.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException  extends BaseCustomException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, String errorCode) {
        super(message);
        this.setErrorCode(errorCode);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
