package vn.sapo.api.sapocore.model.product;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("product")
public class SpProductResponse {
    private int id;
    private String opt1;
    private String opt2;
    private String opt3;
    private List<SpCreateVariantResponse> variants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SpCreateVariantResponse> getVariants() {
        return variants;
    }

    public void setVariants(List<SpCreateVariantResponse> variants) {
        this.variants = variants;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }
}
