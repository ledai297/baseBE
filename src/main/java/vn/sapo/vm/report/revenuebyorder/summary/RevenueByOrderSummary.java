package vn.sapo.vm.report.revenuebyorder.summary;

import java.math.BigDecimal;

public class RevenueByOrderSummary {
    private BigDecimal grossRevenue;
    private BigDecimal netRevenue;
    private int quantity;

    public BigDecimal getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(BigDecimal grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public BigDecimal getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(BigDecimal netRevenue) {
        this.netRevenue = netRevenue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
