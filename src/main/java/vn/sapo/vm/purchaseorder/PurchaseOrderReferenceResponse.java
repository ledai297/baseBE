package vn.sapo.vm.purchaseorder;

import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderReferenceResponse {
    private List<Vendor> vendors;
    private List<Warehouse> warehouses;
    private List<Variant> variants;
    private List<WardModel> wards;
    private List<DistrictModel> districts;
    private List<ProvinceModel> provinces;
    private List<User> users;

    public PurchaseOrderReferenceResponse() {
        this.vendors = new ArrayList<>();
        this.warehouses = new ArrayList<>();
        this.variants = new ArrayList<>();
        this.wards = new ArrayList<>();
        this.districts = new ArrayList<>();
        this.provinces = new ArrayList<>();
    }

    public PurchaseOrderReferenceResponse(List<Vendor> vendors,
                                          List<Warehouse> warehouses,
                                          List<Variant> variants) {
        this.vendors = vendors;
        this.warehouses = warehouses;
        this.variants = variants;
    }

    public PurchaseOrderReferenceResponse (List<Vendor> vendors){
        this.vendors = vendors;
    }
    public PurchaseOrderReferenceResponse (List<Vendor> vendors, List<User> users){
        this.vendors = vendors;
        this.users = users;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public List<WardModel> getWards() {
        return wards;
    }

    public void setWards(List<WardModel> wards) {
        this.wards = wards;
    }

    public List<DistrictModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictModel> districts) {
        this.districts = districts;
    }

    public List<ProvinceModel> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceModel> provinces) {
        this.provinces = provinces;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class Vendor {
        private long id;
        private String code;
        private String name;
        private String phoneNumber;

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
    }

    public static class Warehouse {
        private Long id;
        private String label;
        private String phoneNumber;
        private Long countryId;
        private Long provinceId;
        private Long districtId;
        private Long wardId;
        private String line1;
        private String line2;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }

    public static class Variant {
        private Long id;
        private Long productId;
        private String name;
        private String sku;
        private String barcode;
        private String unit;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public static class User {
        private Long id;
        private String firstName;
        private String lastName;
        private String username;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
