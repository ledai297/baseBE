package vn.sapo.api.sapocore.model.product;

public class SpWrapperCreateVariantResponse {
    private SpCreateVariantResponse variant;

    public SpWrapperCreateVariantResponse(){

    }

    public SpWrapperCreateVariantResponse(SpCreateVariantResponse variant){
        this.variant = variant;
    }

    public SpCreateVariantResponse getVariant() {
        return variant;
    }

    public void setVariant(SpCreateVariantResponse variant) {
        this.variant = variant;
    }
}
