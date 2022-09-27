package vn.sapo.vm.pagination;

import org.springframework.data.domain.Page;

public class Pagination {
    private Long total;
    private Integer page;
    private Integer size;

    public Pagination(Long total, Integer pageIndex, Integer pageSize) {
        this.total = total;
        this.page = pageIndex;
        this.size = pageSize;
    }

    public Pagination(Page page) {
        this.total = page.getTotalElements();
        this.page = page.getNumber();
        this.size = page.getSize();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
