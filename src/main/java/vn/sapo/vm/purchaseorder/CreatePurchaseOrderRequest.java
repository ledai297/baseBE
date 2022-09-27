package vn.sapo.vm.purchaseorder;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class CreatePurchaseOrderRequest {
    @NotNull
    private Long vendorId;
    @NotNull
    private Long warehouseId;
    @NotNull
    private Date purchaseDate;
    @NotNull
    private Date expectedDeliveryDate;
    @NotEmpty
    private List<@Valid CreatePurchaseOrderLineItemRequest> lineItems;
    @NotNull
    @Valid
    private CreateAddressRequest vendorAddress;
    @Length(max = 999, message = "Ghi chú không được vượt quá 999 ký tự")
    private String note;

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

    public List<CreatePurchaseOrderLineItemRequest> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<CreatePurchaseOrderLineItemRequest> lineItems) {
        this.lineItems = lineItems;
    }

    public CreateAddressRequest getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(CreateAddressRequest vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
