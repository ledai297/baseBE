package vn.sapo.api.sapocore.model.product;

public class SpWrapperUpdateVariantImageRequest {
    private SpUpdateVariantImageRequest variant;

    public SpWrapperUpdateVariantImageRequest(SpUpdateVariantImageRequest variant){
        this.variant = variant;
    }

    public SpUpdateVariantImageRequest getVariant() {
        return variant;
    }

    public void setVariant(SpUpdateVariantImageRequest variant) {
        this.variant = variant;
    }
}
