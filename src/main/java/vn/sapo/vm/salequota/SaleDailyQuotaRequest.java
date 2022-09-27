package vn.sapo.vm.salequota;

import java.util.Set;

public class SaleDailyQuotaRequest {
    Set<Long> variantIds;

    public Set<Long> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(Set<Long> variantIds) {
        this.variantIds = variantIds;
    }
}
