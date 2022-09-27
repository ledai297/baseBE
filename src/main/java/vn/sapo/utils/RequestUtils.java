package vn.sapo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.undertow.servlet.util.IteratorEnumeration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import vn.sapo.config.Constants;
import vn.sapo.web.servletfilter.ResettableStreamHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class RequestUtils {
    private RequestUtils() {
    }

    public static String getBody(HttpServletRequest request) throws IOException {
        ResettableStreamHttpServletRequest wrappedRequest = (ResettableStreamHttpServletRequest) request;
        wrappedRequest.resetInputStream();
        return wrappedRequest.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
    }

    public static Map<String, Object> getHeaders(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        if(headerNames == null)
            return new HashMap<>();

        Map<String, Object> result = new HashMap<>();
        Iterator<String> headerNameIterator = headerNames.asIterator();
        while (headerNameIterator.hasNext()){
            String key = headerNameIterator.next();
            result.put(key, request.getHeader(key));
        }
        return result;
    }
    public static String getHeader(HttpServletRequest request, String header) {
        return getHeaders(request).entrySet().stream()
            .filter(f -> f.getKey().toLowerCase().trim().equals(header.toLowerCase().trim()))
            .map(f -> (String) f.getValue())
            .findFirst()
            .orElse(null);
    }

    public static void writeJsonResponse(HttpServletResponse response, ObjectMapper writer, Object object, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        writer.writeValue(response.getWriter(), object);
    }
}
