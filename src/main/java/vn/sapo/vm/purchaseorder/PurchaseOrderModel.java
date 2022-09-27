package vn.sapo.vm.purchaseorder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PurchaseOrderModel {
    private Long Id;
    private String code;
    private Long vendorId;
    private String vendorName;
    private Long warehouseId;
    private String warehouseName;
    private BigDecimal totalAmount;
    private BigDecimal totalTaxAmount;
    private Date createdDate;
    private Date purchaseDate;
    private Date confirmedDate;
    private Date expectedDeliveryDate;
    private Long userId;
    private String userName;
    private AddressResponse vendorAddress;
    private List<PurchaseOrderLineItem> lineItems;
    private PurchaseOrderReferenceResponse reference;
    private Vendor vendor;
    private String note;

    public PurchaseOrderModel(){

    }

    public void setIndexLineItem() {
        for (int i = 0; i < lineItems.size(); i++){
            PurchaseOrderLineItem lineItem = lineItems.get(i);
            lineItem.setIndex(i + 1);
        }
    }

    public PurchaseOrderReferenceResponse getReference() {
        return reference;
    }

    public void setReference(PurchaseOrderReferenceResponse reference) {
        this.reference = reference;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public AddressResponse getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(AddressResponse vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public List<PurchaseOrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<PurchaseOrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    //    public void formatDecimal(){
//        for(int i = 0; i < lineItems.size(); i++){
//            PurchaseOrderLineItem lineItem = lineItems.get(i);
//            lineItem.setTaxRate(BigDecimal.valueOf(Double.valueOf(lineItem.getTaxRate().stripTrailingZeros().toPlainString())));
//        }
//    }

    public static class PurchaseOrderLineItem {
        private int index;
        private Long id;
        private Long variantId;
        private String sku;
        private String barcode;
        private String name;
        private String unit;
        private Long quantity;
        private BigDecimal taxRate;
        private BigDecimal price;
        private BigDecimal taxAmount;
        private BigDecimal amount;
        private Long createdBy;
        private Instant createdDate;
        private Instant modifiedDate;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getVariantId() {
            return variantId;
        }

        public void setVariantId(Long variantId) {
            this.variantId = variantId;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(BigDecimal taxRate) {
            this.taxRate = taxRate;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Long getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
        }

        public Instant getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Instant createdDate) {
            this.createdDate = createdDate;
        }

        public Instant getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Instant modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public static class Vendor {
        private long id;
        private String code;
        private String name;
        private String phoneNumber;
        private String email;
        private String taxIdentificationNumber;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTaxIdentificationNumber() {
            return taxIdentificationNumber;
        }

        public void setTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
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

}
