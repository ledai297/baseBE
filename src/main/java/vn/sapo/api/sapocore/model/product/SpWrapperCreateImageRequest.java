package vn.sapo.api.sapocore.model.product;

public class SpWrapperCreateImageRequest {
    private SpCreateImageRequest image;
    public SpWrapperCreateImageRequest(){

    }
    public SpWrapperCreateImageRequest(SpCreateImageRequest image){
        this.image = image;
    }

    public SpCreateImageRequest getImage() {
        return image;
    }

    public void setImage(SpCreateImageRequest image) {
        this.image = image;
    }
}
