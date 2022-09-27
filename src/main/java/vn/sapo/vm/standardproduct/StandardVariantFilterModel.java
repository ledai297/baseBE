package vn.sapo.vm.standardproduct;

import vn.sapo.vm.pagination.BasePaginationFilterModel;

import javax.validation.constraints.Size;
import java.util.List;

public class StandardVariantFilterModel extends BasePaginationFilterModel {
    private List<Long> ids;
    private List<Long> productIds;
    private List<String> barcodes;
    @Size(min = 3, message = "Từ khóa tìm kiếm phải có ít nhất 3 ký tự")
    private String keyword;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public List<String> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<String> barcodes) {
        this.barcodes = barcodes;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
