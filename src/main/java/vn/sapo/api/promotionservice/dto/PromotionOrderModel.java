package vn.sapo.api.promotionservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class PromotionOrderModel {
    private PromotionCustomerModel customer;
    private PromotionAddressModel deliveryAddress;
    private List<PromotionOrderLineItemModel> lineItems;
    private BigDecimal totalAmount;

    public PromotionCustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(PromotionCustomerModel customer) {
        this.customer = customer;
    }

    public PromotionAddressModel getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(PromotionAddressModel deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<PromotionOrderLineItemModel> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<PromotionOrderLineItemModel> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
