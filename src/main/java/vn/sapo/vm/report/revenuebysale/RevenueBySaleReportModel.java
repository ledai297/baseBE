package vn.sapo.vm.report.revenuebysale;

import java.util.List;

public class RevenueBySaleReportModel {
    private List<RevenueBySaleItem> sales;

    public List<RevenueBySaleItem> getSales() {
        return sales;
    }

    public void setSales(List<RevenueBySaleItem> sales) {
        this.sales = sales;
    }
}
