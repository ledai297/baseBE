package vn.sapo.vm.purchaseorder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreatePurchaseOrderLineItemRequest {
    @NotNull
    private Long variantId;
    @NotNull
    @Min(1)
    private Long quantity;
    @Min(0)
    private BigDecimal taxRate;
    @Min(0)
    @NotNull
    private BigDecimal price;

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
