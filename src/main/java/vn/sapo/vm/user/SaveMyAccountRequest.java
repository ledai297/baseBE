package vn.sapo.vm.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class SaveMyAccountRequest {
    @Length(max=50)
    private String firstName;
    private String lastName;
    private String imageUrl;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
