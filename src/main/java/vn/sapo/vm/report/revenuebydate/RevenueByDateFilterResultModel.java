package vn.sapo.vm.report.revenuebydate;

import java.util.List;

public class RevenueByDateFilterResultModel {
    private List<RevenueByDateModel> revenues;


    public RevenueByDateFilterResultModel() {
    }

    public RevenueByDateFilterResultModel(List<RevenueByDateModel> revenues) {
        this.revenues = revenues;
    }

    public List<RevenueByDateModel> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<RevenueByDateModel> revenues) {
        this.revenues = revenues;
    }
}
