package vn.sapo.vm.report.revenuebysale;

import vn.sapo.vm.report.revenuebyorder.summary.RevenueByOrderSummary;

public class RevenueBySaleItem {
    private Long saleId;
    private RevenueByOrderSummary revenue;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public RevenueByOrderSummary getRevenue() {
        return revenue;
    }

    public void setRevenue(RevenueByOrderSummary revenue) {
        this.revenue = revenue;
    }
}
