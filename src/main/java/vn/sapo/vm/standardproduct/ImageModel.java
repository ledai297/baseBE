package vn.sapo.vm.standardproduct;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String src;
    private Integer width;
    private Integer height;
    private boolean avatar;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean getAvatar() {
        return avatar;
    }

    public void setAvatar(boolean avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
            ", src='" + src + '\'' +
            ", width=" + width +
            ", height=" + height +
            ", avatar=" + avatar +
            '}';
    }
}
