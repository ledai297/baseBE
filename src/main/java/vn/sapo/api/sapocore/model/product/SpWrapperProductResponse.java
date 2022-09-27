package vn.sapo.api.sapocore.model.product;

public class SpWrapperProductResponse {
    private SpProductResponse product;

    public SpWrapperProductResponse(){

    }

    public SpWrapperProductResponse(SpProductResponse product){
        this.product = product;
    }

    public SpProductResponse getProduct() {
        return product;
    }

    public void setProduct(SpProductResponse product) {
        this.product = product;
    }
}
