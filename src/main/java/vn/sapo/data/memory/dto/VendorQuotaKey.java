package vn.sapo.data.memory.dto;

import vn.sapo.data.memory.VendorLimitationImpl;

public class VendorQuotaKey {
    private Long vendorId;
    private Long variantId;

    public VendorQuotaKey(Long vendorId, Long variantId){
        this.vendorId = vendorId;
        this.variantId = variantId;
    }

    @Override
    public int hashCode(){
        int hash = 1;
        return hash * vendorId.hashCode() + hash * variantId.hashCode();
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof VendorQuotaKey))
            return false;

        VendorQuotaKey vendorQuotaKey = (VendorQuotaKey) obj;
        return this.vendorId.equals(vendorQuotaKey.vendorId)
            && this.variantId.equals(vendorQuotaKey.variantId);
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }
}
