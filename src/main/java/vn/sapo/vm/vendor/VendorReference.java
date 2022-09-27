package vn.sapo.vm.vendor;

import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;

import java.util.List;

public class VendorReference {
    private List<WardModel> wards;
    private List<DistrictModel> districts;
    private List<ProvinceModel> provinces;

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


}
