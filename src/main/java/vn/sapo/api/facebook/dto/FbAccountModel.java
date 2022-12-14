package vn.sapo.api.facebook.dto;

public class FbAccountModel {
    private String id;
    private String name;
    private String email;
    private FbPictureModel picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FbPictureModel getPicture() {
        return picture;
    }

    public void setPicture(FbPictureModel picture) {
        this.picture = picture;
    }
}
