package vn.sapo.api.promotionservice.dto;

import java.math.BigDecimal;

public class PromotionOrderLineItemModel {
    private long variantId;
    private long quantity;
    private BigDecimal price;

    public long getVariantId() {
        return variantId;
    }

    public void setVariantId(long variantId) {
        this.variantId = variantId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
