package vn.sapo.domain.purchaseorder;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import vn.sapo.statics.StaticCountry;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Address {
    private String label;
    @NotBlank
    @Length(min = 8, max = 20)
    private String phoneNumber;
    @NotNull
    private Long countryId;
    @NotNull
    private Long provinceId;
    @NotNull
    private Long districtId;
    @NotNull
    private Long wardId;
    @NotBlank
    @Length(min = 1, max = 255)
    private String line1;
    @Length(max = 255)
    private String line2;

    public Address() {

    }

    public Address(Long provinceId, Long districtId, Long wardId, String line1) {
        this.countryId = StaticCountry.VIETNAM.id;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.line1 = line1;
    }

    public Address(Long provinceId, Long districtId, Long wardId, String label, String phoneNumber, String line1, String line2) {
        this(provinceId, districtId, wardId, line1);
        this.label = label;
        this.phoneNumber = phoneNumber;
        this.line2 = line2;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public Long getWardId() {
        return wardId;
    }

    public String getLine1() {
        return line1;
    }

    public String getLabel() {
        return label;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getLine2() {
        return line2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return StringUtils.equals(getLabel(), address.getLabel()) &&
            getPhoneNumber().equals(address.getPhoneNumber()) &&
            getCountryId().equals(address.getCountryId()) &&
            getProvinceId().equals(address.getProvinceId()) &&
            getDistrictId().equals(address.getDistrictId()) &&
            getWardId().equals(address.getWardId()) &&
            StringUtils.equals(getLine1(), address.getLine1()) &&
            StringUtils.equals(getLine2(), address.getLine2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getPhoneNumber(), getCountryId(), getProvinceId(), getDistrictId(), getWardId(), getLine1(), getLine2());
    }

    @Override
    public String toString() {
        return "Address{" +
            "label='" + label + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", countryId=" + countryId +
            ", provinceId=" + provinceId +
            ", districtId=" + districtId +
            ", wardId=" + wardId +
            ", line1='" + line1 + '\'' +
            ", line2='" + line2 + '\'' +
            '}';
    }
}
