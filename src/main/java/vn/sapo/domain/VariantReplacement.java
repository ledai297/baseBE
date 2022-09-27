package vn.sapo.domain;

import javax.persistence.*;

@Entity
@Table(name = "variant_replacement")
public class VariantReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long variantId;
    private long replacementId;

    public VariantReplacement() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVariantId() {
        return variantId;
    }

    public void setVariantId(long variantId) {
        this.variantId = variantId;
    }

    public long getReplacementId() {
        return replacementId;
    }

    public void setReplacementId(long replacementId) {
        this.replacementId = replacementId;
    }
}
