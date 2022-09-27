package vn.sapo.api.sapocore.model.product;

public class SpWrapperCreateVariantRequest {
    private SpCreateVariantRequest variant;

    public SpWrapperCreateVariantRequest(SpCreateVariantRequest variant){
        this.variant = variant;
    }

    public SpCreateVariantRequest getVariant() {
        return variant;
    }

    public void setVariant(SpCreateVariantRequest variant) {
        this.variant = variant;
    }
}
