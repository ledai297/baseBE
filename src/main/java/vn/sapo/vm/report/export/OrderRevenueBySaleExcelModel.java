package vn.sapo.vm.report.export;

import java.math.BigDecimal;

public class OrderRevenueBySaleExcelModel {
    private Integer index;
    private String orderCode;
    private String fulfillmentCode;
    private String statementDate;
    private String codDate;
    private BigDecimal grossRevenue;
    private BigDecimal netRevenue;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getCodDate() {
        return codDate;
    }

    public void setCodDate(String codDate) {
        this.codDate = codDate;
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

    public String getFulfillmentCode() {
        return fulfillmentCode;
    }

    public void setFulfillmentCode(String fulfillmentCode) {
        this.fulfillmentCode = fulfillmentCode;
    }
}
