package vn.sapo.web.rest.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import vn.sapo.exception.BaseCustomException;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    Logger logger = LoggerFactory.getLogger("GeneralException");

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        ErrorModel errorModel;
        HttpStatus httpStatus;

        logger.error("Exception", ex);

        if (ex instanceof BaseCustomException) {
            BaseCustomException customEx = (BaseCustomException) ex;
            httpStatus = customEx.getHttpStatus();
            errorModel = new ErrorModel(httpStatus.value(), customEx.getMessages(), customEx.getErrorCode());
        } else if (ex instanceof BindException) {
            BindException customEx = (BindException) ex;
            httpStatus = HttpStatus.BAD_REQUEST;
            List<String> messages = customEx.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
            errorModel = new ErrorModel(httpStatus.value(), messages, null);
        } else if (ex instanceof InsufficientAuthenticationException) {
            // Lỗi này bắn ra khi user chưa được xác thực hoặc phân quyền
            // truy cập vào tài nguyên yêu cầu xác thực hoặc phân quyền
            // Ví dụ:
            // 1. trong phần SecurityConfiguration
            httpStatus = HttpStatus.UNAUTHORIZED;
            InsufficientAuthenticationException typedException = (InsufficientAuthenticationException) ex;
            errorModel = new ErrorModel(httpStatus.value(), typedException.getMessage());
        } else if (ex instanceof BadCredentialsException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            BadCredentialsException typedException = (BadCredentialsException) ex;
            errorModel = new ErrorModel(httpStatus.value(), typedException.getMessage());
        } else if (ex instanceof EmailAlreadyUsedException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            EmailAlreadyUsedException emailAlreadyUsedException = (EmailAlreadyUsedException) ex;
            errorModel = new ErrorModel(httpStatus.value(), emailAlreadyUsedException.getTitle());
        } else if (ex instanceof InvalidPasswordException) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            InvalidPasswordException typedException = (InvalidPasswordException) ex;
            errorModel = new ErrorModel(httpStatus.value(), "Incorrect password");
        } else if (ex instanceof AccessDeniedException) {
            //Lỗi này bắn ra khi user không có quyền truy cập tài nguyên
            //Ví dụ như do annotation @PreAuthorize trên controller method
            httpStatus = HttpStatus.UNAUTHORIZED;
            errorModel = new ErrorModel(httpStatus.value(), "Không có quyền truy cập");
        } else if (ex instanceof MaxUploadSizeExceededException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorModel = new ErrorModel(
                httpStatus.value(),
                "Kích thước file tối đa là:" + ((MaxUploadSizeExceededException) ex).getMaxUploadSize()
            );
        } else if (ex instanceof HttpMessageNotReadableException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorModel = new ErrorModel(
                httpStatus.value(),
                ex.getMessage()
            );
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            errorModel = new ErrorModel(httpStatus.value(), "An error occured");
        }

        return new ResponseEntity<>(errorModel, httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getFieldErrors().stream()
            .map(f -> f.getField() + ": " + f.getDefaultMessage())
            .collect(Collectors.toList());

        ErrorModel errorModel = new ErrorModel(HttpStatus.BAD_REQUEST.value(), errorMessages);
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

}
