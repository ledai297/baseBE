package vn.sapo.vm.purchaseorder;

import vn.sapo.statics.purchaseorder.PurchaseOrderStatus;
import vn.sapo.vm.pagination.BasePaginationFilterModel;

import java.util.List;

public class PurchaseOrderFilterModel extends BasePaginationFilterModel {
    private List<Long> ids;
    private List<Long> userIds;
    private List<Long> vendorIds;
    private List<Long> warehouseIds;
    private String keyword;
    private String createdDateMin;
    private String createdDateMax;
    private String purchaseDateMin;
    private String purchaseDateMax;
    private List<PurchaseOrderStatus> statuses;

    public PurchaseOrderFilterModel(){

    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getVendorIds() {
        return vendorIds;
    }

    public void setVendorIds(List<Long> vendorIds) {
        this.vendorIds = vendorIds;
    }

    public List<Long> getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(List<Long> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    public List<PurchaseOrderStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<PurchaseOrderStatus> statuses) {
        this.statuses = statuses;
    }

    public String getCreatedDateMin() {
        return createdDateMin;
    }

    public void setCreatedDateMin(String createdDateMin) {
        this.createdDateMin = createdDateMin;
    }

    public String getCreatedDateMax() {
        return createdDateMax;
    }

    public void setCreatedDateMax(String createdDateMax) {
        this.createdDateMax = createdDateMax;
    }

    public String getPurchaseDateMin() {
        return purchaseDateMin;
    }

    public void setPurchaseDateMin(String purchaseDateMin) {
        this.purchaseDateMin = purchaseDateMin;
    }

    public String getPurchaseDateMax() {
        return purchaseDateMax;
    }

    public void setPurchaseDateMax(String purchaseDateMax) {
        this.purchaseDateMax = purchaseDateMax;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
