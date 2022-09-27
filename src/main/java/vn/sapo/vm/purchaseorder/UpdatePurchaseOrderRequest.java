package vn.sapo.vm.purchaseorder;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

public class UpdatePurchaseOrderRequest {
    private Long warehouseId;
    private Date purchaseDate;
    private Date expectedDeliveryDate;
    private List<UpdatePurchaseOrderLineItemRequest> lineItems;
    private UpdateAddressRequest vendorAddress;
    @Length(max = 999, message = "Ghi chú không vượt quá 999 ký tự")
    private String note;

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

    public List<UpdatePurchaseOrderLineItemRequest> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<UpdatePurchaseOrderLineItemRequest> lineItems) {
        this.lineItems = lineItems;
    }

    public UpdateAddressRequest getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(UpdateAddressRequest vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
