package vn.sapo.vm.report.revenuebyorder;

import java.util.List;

public class RevenueByOrderFilterModel {
    private List<Long> orderIds;

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
