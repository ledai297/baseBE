package vn.sapo.vm.report.revenuebyorder.summary;

import java.util.List;

public class RevenueByOrderCrossCheckingFilterModel {
    private String crossCheckingDateFrom;
    private String crossCheckingDateTo;
    private List<Long> saleIds;

    public String getCrossCheckingDateFrom() {
        return crossCheckingDateFrom;
    }

    public void setCrossCheckingDateFrom(String crossCheckingDateFrom) {
        this.crossCheckingDateFrom = crossCheckingDateFrom;
    }

    public String getCrossCheckingDateTo() {
        return crossCheckingDateTo;
    }

    public void setCrossCheckingDateTo(String crossCheckingDateTo) {
        this.crossCheckingDateTo = crossCheckingDateTo;
    }

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }
}
