package vn.sapo.vm.user;

import vn.sapo.security.LoginProvider;
import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.List;

public class MerchantFilterModel extends BasePaginationFilterModel implements Cloneable{
    private List<String> authorities;
    private String createdDateMin;
    private String createdDateMax;
    private List<LoginProvider> providers;
    private String searchKeyword;
    private List<Long> saleIds;
    private List<Long> merchantIds;

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getCreatedDateMin() {
        return createdDateMin;
    }

    public void setCreatedDateMin(String createdDateMin) {
        this.createdDateMin = createdDateMin;
    }

    public String getCreatedDateMax() {
        return createdDateMax;
    }

    public void setCreatedDateMax(String createdDateMax) {
        this.createdDateMax = createdDateMax;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }

    public List<LoginProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<LoginProvider> providers) {
        this.providers = providers;
    }

    public List<Long> getMerchantIds() {
        return merchantIds;
    }

    public void setMerchantIds(List<Long> merchantIds) {
        this.merchantIds = merchantIds;
    }

    @Override
    public String toString() {
        return "MerchantFilterModel{" +
            "authorities=" + authorities +
            ", createdDateMin='" + createdDateMin + '\'' +
            ", createdDateMax='" + createdDateMax + '\'' +
            ", searchKeyword='" + searchKeyword + '\'' +
            ", saleIds=" + saleIds +
            '}';
    }
}
