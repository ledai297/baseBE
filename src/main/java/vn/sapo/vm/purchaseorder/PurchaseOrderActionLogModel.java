package vn.sapo.vm.purchaseorder;

import vn.sapo.statics.purchaseorder.PurchaseOrderLogAction;

import java.time.Instant;

public class PurchaseOrderActionLogModel {
    private long id;
    private long purchaseOrderId;
    private PurchaseOrderLogAction action;
    private Long actorId;
    private Instant createdAt;
    private String detail;
    private long vendorId;
    private long warehouseId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public PurchaseOrderLogAction getAction() {
        return action;
    }

    public void setAction(PurchaseOrderLogAction action) {
        this.action = action;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
}
