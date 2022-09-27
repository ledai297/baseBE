package vn.sapo.vm.purchaseorder;

import vn.sapo.statics.purchaseorder.PurchaseOrderCancellationReason;
import vn.sapo.statics.purchaseorder.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PurchaseOrderResponse {
    private Long id;
    private String code;
    private Long vendorId;
    private Long warehouseId;
    private Date purchaseDate;
    private Date expectedDeliveryDate;
    private Date confirmedDate;
    private BigDecimal totalAmount;
    private PurchaseOrderStatus status;
    private Date cancelledDate;
    private Date rejectedDate;
    private Date completedDate;
    private List<PurchaseOrderLineItemResponse> lineItems;
    private AddressResponse vendorAddress;
    private BigDecimal totalTaxAmount;
    private Long createdBy;
    private Instant createdDate;
    private Instant modifiedDate;
    private PurchaseOrderCancellationReason cancellationReason;
    private String cancellationReasonDetail;
    private String note;
    private Boolean deleted;
    private Date deletedDate;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrderStatus status) {
        this.status = status;
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

    public List<PurchaseOrderLineItemResponse> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<PurchaseOrderLineItemResponse> lineItems) {
        this.lineItems = lineItems;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public AddressResponse getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(AddressResponse vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public PurchaseOrderCancellationReason getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(PurchaseOrderCancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationReasonDetail() {
        return cancellationReasonDetail;
    }

    public void setCancellationReasonDetail(String cancellationReasonDetail) {
        this.cancellationReasonDetail = cancellationReasonDetail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
