package vn.sapo.web.servletfilter;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ResettableStreamHttpServletRequest extends HttpServletRequestWrapper{
    private byte[] rawData;
    private final HttpServletRequest request;
    private final ResettableServletInputStream servletStream;
    private String data;

    public ResettableStreamHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
        this.servletStream = new ResettableServletInputStream();

    }

    public void resetInputStream() {
        servletStream.stream = new ByteArrayInputStream(rawData);
    }

    public String getData() throws IOException {
        if (data != null) {
            return data;
        } else {
            data = IOUtils.toString(this.getReader());
            this.resetInputStream();
            return data;
        }
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (rawData == null) {
            rawData = IOUtils.toByteArray(this.request.getInputStream());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return servletStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (rawData == null) {
            rawData = IOUtils.toByteArray(this.request.getInputStream());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return new BufferedReader(new InputStreamReader(servletStream,
            StandardCharsets.UTF_8));
    }

    private static class ResettableServletInputStream extends ServletInputStream {

        private InputStream stream;

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        @Override
        public boolean isFinished() {
            throw new RuntimeException("Not yet implemented");
        }

        @Override
        public boolean isReady() {
            throw new RuntimeException("Not yet implemented");
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new RuntimeException("Not yet implemented");
        }
    }
}
