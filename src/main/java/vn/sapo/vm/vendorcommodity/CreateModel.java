package vn.sapo.vm.vendorcommodity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateModel {
    @NotNull
    private Long vendorId;
    @NotNull
    private Long variantId;
    @NotNull
    private String referenceCode;
    @NotNull
    private String referenceName;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal taxRate;
    @NotNull
    private Long dailyQuota;


    public Long getDailyQuota() {
        return dailyQuota;
    }

    public void setDailyQuota(Long dailyQuota) {
        this.dailyQuota = dailyQuota;
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

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
