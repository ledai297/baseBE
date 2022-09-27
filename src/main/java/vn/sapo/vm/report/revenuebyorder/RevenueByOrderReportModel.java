package vn.sapo.vm.report.revenuebyorder;

import vn.sapo.statics.order.OrderStatus;

import java.math.BigDecimal;

public class RevenueByOrderReportModel {
    private Long id;
    private OrderStatus status;
    private BigDecimal grossRevenue;
    private BigDecimal netRevenue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

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
}
