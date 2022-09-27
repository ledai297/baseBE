package vn.sapo.api.sapocore.model.product;

public class SpCreateOptionRequest {
    private String name;

    public SpCreateOptionRequest(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
