package vn.sapo.vm.report.revenuebymerchant;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RevenueByMerchantFilterModel {
    @NotNull
    List<Long> merchantIds;
    private String dateMin;
    private String dateMax;
    @NotNull
    private Long saleId;

    public List<Long> getMerchantIds() {
        return merchantIds;
    }

    public void setMerchantIds(List<Long> merchantIds) {
        this.merchantIds = merchantIds;
    }

    public String getDateMin() {
        return dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
