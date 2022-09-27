package vn.sapo.vm.vendor;

import java.util.List;

public class VendorFilterResultModel {
    private List<VendorModel> vendors;
    private VendorReference references;

    public List<VendorModel> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorModel> vendors) {
        this.vendors = vendors;
    }

    public VendorReference getReferences() {
        return references;
    }

    public void setReferences(VendorReference references) {
        this.references = references;
    }
}
