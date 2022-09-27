package vn.sapo.vm.report.revenuebysale;

import javax.validation.constraints.NotNull;
import java.util.List;

public class RevenueBySaleFilterModel {
    @NotNull
    List<Long> saleIds;
    private String dateMin;
    private String dateMax;

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }

    public String getDateMin() {
        return dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }
}
