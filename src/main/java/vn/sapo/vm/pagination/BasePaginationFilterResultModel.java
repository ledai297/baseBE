package vn.sapo.vm.pagination;

public abstract class BasePaginationFilterResultModel {
    private Pagination pagination;

    public BasePaginationFilterResultModel(Pagination pagination) {
        this.pagination = pagination;
    }

    public BasePaginationFilterResultModel() {
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
