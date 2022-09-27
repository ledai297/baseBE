package vn.sapo.domain.purchaseorder;


import vn.sapo.statics.purchaseorder.PurchaseOrderLogAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "purchase_order_log")
public class PurchaseOrderLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long purchaseOrderId;
    private long vendorId;
    private long warehouseId;
    @Enumerated(EnumType.STRING)
    private PurchaseOrderLogAction action;
    private Long actorId;
    private Long clientId;
    private Instant createdAt;
    private String detail;
    private PurchaseOrderSchemaVersion schemaVersion;

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

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long userId) {
        this.vendorId = userId;
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public PurchaseOrderLogAction getAction() {
        return action;
    }

    public void setAction(PurchaseOrderLogAction action) {
        this.action = action;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public PurchaseOrderSchemaVersion getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(PurchaseOrderSchemaVersion schemaVersion) {
        this.schemaVersion = schemaVersion;
    }
}
