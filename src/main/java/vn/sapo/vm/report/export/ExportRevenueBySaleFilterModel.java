package vn.sapo.vm.report.export;

import java.util.List;

public class ExportRevenueBySaleFilterModel {
    private String revenueRecognitionDateFrom;
    private String revenueRecognitionDateTo;
    private List<Long> saleIds;

    public String getRevenueRecognitionDateFrom() {
        return revenueRecognitionDateFrom;
    }

    public void setRevenueRecognitionDateFrom(String revenueRecognitionDateFrom) {
        this.revenueRecognitionDateFrom = revenueRecognitionDateFrom;
    }

    public String getRevenueRecognitionDateTo() {
        return revenueRecognitionDateTo;
    }

    public void setRevenueRecognitionDateTo(String revenueRecognitionDateTo) {
        this.revenueRecognitionDateTo = revenueRecognitionDateTo;
    }

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }
}
