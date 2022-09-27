package vn.sapo.vm.salequota;

import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.Set;

public class SaleQuotaFilterRequest extends BasePaginationFilterModel {
    private Set<Long> variantIds;
    private String keyword;

    public Set<Long> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(Set<Long> variantIds) {
        this.variantIds = variantIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
