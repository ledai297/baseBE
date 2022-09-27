package vn.sapo.vm.warehouse;

import vn.sapo.domain.Warehouse;
import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;
import vn.sapo.vm.pagination.BasePaginationFilterResultModel;

import java.util.List;

public class WarehouseFilterResultModel extends BasePaginationFilterResultModel {
    private List<Warehouse> warehouses;
    private WarehouseFilterReference references;

    public static class WarehouseFilterReference {
        private List<ProvinceModel> provinces;
        private List<DistrictModel> districts;
        private List<WardModel> wards;

        public List<ProvinceModel> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<ProvinceModel> provinces) {
            this.provinces = provinces;
        }

        public List<DistrictModel> getDistricts() {
            return districts;
        }

        public void setDistricts(List<DistrictModel> districts) {
            this.districts = districts;
        }

        public List<WardModel> getWards() {
            return wards;
        }

        public void setWards(List<WardModel> wards) {
            this.wards = wards;
        }
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public WarehouseFilterReference getReferences() {
        return references;
    }

    public void setReferences(WarehouseFilterReference references) {
        this.references = references;
    }
}
