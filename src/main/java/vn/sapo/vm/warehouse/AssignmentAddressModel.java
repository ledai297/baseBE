package vn.sapo.vm.warehouse;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AssignmentAddressModel {
    private Long warehouseId;
    @NotNull
    private List<Long> districtIds;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<Long> getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(List<Long> districtIds) {
        this.districtIds = districtIds;
    }
}
