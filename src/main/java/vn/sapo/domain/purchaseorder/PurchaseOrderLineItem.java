package vn.sapo.domain.purchaseorder;

import vn.sapo.domain.abstractions.BaseLocalEntity;
import vn.sapo.domain.abstractions.LocalEntity;
import vn.sapo.domain.abstractions.event.payloads.FieldChangedPayload;
import vn.sapo.domain.purchaseorder.event.PurchaseOrderEvent;
import vn.sapo.domain.purchaseorder.event.PurchaseOrderEventName;
import vn.sapo.utils.NumberUtils;
import vn.sapo.utils.ObjectUtils;
import vn.sapo.utils.ValidationUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PurchaseOrderLineItem extends BaseLocalEntity<PurchaseOrder, Long> implements LocalEntity<PurchaseOrder, Long> {
    @NotNull
    private Long variantId;
    @Min(1)
    @NotNull
    private Long quantity;
    private BigDecimal taxRate;
    @Min(0)
    @NotNull
    private BigDecimal taxAmount;
    @Min(0)
    @NotNull
    private BigDecimal price;
    @Min(0)
    @NotNull
    private BigDecimal amount;

    public PurchaseOrderLineItem() {
    }

    PurchaseOrderLineItem(
        PurchaseOrder root,
        Long id,
        Long variantId,
        Long quantity,
        BigDecimal taxRate,
        BigDecimal price
    ) {
        this.aggRoot = root;
        this.id = id;
        this.variantId = variantId;
        this.quantity = quantity;
        this.taxRate = taxRate;
        this.price = price;
        calculate();
        validate();
        create();
    }

    public void setQuantity(long quantity) {
        if (ObjectUtils.equals(this.quantity, quantity))
            return;

        this.addEvent(
            new PurchaseOrderEvent(
                PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                new FieldChangedPayload(
                    "quantity",
                    this.quantity,
                    quantity
                )
            )
        );
        this.quantity = quantity;
        validate();
        calculate();
        modify();
    }

    public void setPrice(BigDecimal price) {
        if (NumberUtils.compareBigDecimal(this.price, price) == 0)
            return;

        this.addEvent(
            new PurchaseOrderEvent(
                PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                new FieldChangedPayload(
                    "price",
                    this.price,
                    price
                )
            )
        );

        this.price = price;
        validate();
        calculate();
        modify();
    }

    public void setTaxRate(BigDecimal taxRate) {
        if (NumberUtils.compareBigDecimal(this.taxRate, taxRate) == 0)
            return;

        this.addEvent(
            new PurchaseOrderEvent(
                PurchaseOrderEventName.FIELD_VALUE_CHANGED,
                new FieldChangedPayload(
                    "taxRate",
                    this.taxRate,
                    taxRate
                )
            )
        );
        this.taxRate = taxRate;
        validate();
        calculate();
        modify();
    }

    private void calculate(){
        this.amount = this.price.multiply(BigDecimal.valueOf(this.quantity));
        BigDecimal tax = this.taxRate == null ? BigDecimal.valueOf(0) : this.taxRate;
        this.taxAmount = this.amount.multiply(tax.divide(BigDecimal.valueOf(100)));
    }

    private void validate(){
        ValidationUtils.validateObject(this);
    }

    public Long getVariantId() {
        return variantId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
