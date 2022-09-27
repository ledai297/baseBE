package vn.sapo.vm.salequota;

public class SaleDailyQuotaResponse {
    private Long variantId;
    private Long dailyQuota;
    private Long used;

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

    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }
}
