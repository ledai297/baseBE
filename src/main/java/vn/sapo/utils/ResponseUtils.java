package vn.sapo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.MediaType;
import vn.sapo.web.rest.errors.ErrorModel;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    public static void writeJsonResponse(ObjectMapper jsonConverter, ServletResponse servletResponse, Object model, int status) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setStatus(status);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");

        jsonConverter.writeValue(httpServletResponse.getWriter(), model);
    }
}
