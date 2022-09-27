package vn.sapo.vm.vendor;

import vn.sapo.statics.AddressOwnerType;

import java.util.Date;
import java.util.List;

public class VendorModel {
    private long id;
    private String code;
    private String name;
    private String phoneNumber;
    private List<AddressModel> warehouses;
    private Date createdAt;
    private Date modifiedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AddressModel> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<AddressModel> warehouses) {
        this.warehouses = warehouses;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class AddressModel {
        private Long id;
        private Long ownerId;
        private AddressOwnerType ownerType;
        private String label;
        private String phoneNumber;
        private Long countryId;
        private Long provinceId;
        private Long districtId;
        private Long wardId;
        private String line1;
        private String line2;
        private Date createdAt;
        private Date modifiedAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
        }

        public AddressOwnerType getOwnerType() {
            return ownerType;
        }

        public void setOwnerType(AddressOwnerType ownerType) {
            this.ownerType = ownerType;
        }

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

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(Date modifiedAt) {
            this.modifiedAt = modifiedAt;
        }
    }
}
