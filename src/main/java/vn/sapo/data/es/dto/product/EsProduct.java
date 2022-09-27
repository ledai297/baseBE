package vn.sapo.data.es.dto.product;

import org.springframework.data.elasticsearch.annotations.Document;
import vn.sapo.data.es.dto.BaseEsDto;

import javax.persistence.Id;
import java.time.Instant;
import java.util.List;

@Document(
    indexName = "#{@productIndexName}",
    useServerConfiguration = false
)
public class EsProduct extends BaseEsDto {
    @Id
    private Long id;
    private String name;
    private String nameWithoutAccents;
    private Long categoryId;
    private Long brandId;
    private Long originalCountryId;
    private Long producerId;
    private String status;
    private String shortDescription;
    private String fullDescription;
    private String note;
    private Long createdBy;
    private Instant createdDate;
    private Instant modifiedDate;
    private List<EsProductVariant> variants;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<EsProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<EsProductVariant> variants) {
        this.variants = variants;
    }

    public String getNameWithoutAccents() {
        return nameWithoutAccents;
    }

    public void setNameWithoutAccents(String nameWithoutAccents) {
        this.nameWithoutAccents = nameWithoutAccents;
    }
}
