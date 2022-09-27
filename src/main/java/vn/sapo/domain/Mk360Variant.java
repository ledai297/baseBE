package vn.sapo.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mk_360_variant")
public class Mk360Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mkVariantId;
    private Long mkProductId;

    @Column(name = "sapo360_product_id")
    private Long sapo360ProductId;

    @Column(name = "sapo360_variant_id")
    private Long sapo360VariantId;
    private Date mappedAt;

    public Mk360Variant() {
    }

    public Mk360Variant(long mkVariantId, long mkProductId, long sapo360VariantId, long sapo360ProductId) {
        this.mkProductId = mkProductId;
        this.mkVariantId = mkVariantId;
        this.sapo360VariantId = sapo360VariantId;
        this.sapo360ProductId = sapo360ProductId;
        this.mappedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMkVariantId() {
        return mkVariantId;
    }

    public void setMkVariantId(Long mkVariantId) {
        this.mkVariantId = mkVariantId;
    }

    public Long getMkProductId() {
        return mkProductId;
    }

    public void setMkProductId(Long mkProductId) {
        this.mkProductId = mkProductId;
    }

    public Long getSapo360ProductId() {
        return sapo360ProductId;
    }

    public void setSapo360ProductId(Long sapo360ProductId) {
        this.sapo360ProductId = sapo360ProductId;
    }

    public Long getSapo360VariantId() {
        return sapo360VariantId;
    }

    public void setSapo360VariantId(Long sapo360VariantId) {
        this.sapo360VariantId = sapo360VariantId;
    }

    public Date getMappedAt() {
        return mappedAt;
    }

    public void setMappedAt(Date mappedAt) {
        this.mappedAt = mappedAt;
    }
}
