package vn.sapo.data.es.dto.product;

import org.springframework.data.elasticsearch.annotations.Document;
import vn.sapo.data.es.dto.BaseEsDto;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Document(
    indexName = "#{@variantIndexName}",
    useServerConfiguration = false
)
public class EsVariant extends BaseEsDto {
    @Id
    private Long id;
    private Long productId;
    private String name;
    private String nameWithoutAccents;
    private String sku;
    private String barcode;
    private String status;
    private String unit;
    private BigDecimal price;
    private String shortDescription;
    private String fullDescription;
    private Long createdBy;
    private Instant createdDate;
    private Instant modifiedDate;
    private BigDecimal weight;
    private String weightUnit;
    private BigDecimal retailPrice;
    private Long baseUnitVariantId;
    private BigDecimal unitMultiplier;
    private List<EsVariantAttribute> attributes;
    private List<EsVariantImage> images;
    private Long countryId;
    private Long categoryId;
    private Long brandId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getBaseUnitVariantId() {
        return baseUnitVariantId;
    }

    public void setBaseUnitVariantId(Long baseUnitVariantId) {
        this.baseUnitVariantId = baseUnitVariantId;
    }

    public BigDecimal getUnitMultiplier() {
        return unitMultiplier;
    }

    public void setUnitMultiplier(BigDecimal unitMultiplier) {
        this.unitMultiplier = unitMultiplier;
    }

    public List<EsVariantAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<EsVariantAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<EsVariantImage> getImages() {
        return images;
    }

    public void setImages(List<EsVariantImage> images) {
        this.images = images;
    }

    public String getNameWithoutAccents() {
        return nameWithoutAccents;
    }

    public void setNameWithoutAccents(String nameWithoutAccents) {
        this.nameWithoutAccents = nameWithoutAccents;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
