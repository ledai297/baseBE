package vn.sapo.api.sapocore.model.product;

import java.math.BigDecimal;

public class SpVariantPrice {
    private int priceListId;
    private BigDecimal value;

    public SpVariantPrice(int priceListId, BigDecimal value){
        this.priceListId = priceListId;
        this.value = value;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(int priceListId) {
        this.priceListId = priceListId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
