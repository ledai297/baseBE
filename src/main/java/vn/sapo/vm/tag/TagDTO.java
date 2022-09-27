package vn.sapo.vm.tag;

import vn.sapo.statics.TagReferName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

public class TagDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TagReferName referName;

    @NotBlank
    @Size(max = 50)
    private String value;

    private Instant createdDate = Instant.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagReferName getReferName() {
        return referName;
    }

    public void setReferName(TagReferName referName) {
        this.referName = referName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
            "id=" + id +
            ", referName=" + referName +
            ", value='" + value + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}
