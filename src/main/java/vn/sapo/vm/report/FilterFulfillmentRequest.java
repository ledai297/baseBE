package vn.sapo.vm.report;

import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.time.Instant;
import java.util.List;

public class FilterFulfillmentRequest extends BasePaginationFilterModel {
    private Instant statementDateFrom;
    private Instant statementDateTo;
    private List<Long> saleIds;
    private String keyword;

    public Instant getStatementDateFrom() {
        return statementDateFrom;
    }

    public void setStatementDateFrom(Instant statementDateFrom) {
        this.statementDateFrom = statementDateFrom;
    }

    public Instant getStatementDateTo() {
        return statementDateTo;
    }

    public void setStatementDateTo(Instant statementDateTo) {
        this.statementDateTo = statementDateTo;
    }

    public List<Long> getSaleIds() {
        return saleIds;
    }

    public void setSaleIds(List<Long> saleIds) {
        this.saleIds = saleIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
