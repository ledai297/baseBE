package vn.sapo.vm.warehouse;


import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.List;

public class WarehouseFilterModel extends BasePaginationFilterModel {
    private Long deliveryAddressDistrictId;
    private List<Long> ids;
    private String keyword;
    private List<String> statuses;

    public Long getDeliveryAddressDistrictId() {
        return deliveryAddressDistrictId;
    }

    public void setDeliveryAddressDistrictId(Long deliveryAddressDistrictId) {
        this.deliveryAddressDistrictId = deliveryAddressDistrictId;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<String> statuses) {
        this.statuses = statuses;
    }
}
