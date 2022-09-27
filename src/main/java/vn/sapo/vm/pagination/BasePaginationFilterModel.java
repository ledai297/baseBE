package vn.sapo.vm.pagination;

import java.util.Collections;
import java.util.List;

public class BasePaginationFilterModel {
    private Integer page;
    private Integer size;
    private List<String> sort;

    private static final Integer PAGE_MIN = 1;
    private static final Integer SIZE_MIN = 1;
    private static final Integer SIZE_MAX = 250;
    private static final Integer SIZE_DEFAULT = 20;

    public Integer getPage() {
        if(page == null)
            return PAGE_MIN;
        return page;
    }

    public void setPage(Integer page) {
        if(page == null || page < PAGE_MIN){
            this.page = PAGE_MIN;
            return;
        }
        this.page = page;
    }

    public Integer getSize() {
        if(size == null)
            return SIZE_DEFAULT;
        return size;
    }

    public void setSize(Integer size) {
        if(size == null){
            this.size = SIZE_DEFAULT;
            return;
        }
        if(size >= SIZE_MIN && size <= SIZE_MAX){
            this.size = size;
            return;
        }
        if(size < SIZE_MIN){
            this.size = SIZE_DEFAULT;
            return;
        }
        if(size > SIZE_MAX){
            this.size = SIZE_MAX;
        }

    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        if (sort == null)
            return;
        if (sort.size() == 2) {
            if (sort.get(1).equalsIgnoreCase("asc") || sort.get(1).equalsIgnoreCase("desc")) {
                this.sort = Collections.singletonList(String.join(",", sort));
            } else {
                this.sort = sort;
            }
        } else {
            this.sort = sort;
        }
    }
}
