package vn.sapo.api.sapocore.model.product;

public class SpCreateImageRequest {
    private String src;

    public SpCreateImageRequest(){

    }
    public SpCreateImageRequest(String src){
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
