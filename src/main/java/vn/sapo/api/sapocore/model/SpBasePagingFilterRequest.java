package vn.sapo.api.sapocore.model;

public abstract class SpBasePagingFilterRequest {
    private int page;
    private int limit;

    public SpBasePagingFilterRequest(int page, int limit){
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
