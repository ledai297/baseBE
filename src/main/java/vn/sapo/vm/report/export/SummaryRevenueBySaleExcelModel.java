package vn.sapo.vm.report.export;

import java.math.BigDecimal;

public class SummaryRevenueBySaleExcelModel {
    private Integer index;
    private String saleName;
    private String email;
    private Integer signboardContractQuantity;
    private BigDecimal netRevenue;
    private BigDecimal grossRevenue;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSignboardContractQuantity() {
        return signboardContractQuantity;
    }

    public void setSignboardContractQuantity(int signboardContractQuantity) {
        this.signboardContractQuantity = signboardContractQuantity;
    }

    public BigDecimal getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(BigDecimal netRevenue) {
        this.netRevenue = netRevenue;
    }

    public BigDecimal getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(BigDecimal grossRevenue) {
        this.grossRevenue = grossRevenue;
    }
}
