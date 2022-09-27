package vn.sapo.domain;


import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import vn.sapo.security.SecurityUtils;
import vn.sapo.statics.FileStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * A ProductFile.
 */
@Entity
@Table(name = "ie_demand")
@TypeDef(
    name = "list-array",
    typeClass = ListArrayType.class
)
public class IEDemand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "action")
    private String action;

    @Column(name = "description")
    private String description;

    @Column(name = "file_url")
    private String fileUrl;

    @NotNull
    @Column(name = "status")
    private FileStatus status;

    @Column(name = "errors", columnDefinition = "varchar[]")
    @Type(type = "list-array")
    private List<String> errors;

    @Column(name = "processed_at")
    private Instant processedAt;

    @Column(name = "processed_end_at")
    private Instant processedEndAt;

    @NotNull
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy = SecurityUtils.getCurrentUserId().orElse(-1L);

    @NotNull
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

    @NotNull
    @Column(name = "last_modified_by")
    private Long lastModifiedBy = SecurityUtils.getCurrentUserId().orElse(-1L);

    @NotNull
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate = Instant.now();

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }

    public Instant getProcessedEndAt() {
        return processedEndAt;
    }

    public void setProcessedEndAt(Instant processedEndAt) {
        this.processedEndAt = processedEndAt;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public FileStatus getStatus() {
        return status;
    }

    public void setStatus(FileStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IEDemand)) {
            return false;
        }
        return id != null && id.equals(((IEDemand) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "IEDemand{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", action='" + action + '\'' +
            ", description='" + description + '\'' +
            ", fileUrl='" + fileUrl + '\'' +
            ", status=" + status +
            ", errors=" + errors +
            ", processedAt=" + processedAt +
            ", processedEndAt=" + processedEndAt +
            ", createdBy=" + createdBy +
            ", createdDate=" + createdDate +
            ", lastModifiedBy=" + lastModifiedBy +
            ", lastModifiedDate=" + lastModifiedDate +
            '}';
    }
}
