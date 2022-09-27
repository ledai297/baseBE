package vn.sapo.api.sapocore.model.product;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("product")
public class SpCreateProductRequest {
    private String name;
    private String brand;
    private String content;

    private List<SpCreateOptionRequest> options;

    private List<SpCreateVariantRequest> variants;

    public SpCreateProductRequest(){

    }

    public SpCreateProductRequest(String name, List<SpCreateVariantRequest> variants){
        this.name = name;
        this.options = new ArrayList<>();
        this.variants = variants;
    }
    public SpCreateProductRequest(
        String name,
        List<SpCreateVariantRequest> variants,
        List<SpCreateOptionRequest> options
    ){
        this(name, variants);
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SpCreateVariantRequest> getVariants() {
        return variants;
    }

    public void setVariants(List<SpCreateVariantRequest> variants) {
        this.variants = variants;
    }

    public List<SpCreateOptionRequest> getOptions() {
        return options;
    }

    public void setOptions(List<SpCreateOptionRequest> options) {
        this.options = options;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
