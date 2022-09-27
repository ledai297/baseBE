package vn.sapo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "application.business.variant-thumbnail-creation")
public class ThumbnailConfiguration {
    private Boolean forceSize;
    private List<ImageSize> sizes;

    public Boolean getForceSize() {
        return forceSize;
    }

    public void setForceSize(Boolean forceSize) {
        this.forceSize = forceSize;
    }

    public List<ImageSize> getSizes() {
        return sizes;
    }

    public void setSizes(List<ImageSize> sizes) {
        this.sizes = sizes;
    }

    public static class ImageSize{
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
