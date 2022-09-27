package vn.sapo.vm.vendorcommodity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateModel {
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

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getDailyQuota() {
        return dailyQuota;
    }

    public void setDailyQuota(Long dailyQuota) {
        this.dailyQuota = dailyQuota;
    }
}
