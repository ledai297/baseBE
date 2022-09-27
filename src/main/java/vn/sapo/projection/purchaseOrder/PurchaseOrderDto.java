package vn.sapo.projection.purchaseOrder;

import vn.sapo.statics.purchaseorder.PurchaseOrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrderDto {
    @Id
    private Long id;
    private String code;
    @Column(name = "vendor_id")
    private Long vendorId;
    @Column(name = "warehouse_id")
    private Long warehouseId;
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Column(name = "expected_delivery_date")
    private Date expectedDeliveryDate;
    @Column(name = "confirmed_date")
    private Date confirmedDate;
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "cancelled_date")
    private Date cancelledDate;
    @Column(name = "rejected_date")
    private Date rejectedDate;
    @Column(name = "complete_date")
    private Date completedDate;
    @Column(name = "total_tax_amount")
    private BigDecimal totalTaxAmount;
    @Column(name = "created_by")
    private Long createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_date")
    private Date modifiedDate;
    private Integer version;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="label", column=@Column(name="vendor_address_label")),
        @AttributeOverride(name="provinceId", column=@Column(name="vendor_address_province_id")),
        @AttributeOverride(name="districtId", column=@Column(name="vendor_address_district_id")),
        @AttributeOverride(name="wardId", column=@Column(name="vendor_address_ward_id")),
        @AttributeOverride(name="line1", column=@Column(name="vendor_address_line1")),
        @AttributeOverride(name="line2", column=@Column(name="vendor_address_line2")),
        @AttributeOverride(name="countryId", column=@Column(name="vendor_address_country_id")),
        @AttributeOverride(name="phoneNumber", column=@Column(name="vendor_address_phone_number")),
    })
    private VendorAddressDto vendorAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public VendorAddressDto getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(VendorAddressDto vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
}
