package vn.sapo.vm.standardproduct;

import vn.sapo.vm.pagination.BasePaginationFilterResultModel;

import java.util.List;

public class StandardVariantFilterResultModel extends BasePaginationFilterResultModel {
    private List<VariantModel> variants;
    private List<ProductReference.Attribute> attributes;

    public List<VariantModel> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantModel> variants) {
        this.variants = variants;
    }

    public List<ProductReference.Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductReference.Attribute> attributes) {
        this.attributes = attributes;
    }
}
