package vn.sapo.domain.vendor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendor_daily_quota")
public class VendorDailyQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dateKey;
    private Long vendorId;
    private Long variantId;
    private Long quota;
    private Long used;
    private Date createdAt;
    private Date modifiedAt;

    @Transient
    private boolean created;
    @Transient
    private boolean modified;

    public void resetChanges(){
        this.created = false;
        this.modified = false;
    }

    public VendorDailyQuota(){

    }
    public VendorDailyQuota(int dateKey, long vendorId, long variantId, long quota){
        this.dateKey = dateKey;
        this.vendorId = vendorId;
        this.variantId = variantId;
        this.quota = quota;
        this.used = 0L;
        this.createdAt = new Date();
        this.modifiedAt = new Date();
        this.created = true;
    }
    public synchronized void changeQuota(long newQuota){
        //Hàm này có thể có nhiều thread gọi cùng lúc
        if(this.quota == newQuota)
            return;

        this.quota = newQuota;
        this.modifiedAt = new Date();
        modified = true;
    }
    public void increaseUsed(long increment){
        //Hàm này không bị nhiều thread gọi cùng lúc
        this.used += increment;
        this.modifiedAt = new Date();
        modified = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDateKey() {
        return dateKey;
    }

    public void setDateKey(Integer dateKey) {
        this.dateKey = dateKey;
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

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public Long getUsed() {
        return used;
    }

    public void setUsed(Long used) {
        this.used = used;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isModified() {
        return modified;
    }
}
