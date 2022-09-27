package vn.sapo.vm.standardproduct;

import java.io.Serializable;

public class VariantAttributeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long attributeId;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
