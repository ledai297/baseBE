package vn.sapo.vm.report.revenuebydate;

import vn.sapo.statics.order.OrderStatus;

import java.util.Set;

public class RevenueByDateFilterModel {
    private Set<Long> saleIds;
    private String revenueDateMin;
    private String revenueDateMax;
    private OrderStatus status;

    public Set<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(Set<Long> saleIds) {
        this.saleIds = saleIds;
    }

    public String getRevenueDateMin() {
        return revenueDateMin;
    }

    public void setRevenueDateMin(String revenueDateMin) {
        this.revenueDateMin = revenueDateMin;
    }

    public String getRevenueDateMax() {
        return revenueDateMax;
    }

    public void setRevenueDateMax(String revenueDateMax) {
        this.revenueDateMax = revenueDateMax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
