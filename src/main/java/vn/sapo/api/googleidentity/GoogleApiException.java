package vn.sapo.api.googleidentity;

import org.springframework.http.HttpStatus;
import vn.sapo.exception.BaseCustomException;

public class GoogleApiException extends BaseCustomException {
    public GoogleApiException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
