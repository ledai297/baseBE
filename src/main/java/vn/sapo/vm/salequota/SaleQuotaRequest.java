package vn.sapo.vm.salequota;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleQuotaRequest {
    @NotNull
    private Long variantId;
    @Min(value = 0, message = "Khả năng cung cấp phải lớn hơn hoặc bằng 0")
    private Long dailyQuota;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Long getDailyQuota() {
        return dailyQuota;
    }

    public void setDailyQuota(Long dailyQuota) {
        this.dailyQuota = dailyQuota;
    }
}
