package vn.sapo.domain.vendor;

import vn.sapo.statics.InventoryTransactionSourceType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendor_daily_quota_transaction")
public class VendorDailyQuotaTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dateKey;
    private Long vendorId;
    private Long variantId;
    @Enumerated(EnumType.STRING)
    private InventoryTransactionSourceType sourceType;
    private Long sourceId;
    private Long eventId;
    private Long increment;
    private Date createdAt;

    public VendorDailyQuotaTransaction(){

    }

    public VendorDailyQuotaTransaction(
        Integer dateKey,
        Long vendorId,
        Long variantId,
        InventoryTransactionSourceType sourceType,
        Long sourceId,
        Long eventId,
        Date createdAt
    ){
        this.dateKey = dateKey;
        this.vendorId = vendorId;
        this.variantId = variantId;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.eventId = eventId;
        this.createdAt = createdAt;
    }
    public void setInventory(Long increment){
        this.increment = increment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public InventoryTransactionSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(InventoryTransactionSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getIncrement() {
        return increment;
    }

    public void setIncrement(Long stock) {
        this.increment = stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDateKey() {
        return dateKey;
    }

    public void setDateKey(Integer dateKey) {
        this.dateKey = dateKey;
    }

}
