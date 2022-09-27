package vn.sapo.vm.report.revenuebymerchant;

import vn.sapo.vm.report.revenuebyorder.summary.RevenueByOrderSummary;

public class RevenueByMerchantItem {
    private Long merchantId;
    private RevenueByOrderSummary revenue;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public RevenueByOrderSummary getRevenue() {
        return revenue;
    }

    public void setRevenue(RevenueByOrderSummary revenue) {
        this.revenue = revenue;
    }
}
