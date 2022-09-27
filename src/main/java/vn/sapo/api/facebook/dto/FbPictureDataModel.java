package vn.sapo.api.facebook.dto;

public class FbPictureDataModel {
    private Integer height;
    private Boolean isSilhouette;
    private String url;
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getSilhouette() {
        return isSilhouette;
    }

    public void setSilhouette(Boolean silhouette) {
        isSilhouette = silhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
