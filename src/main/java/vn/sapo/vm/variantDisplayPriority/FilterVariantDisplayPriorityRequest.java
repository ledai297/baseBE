package vn.sapo.vm.variantDisplayPriority;

import java.util.List;

public class FilterVariantDisplayPriorityRequest {
    private List<Long> variantIds;

    public List<Long> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(List<Long> variantIds) {
        this.variantIds = variantIds;
    }
}
