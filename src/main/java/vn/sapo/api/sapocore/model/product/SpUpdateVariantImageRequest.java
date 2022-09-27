package vn.sapo.api.sapocore.model.product;

public class SpUpdateVariantImageRequest {
    private int imageId;

    public SpUpdateVariantImageRequest(int imageId){
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
