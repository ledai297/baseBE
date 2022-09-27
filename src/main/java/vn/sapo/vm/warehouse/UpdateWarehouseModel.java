package vn.sapo.vm.warehouse;

import org.hibernate.validator.constraints.Length;
import vn.sapo.statics.WarehouseStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateWarehouseModel {
    @NotNull(message = "Tên kho không được để trống")
    @Length(min=1, max = 255, message = "Tên kho phải từ 1 - 255 ký tự")
    private String label;

    @NotNull(message = "Số điện thoại thoại không được để trống")
    @Pattern(regexp = "[0-9]{9,13}", message = "Số điện thoại không đúng định dạng")
    private String phoneNumber;
    @NotNull(message = "Quốc gia không được để trống")
    private Long countryId;
    @NotNull(message = "Tỉnh/Thành phố không được để trống")
    private Long provinceId;
    @NotNull(message = "Quận/Huyện không được để trống")
    private Long districtId;
    @NotNull(message = "Phường/Xã không được để trống")
    private Long wardId;
    @NotNull(message = "Địa chỉ chi tiết không được để trống")
    @Length(max = 255, message = "Địa chỉ chi tiết phải từ 1 - 255 ký tự")
    private String line1;
    @Length(min = 1, max = 255, message = "Địa chỉ chi tiết phải từ 1 - 255 ký tự")
    private String line2;

    private WarehouseStatus status;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public WarehouseStatus getStatus() {
        return status;
    }

    public void setStatus(WarehouseStatus status) {
        this.status = status;
    }
}
