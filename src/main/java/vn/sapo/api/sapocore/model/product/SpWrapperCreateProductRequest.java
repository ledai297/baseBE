package vn.sapo.api.sapocore.model.product;

public class SpWrapperCreateProductRequest {
    private SpCreateProductRequest product;

    public SpWrapperCreateProductRequest(SpCreateProductRequest product){
        this.product = product;
    }

    public SpCreateProductRequest getProduct() {
        return product;
    }

    public void setProduct(SpCreateProductRequest product) {
        this.product = product;
    }
}
