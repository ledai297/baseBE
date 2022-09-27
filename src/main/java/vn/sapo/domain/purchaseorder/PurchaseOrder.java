package vn.sapo.domain.purchaseorder;

import vn.sapo.domain.abstractions.AggregateRoot;
import vn.sapo.domain.abstractions.BaseAggregateRoot;
import vn.sapo.domain.abstractions.event.payloads.FieldChangedPayload;
import vn.sapo.domain.purchaseorder.event.PurchaseOrderEvent;
import vn.sapo.domain.purchaseorder.event.PurchaseOrderEventName;
import vn.sapo.exception.DomainValidateException;
import vn.sapo.projection.product.VariantDto;
import vn.sapo.statics.purchaseorder.PurchaseOrderCancellationReason;
import vn.sapo.statics.purchaseorder.PurchaseOrderStatus;
import vn.sapo.utils.CollectionUtils;
import vn.sapo.utils.NumberUtils;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PurchaseOrder extends BaseAggregateRoot<Long> implements AggregateRoot<Long>, Serializable {
    @NotBlank
    @Size(max = 100)
    private String code;
    @NotNull
    private Long vendorId;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Date purchaseDate;
    @NotNull
    private Date expectedDeliveryDate;
    private Date confirmedDate;
    @Min(0)
    @NotNull
    private BigDecimal totalAmount;
    @NotNull
    private PurchaseOrderStatus status;
    private Date cancelledDate;
    private Date rejectedDate;
    private Date completedDate;
    @NotEmpty
    private List<PurchaseOrderLineItem> lineItems;
    @Min(0)
    @NotNull
    private BigDecimal totalTaxAmount;
    @NotNull
    private Address vendorAddress;
    private PurchaseOrderCancellationReason cancellationReason;
    private String cancellationReasonDetail;
    @Size(max = 999)
    private String note;
    private Boolean deleted;
    private Date deletedDate;

    public PurchaseOrder() {
    }

    public PurchaseOrder(
        long id,
        String code,
        long vendorId,
        long warehouseId
    ) {
        this.id = id;
        this.code = code;
        this.vendorId = vendorId;
        this.warehouseId = warehouseId;
        this.status = PurchaseOrderStatus.DRAFT;
        this.deleted = false;
        create();
        this.addEvent(new PurchaseOrderEvent(PurchaseOrderEventName.PURCHASE_ORDER_CREATED));
    }

    public void setVendorId(Long vendorId) {
        if (NumberUtils.compareLong(this.vendorId, vendorId) == 0) {
            return;
        }
        if (!isCreated()) {
            this.addEvent(
                new PurchaseOrderEvent(
                    PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                    new FieldChangedPayload(
                        "vendorId",
                        this.vendorId,
                        vendorId
                    )
                )
            );
            modify();
        }
        this.vendorId = vendorId;

    }

    public void setWarehouseId(Long warehouseId) {
        if (NumberUtils.compareLong(this.warehouseId, warehouseId) == 0)
            return;
        if (!isCreated()) {
            new PurchaseOrderEvent(
                PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                new FieldChangedPayload(
                    "warehouseId",
                    this.warehouseId,
                    warehouseId
                )
            );
            modify();
        }
        this.warehouseId = warehouseId;
    }

    public void addLineItem(
        Long id,
        Long variantId,
        Long quantity,
        BigDecimal taxRate,
        BigDecimal purchasePrice
    ) {
        if (!this.status.equals(PurchaseOrderStatus.DRAFT)) {
            throw new DomainValidateException(
                String.format("Đơn mua %s, không thể thay đổi thông tin hàng hóa", this.status.getDescription())
            );
        }
        if (CollectionUtils.isEmpty(this.lineItems)) {
            this.lineItems = new ArrayList<>();
        }

        long duplicatedItemCount = this.lineItems.stream()
            .filter(li -> li.getVariantId().equals(variantId))
            .count();
        if (duplicatedItemCount > 0) {
            throw new DomainValidateException("Hàng hóa trùng lặp trong đơn hàng");
        }

        PurchaseOrderLineItem lineItem = new PurchaseOrderLineItem(
            this,
            id,
            variantId,
            quantity,
            taxRate,
            purchasePrice
        );
        this.lineItems.add(lineItem);
        this.calculate();
        if (!isCreated()) {
            this.addEvent(new PurchaseOrderEvent(
                PurchaseOrderEventName.PURCHASE_ORDER_LINE_ITEM_ADDED,
                id
            ));
            modify();
        }
    }

    private void calculate() {
        BigDecimal totalAmountLineItem = lineItems.stream()
            .map(PurchaseOrderLineItem::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalTaxAmount = lineItems.stream()
            .map(PurchaseOrderLineItem::getTaxAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalAmount = totalAmountLineItem.add(totalTaxAmount);
    }

    public void setPurchaseDate(Date purchaseDate) {
        if (Objects.equals(this.purchaseDate, purchaseDate)) {
            return;
        }
        if (!isCreated()) {
            this.addEvent(
                new PurchaseOrderEvent(
                    PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                    new FieldChangedPayload(
                        "purchaseDate",
                        this.purchaseDate,
                        purchaseDate
                    )
                )
            );
            modify();
        }
        this.purchaseDate = purchaseDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        if (Objects.equals(this.expectedDeliveryDate, expectedDeliveryDate)) {
            return;
        }
        if (!isCreated()) {
            this.addEvent(
                new PurchaseOrderEvent(
                    PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                    new FieldChangedPayload(
                        "expectedDeliveryDate",
                        this.expectedDeliveryDate,
                        expectedDeliveryDate
                    )
                )
            );
            modify();
        }
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setVendorAddress(Address vendorAddress) {
        if (vendorAddress.equals(this.vendorAddress))
            return;
        if (!isCreated()) {
            this.addEvent(
                new PurchaseOrderEvent(
                    PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                    new FieldChangedPayload(
                        "vendorAddress",
                        this.vendorAddress,
                        vendorAddress
                    )
                )
            );
            modify();
        }
        this.vendorAddress = vendorAddress;
    }

    public void removeLineItem(Long id) {
        if (!this.status.equals(PurchaseOrderStatus.DRAFT)) {
            throw new DomainValidateException(
                String.format("Đơn mua %s, không thể thay đổi thông tin hàng hóa", this.status.getDescription())
            );
        }
        PurchaseOrderLineItem lineItem = this.lineItems.stream()
            .filter(li -> li.getId().equals(id))
            .findFirst()
            .orElseThrow();
        boolean removed = this.lineItems.removeIf(li -> li.getId().equals(id));
        if (!removed)
            return;
        this.calculate();
        if (!isCreated()) {
            this.addEvent(new PurchaseOrderEvent(
                PurchaseOrderEventName.PURCHASE_ORDER_LINE_ITEM_REMOVED,
                lineItem
            ));
            modify();
        }
    }

    public void updateLineItem(PurchaseOrderLineItem lineItem) {
        if (!this.status.equals(PurchaseOrderStatus.DRAFT)) {
            throw new DomainValidateException(
                String.format("Đơn mua %s, không thể thay đổi thông tin hàng hóa", this.status.getDescription())
            );
        }
        this.calculate();
        if (!isCreated()) {
            this.addEvent(new PurchaseOrderEvent(
                PurchaseOrderEventName.PURCHASE_ORDER_LINE_ITEM_UPDATED,
                lineItem.getId()
            ));
            modify();
        }
    }

    public void cancelByAdmin(PurchaseOrderCancellationReason reason, String detail) {
        if (this.status.equals(PurchaseOrderStatus.CANCELLED) || this.status.equals(PurchaseOrderStatus.COMPLETED)
            || this.status.equals(PurchaseOrderStatus.REJECTED)) {
            throw new DomainValidateException("Trạng thái đơn mua không phù hợp để hủy");
        }
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_CANCELLED,
            new FieldChangedPayload(
                "status", this.status, PurchaseOrderStatus.CANCELLED
            )
        ));
        this.status = PurchaseOrderStatus.CANCELLED;
        this.cancelledDate = new Date();
        updateCancelReason(reason, detail);
        // luồng liên quan đến vận đơn để sau
        modify();
    }

    public void confirmPurchaseOrder() {
        if (!this.status.equals(PurchaseOrderStatus.DRAFT))
            throw new DomainValidateException("Trạng thái đơn mua không phù hợp");
        if(this.status.equals(PurchaseOrderStatus.CONFIRMED)){
            return;
        }
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_CONFIRMED,
            new FieldChangedPayload(
                "status", this.status, PurchaseOrderStatus.CONFIRMED
            )
        ));
        this.status = PurchaseOrderStatus.CONFIRMED;
        this.confirmedDate = new Date();
        modify();
    }

    public void deletePurchaseOrder(){
        if(this.deleted.equals(PurchaseOrderStatus.DELETED)){
            return;
        }
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_DELETED,
            new FieldChangedPayload(
                "deleted", this.status, PurchaseOrderStatus.DELETED
            )
        ));
        this.deleted = true;
        this.deletedDate = new Date();
        modify();
    }


    public void rejectPurchaseOrder() {
        if (!this.status.equals(PurchaseOrderStatus.DRAFT))
            throw new DomainValidateException("Trạng thái đơn mua không phù hợp");
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_REJECTED,
            new FieldChangedPayload(
                "status", this.status, PurchaseOrderStatus.REJECTED
            )
        ));
        this.status = PurchaseOrderStatus.REJECTED;
        this.rejectedDate = new Date();
        modify();
    }

    public void deliverPurchaseOrder() {
        if (!this.status.equals(PurchaseOrderStatus.CONFIRMED))
            throw new DomainValidateException("Trạng thái đơn mua không phù hợp");
        if(this.status.equals(PurchaseOrderStatus.DELIVERING)){
            return;
        }
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_DELIVERING,
            new FieldChangedPayload(
                "status", this.status, PurchaseOrderStatus.DELIVERING
            )
        ));
        this.status = PurchaseOrderStatus.DELIVERING;
        modify();
    }

    public void completePurchaseOrder() {
        if (!this.status.equals(PurchaseOrderStatus.DELIVERING))
            throw new DomainValidateException("Trạng thái đơn mua không phù hợp");
        addEvent(new PurchaseOrderEvent(
            PurchaseOrderEventName.PURCHASE_ORDER_COMPLETED,
            new FieldChangedPayload(
                "status", this.status, PurchaseOrderStatus.COMPLETED
            )
        ));
        this.status = PurchaseOrderStatus.COMPLETED;
        this.completedDate = new Date();
        modify();
    }

//    public void changeStatus(PurchaseOrderStatus status) {
//        this.status = status;
//        addEvent(
//            new PurchaseOrderEvent(
//                PurchaseOrderEventName.FIELD_VALUE_CHANGED,
//                new FieldChangedPayload(
//                    "status", this.status, status
//                )
//            )
//        );
//    }

    public void updateCancelReason(PurchaseOrderCancellationReason reason, String detail) {
        this.cancellationReason = reason;
        this.cancellationReasonDetail = detail;
        modify();
    }

    public String getCode() {
        return code;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public List<PurchaseOrderLineItem> getLineItems() {
        return lineItems;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public Address getVendorAddress() {
        return vendorAddress;
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
