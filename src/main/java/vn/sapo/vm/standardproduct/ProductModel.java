package vn.sapo.vm.standardproduct;


import vn.sapo.statics.product.ProductStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long brandId;
    private Long originalCountryId;
    private Long producerId;
//    private ProductStatus status;
    private List<ProductAttributeValueModel> attributeValues;
    private List<VariantModel> variants;
    private String shortDescription;
    private String fullDescription;
    private String note;
//    private Long createdBy;
    private Instant createdDate;
    private Instant modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getOriginalCountryId() {
        return originalCountryId;
    }

    public void setOriginalCountryId(Long originalCountryId) {
        this.originalCountryId = originalCountryId;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

//    public ProductStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(ProductStatus status) {
//        this.status = status;
//    }

    public List<ProductAttributeValueModel> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<ProductAttributeValueModel> attributeValues) {
        this.attributeValues = attributeValues;
    }

    public List<VariantModel> getVariants() {
        return variants;
    }

    public void setVariants(List<VariantModel> variants) {
        this.variants = variants;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    public Long getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(Long createdBy) {
//        this.createdBy = createdBy;
//    }

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
}
