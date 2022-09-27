package vn.sapo.domain.vendor;

import vn.sapo.domain.SimpleEntity;
import vn.sapo.security.SecurityUtils;
import vn.sapo.statics.vendor.VendorCommodityStatus;
import vn.sapo.utils.ObjectUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "vendor_commodity")
public class VendorCommodity extends SimpleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vendorId;
    private Long variantId;
    private String referenceCode;
    private String referenceName;
    private Date createdDate;
    private Date modifiedDate;
    private Long createdBy;
    @Enumerated(EnumType.STRING)
    private VendorCommodityStatus status;
    private BigDecimal price;
    private BigDecimal taxRate;
    private Long dailyQuota;
    @Transient
    private boolean created;
    @Transient
    private boolean modified;
    @Transient
    private boolean dailyQuotaChanged;

    public VendorCommodity(){

    }
    public VendorCommodity(
        Long vendorId,
        Long variantId,
        String referenceCode,
        BigDecimal price,
        String referenceName,
        BigDecimal taxRate,
        Long dailyQuota
    ){
        this.vendorId = vendorId;
        this.variantId = variantId;
        this.referenceCode = referenceCode;
        this.price = price;
        this.createdBy = SecurityUtils.getCurrentUserId().orElseThrow();
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.status = VendorCommodityStatus.ACTIVE;
        this.referenceName = referenceName;
        this.taxRate = taxRate;
        this.dailyQuota = dailyQuota;
        this.created = true;
    }
    public void changeReferenceCode(String referenceCode){
        if(ObjectUtils.equals(this.referenceCode, referenceCode))
            return;
        this.referenceCode = referenceCode;
        modify();
    }
    public void changeReferenceName(String referenceName){
        if(ObjectUtils.equals(this.referenceName, referenceName))
            return;
        this.referenceName = referenceName;
        modify();
    }
    public void changePrice(BigDecimal price){
        if(this.price.compareTo(price) == 0)
            return;
        this.price = price;
        modify();
    }
    public void changeTaxRate(BigDecimal taxRate){
        if(this.taxRate.compareTo(taxRate) == 0)
            return;
        this.taxRate = taxRate;
        modify();
    }
    public void changeDailyQuota(long dailyQuota){
        if(ObjectUtils.equals(this.dailyQuota, dailyQuota))
            return;
        this.dailyQuota = dailyQuota;
        dailyQuotaChanged = true;
        modify();
    }
    private void modify(){
        this.modified = true;
        this.modifiedDate = new Date();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String sku) {
        this.referenceCode = sku;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public VendorCommodityStatus getStatus() {
        return status;
    }

    public void setStatus(VendorCommodityStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public Long getDailyQuota() {
        return dailyQuota;
    }

    public void setDailyQuota(Long dailyQuota) {
        this.dailyQuota = dailyQuota;
    }

    public boolean isCreated() {
        return created;
    }

    public boolean isModified() {
        return modified;
    }

    public boolean isDailyQuotaChanged() {
        return dailyQuotaChanged;
    }
}
