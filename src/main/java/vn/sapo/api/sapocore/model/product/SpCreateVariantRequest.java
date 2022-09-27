package vn.sapo.api.sapocore.model.product;

import java.math.BigDecimal;
import java.util.List;

public class SpCreateVariantRequest {
    private String description;
    private String name;
    private String opt1;
    private String opt2;
    private String opt3;
    private String sku;
    private String barcode;
    private String unit;
    private List<SpVariantPrice> variantPrices;
    private boolean packsize;
    private Integer packsizeRootId;
    private Integer packsizeQuantity;
    private String weightUnit;
    private BigDecimal weightValue;

    public SpCreateVariantRequest(){

    }

    public SpCreateVariantRequest(String name, String sku, String barcode){
        this.name = name;
        this.sku = sku;
        this.barcode = barcode;
    }
    public void setOptions(String... optionValues){
        if(optionValues.length == 0)
            return;
        if(optionValues.length >= 1)
            this.opt1 = optionValues[0];
        if(optionValues.length >= 2)
            this.opt2 = optionValues[1];
        if(optionValues.length >= 3)
            this.opt3 = optionValues[2];
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<SpVariantPrice> getVariantPrices() {
        return variantPrices;
    }

    public void setVariantPrices(List<SpVariantPrice> variantPrices) {
        this.variantPrices = variantPrices;
    }

    public boolean isPacksize() {
        return packsize;
    }

    public void setPacksize(boolean packsize) {
        this.packsize = packsize;
    }

    public Integer getPacksizeRootId() {
        return packsizeRootId;
    }

    public void setPacksizeRootId(Integer packsizeRootId) {
        this.packsizeRootId = packsizeRootId;
    }

    public Integer getPacksizeQuantity() {
        return packsizeQuantity;
    }

    public void setPacksizeQuantity(Integer packsizeQuantity) {
        this.packsizeQuantity = packsizeQuantity;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(BigDecimal weightValue) {
        this.weightValue = weightValue;
    }
}
