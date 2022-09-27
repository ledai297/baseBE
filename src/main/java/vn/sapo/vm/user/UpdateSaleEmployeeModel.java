package vn.sapo.vm.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public class UpdateSaleEmployeeModel {

    @Size(min = 1, max = 255)
    private String firstName;
    private String lastName;
    @Pattern(regexp = "[0-9]{1,15}", message = "Mã nhân viên chỉ bao gồm ký tự số từ 0-9!")
    private String code;
    @Pattern(regexp = "[0-9]{9,13}", message = "Số điện thoại không đúng định dạng")
    private String phoneNumber;
    @Email
    @Size(min = 1, max = 191)
    private String email;
    private Set<String> authorities;
    private Instant startedDate;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Instant getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Instant startedDate) {
        this.startedDate = startedDate;
    }

    @Override
    public String toString() {
        return "UpdateUserEmployeeModel{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", code='" + code + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", authorities=" + authorities +
            ", startedDate=" + startedDate +
            '}';
    }
}
