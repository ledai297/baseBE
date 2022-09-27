package vn.sapo.api.sapocore.model.product;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("variant")
public class SpCreateVariantResponse {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
