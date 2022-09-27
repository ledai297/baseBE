package vn.sapo.vm.standardproduct;

import java.io.Serializable;
import java.util.List;

public class ProductAttributeValueModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long attributeId;
    private String name;
    private List<String> values;

    public ProductAttributeValueModel() {

    }

    public ProductAttributeValueModel(long attributeId, String name, List<String> values) {
        this.attributeId = attributeId;
        this.name = name;
        this.values = values;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ProductAttributeValueResponse{" +
            "attributeId=" + attributeId +
            ", name='" + name + '\'' +
            ", values=" + values +
            '}';
    }
}
