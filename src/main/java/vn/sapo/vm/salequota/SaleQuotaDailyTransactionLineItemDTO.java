package vn.sapo.vm.salequota;

import vn.sapo.statics.variantquota.VariantQuotaCalculateType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class SaleQuotaDailyTransactionLineItemDTO {
    @NotNull
    private Long variantId;
    @NotNull
    private Long quantity;
    @NotNull
    @Enumerated(EnumType.STRING)
    private VariantQuotaCalculateType calculateType;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public VariantQuotaCalculateType getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(VariantQuotaCalculateType calculateType) {
        this.calculateType = calculateType;
    }
}
