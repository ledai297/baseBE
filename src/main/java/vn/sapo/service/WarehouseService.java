package vn.sapo.service;


import com.fasterxml.jackson.databind.JsonNode;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.sapo.domain.*;
import vn.sapo.repository.*;
import vn.sapo.utils.PaginationUtils;
import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;
import vn.sapo.vm.pagination.Pagination;
import vn.sapo.vm.warehouse.AssignmentAddressModel;
import vn.sapo.vm.warehouse.UpdateWarehouseModel;
import vn.sapo.vm.warehouse.WarehouseFilterModel;
import vn.sapo.vm.warehouse.WarehouseFilterResultModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private JsonHelper jsonHelper;

    @Autowired
    private WarehouseAddressMapRepository warehouseAddressMapRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private MapperFacade mapper;

    public WarehouseFilterResultModel filter(WarehouseFilterModel filterModel) {
        WarehouseFilterResultModel result = new WarehouseFilterResultModel();
        String ids = filterModel.getIds() == null ? "" : StringUtils.join(filterModel.getIds(), ",");
        String keyword = filterModel.getKeyword() == null ? "" : filterModel.getKeyword();

        Long districtId = filterModel.getDeliveryAddressDistrictId() == null
            ? 0L
            : filterModel.getDeliveryAddressDistrictId();

        Pageable pageable = PaginationUtils.convertBasePaginationToPageable(filterModel);
        Page<Warehouse> warehousesPage = warehouseRepository.filter(
            ids,
            districtId,
            keyword,
            filterModel.getStatuses() == null ? "" : StringUtils.join(filterModel.getStatuses(), ","),
            pageable
        );

        List<Warehouse> warehouses = warehousesPage.getContent();
        WarehouseFilterResultModel.WarehouseFilterReference reference = getWarehouseReference(warehouses);
        Pagination pagination = new Pagination(
            warehousesPage.getTotalElements(),
            filterModel.getPage(),
            filterModel.getSize()
        );

        result.setWarehouses(warehouses);
        result.setReferences(reference);
        result.setPagination(pagination);

        return result;
    }

    public WarehouseFilterResultModel.WarehouseFilterReference getWarehouseReference(List<Warehouse> warehouses) {
        WarehouseFilterResultModel.WarehouseFilterReference reference = new WarehouseFilterResultModel.WarehouseFilterReference();

        List<Long> provinceIds = warehouses.stream().map(Warehouse::getProvinceId).collect(Collectors.toList());
        List<Long> districtIds = warehouses.stream().map(Warehouse::getDistrictId).collect(Collectors.toList());
        List<Long> wardIds = warehouses.stream().map(Warehouse::getWardId).collect(Collectors.toList());

        List<Province> provinces = provinceRepository.findByIdIn(provinceIds);
        List<District> districts = districtRepository.findByIdIn(districtIds);
        List<Ward> wards = wardRepository.findByIdIn(wardIds);

        List<ProvinceModel> provinceModels = mapper.mapAsList(provinces, ProvinceModel.class);
        List<DistrictModel> districtModels = mapper.mapAsList(districts, DistrictModel.class);
        List<WardModel> wardModels = mapper.mapAsList(wards, WardModel.class);

        reference.setProvinces(provinceModels);
        reference.setDistricts(districtModels);
        reference.setWards(wardModels);

        return reference;
    }

    public void updateWarehouse(Warehouse warehouse, JsonNode node, UpdateWarehouseModel updateModel) {
        if (jsonHelper.existField(node, "label")) {
            warehouse.setLabel(updateModel.getLabel());
        }

        if (jsonHelper.existField(node, "phone_number")) {
            warehouse.setPhoneNumber(updateModel.getPhoneNumber());
        }

        if (jsonHelper.existField(node, "country_id")) {
            warehouse.setCountryId(updateModel.getCountryId());
        }

        if (jsonHelper.existField(node, "province_id")) {
            warehouse.setProvinceId(updateModel.getProvinceId());
        }

        if (jsonHelper.existField(node, "district_id")) {
            warehouse.setDistrictId(updateModel.getDistrictId());
        }

        if (jsonHelper.existField(node, "ward_id")) {
            warehouse.setWardId(updateModel.getWardId());
        }

        if (jsonHelper.existField(node, "line1")) {
            warehouse.setLine1(updateModel.getLine1());
        }

        if (jsonHelper.existField(node, "line2")) {
            warehouse.setLine2(updateModel.getLine2());
        }

        if (jsonHelper.existField(node, "status")) {
            warehouse.setStatus(updateModel.getStatus());
        }
        warehouse.update();
    }

    public void assignAddress(AssignmentAddressModel model) {
        List<WarehouseAddressMap> existWarehouseAddresses = warehouseAddressMapRepository
            .findByDistrictIdIn(model.getDistrictIds());
        List<Long> existDistrictIds = existWarehouseAddresses.stream().map(WarehouseAddressMap::getDistrictId).collect(Collectors.toList());
        List<Long> nonExistDistrictIds = model.getDistrictIds()
            .stream()
            .filter(districtId -> !existDistrictIds.contains(districtId))
            .collect(Collectors.toList());

        List<WarehouseAddressMap> updateWarehouseAddresses = new ArrayList<>();
        List<WarehouseAddressMap> createWarehouseAddresses = new ArrayList<>();

        for (WarehouseAddressMap warehouseAddress: existWarehouseAddresses) {
            warehouseAddress.setWarehouseId(model.getWarehouseId());
            updateWarehouseAddresses.add(warehouseAddress);
        }

        for (Long districtId: nonExistDistrictIds) {
            WarehouseAddressMap warehouseAddressMap = new WarehouseAddressMap();
            warehouseAddressMap.setDistrictId(districtId);
            warehouseAddressMap.setWarehouseId(model.getWarehouseId());
            createWarehouseAddresses.add(warehouseAddressMap);
        }

        if (updateWarehouseAddresses.size() > 0) {
            warehouseAddressMapRepository.saveAll(updateWarehouseAddresses);
        }

        if (createWarehouseAddresses.size() > 0) {
            warehouseAddressMapRepository.saveAll(createWarehouseAddresses);
        }
    }
}
