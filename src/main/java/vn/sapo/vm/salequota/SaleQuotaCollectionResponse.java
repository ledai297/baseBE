package vn.sapo.vm.salequota;

import vn.sapo.vm.pagination.BasePaginationFilterResultModel;
import vn.sapo.vm.pagination.Pagination;

import java.util.List;

public class SaleQuotaCollectionResponse extends BasePaginationFilterResultModel {

    private final List<SaleQuotaResponse> quotas;

    public SaleQuotaCollectionResponse(Pagination pagination,
                                       List<SaleQuotaResponse> quotas) {
        super(pagination);
        this.quotas = quotas;
    }

    public List<SaleQuotaResponse> getQuotas() {
        return quotas;
    }
}
