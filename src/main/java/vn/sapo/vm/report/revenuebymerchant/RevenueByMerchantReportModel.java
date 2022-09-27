package vn.sapo.vm.report.revenuebymerchant;

import java.util.List;

public class RevenueByMerchantReportModel {
    private List<RevenueByMerchantItem> merchants;

    public List<RevenueByMerchantItem> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<RevenueByMerchantItem> merchants) {
        this.merchants = merchants;
    }
}
