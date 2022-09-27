package vn.sapo.api.sapocore.model.product;

public class SpWrapperUpdateVariantResponse {
    private SpUpdateVariantResponse variant;

    public SpWrapperUpdateVariantResponse(){

    }
    public SpWrapperUpdateVariantResponse(SpUpdateVariantResponse variant){
        this.variant = variant;
    }

    public SpUpdateVariantResponse getVariant() {
        return variant;
    }

    public void setVariant(SpUpdateVariantResponse variant) {
        this.variant = variant;
    }
}
