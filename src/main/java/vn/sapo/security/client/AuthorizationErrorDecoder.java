package vn.sapo.security.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.security.client.exception.RemoteAuthorizationException;

public class AuthorizationErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401) {
            return new RemoteAuthorizationException();
        }
        else if (response.status() >= 300)
            return new RemoteAuthorizationException(response, jsonConverter);
        return defaultErrorDecoder.decode(methodKey, response);
    }

}
