package vn.sapo.security.client.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.SqlConfig;
import vn.sapo.exception.BaseCustomException;
import vn.sapo.security.client.model.ErrorModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RemoteAuthorizationException extends BaseCustomException {
    private HttpStatus httpStatus;
    private ErrorModel errorDetail;
    public RemoteAuthorizationException(){
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorModel.setMessages(Arrays.asList("Không có quyền truy cập hoặc thao tác"));
        this.errorDetail = errorModel;
    }

    public RemoteAuthorizationException(Response response, ObjectMapper objectMapper){
        this.httpStatus = HttpStatus.resolve(response.status());
        ErrorModel errorModel = null;
        try{
            String jsonBody = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            errorModel = objectMapper.readValue(jsonBody, ErrorModel.class);
        }
        catch(Exception e){
            errorModel = new ErrorModel();
            errorModel.setStatusCode(500);
            errorModel.setMessages(Arrays.asList("Lỗi không xác định"));
        }
        this.errorDetail = errorModel;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorModel getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorModel errorDetail) {
        this.errorDetail = errorDetail;
    }
}
