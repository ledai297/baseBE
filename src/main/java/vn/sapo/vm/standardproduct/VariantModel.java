package vn.sapo.vm.standardproduct;

import vn.sapo.statics.WeightUnit;
import vn.sapo.statics.product.VariantStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class VariantModel {
    private Long id;
    private String sku;
    private String barcode;
    private String name;
//    private VariantStatus status;
    private String unit;
    private BigDecimal price;
    private String shortDescription;
    private String fullDescription;
    private List<VariantAttributeModel> attributes;
    private List<ImageModel> images;
    private Long productId;
    private Instant createdDate;
    private Instant modifiedDate;
    private BigDecimal weight;
    private WeightUnit weightUnit;
    private BigDecimal retailPrice;
    private BigDecimal unitMultiplier;
    private Long baseUnitVariantId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public VariantStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(VariantStatus status) {
//        this.status = status;
//    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getUnitMultiplier() {
        return unitMultiplier;
    }

    public void setUnitMultiplier(BigDecimal unitMultiplier) {
        this.unitMultiplier = unitMultiplier;
    }

    public Long getBaseUnitVariantId() {
        return baseUnitVariantId;
    }

    public void setBaseUnitVariantId(Long baseUnitVariantId) {
        this.baseUnitVariantId = baseUnitVariantId;
    }

    public List<VariantAttributeModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<VariantAttributeModel> attributes) {
        this.attributes = attributes;
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public void setImages(List<ImageModel> images) {
        this.images = images;
    }
}
