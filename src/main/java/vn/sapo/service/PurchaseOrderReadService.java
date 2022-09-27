package vn.sapo.service;

import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import vn.sapo.domain.District;
import vn.sapo.domain.Province;
import vn.sapo.domain.User;
import vn.sapo.domain.Ward;
import vn.sapo.domain.purchaseorder.PurchaseOrder;
import vn.sapo.domain.purchaseorder.PurchaseOrderLineItem;
import vn.sapo.domain.vendor.Vendor;
import vn.sapo.exception.NotFoundException;
import vn.sapo.projection.product.VariantDto;
import vn.sapo.repository.*;
import vn.sapo.repository.purchaseorder.PurchaseOrderRepository;
import vn.sapo.repository.vendor.VendorRepository;
import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;
import vn.sapo.vm.purchaseorder.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderReadService {

    private final VendorRepository vendorRepository;
    private final WarehouseRepository warehouseRepository;
    private final VariantProjectionRepository variantRepository;
    private final MapperFacade mapper;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final UserRepository userRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final SpringTemplateEngine templateEngine;
    private final VariantProjectionRepository variantProjectionRepository;
    @Value("${external.html-to-pdf.endpoint}")
    private String converterUrl;

    public PurchaseOrderReadService(
        VendorRepository vendorRepository,
        WarehouseRepository warehouseRepository,
        VariantProjectionRepository variantRepository,
        PurchaseOrderRepository purchaseOrderRepository,
        UserRepository userRepository,
        MapperFacade mapper,
        ProvinceRepository provinceRepository,
        DistrictRepository districtRepository,
        WardRepository wardRepository,
        SpringTemplateEngine templateEngine,
        VariantProjectionRepository variantProjectionRepository
    ) {
        this.vendorRepository = vendorRepository;
        this.warehouseRepository = warehouseRepository;
        this.variantRepository = variantRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.wardRepository = wardRepository;
        this.templateEngine = templateEngine;
        this.variantProjectionRepository = variantProjectionRepository;
    }

    public PurchaseOrderReferenceResponse getReference(PurchaseOrder purchaseOrder) {
        PurchaseOrderReferenceResponse reference = new PurchaseOrderReferenceResponse();
        if (purchaseOrder == null) {
            return reference;
        }
        vendorRepository.findById(purchaseOrder.getVendorId())
            .ifPresent(vendor -> {
                reference.getVendors().add(mapper.map(vendor, PurchaseOrderReferenceResponse.Vendor.class));
            });

        warehouseRepository.findById(purchaseOrder.getWarehouseId())
            .ifPresent(warehouse -> {
                reference.getWarehouses().add(mapper.map(warehouse, PurchaseOrderReferenceResponse.Warehouse.class));
            });
        Set<Long> variantIds = purchaseOrder.getLineItems()
            .stream()
            .map(PurchaseOrderLineItem::getVariantId)
            .collect(Collectors.toSet());
        List<VariantDto> variants = variantRepository.findByIdIn(variantIds, VariantDto.class);
        reference.getVariants().addAll(mapper.mapAsList(variants, PurchaseOrderReferenceResponse.Variant.class));

        Set<Long> wardIds = new HashSet<>();
        wardIds.add(purchaseOrder.getVendorAddress().getWardId());
        wardIds.addAll(reference.getWarehouses().stream().map(PurchaseOrderReferenceResponse.Warehouse::getWardId).collect(Collectors.toSet()));
        List<Ward> wards = wardRepository.findByIdIn(wardIds);
        List<WardModel> wardModels = mapper.mapAsList(
            wards,
            WardModel.class
        );
        reference.setWards(wardModels);

        Set<Long> districtIds = new HashSet<>();
        districtIds.add(purchaseOrder.getVendorAddress().getDistrictId());
        districtIds.addAll(reference.getWarehouses().stream().map(PurchaseOrderReferenceResponse.Warehouse::getDistrictId).collect(Collectors.toSet()));
        List<District> districts = districtRepository.findByIdIn(districtIds);
        List<DistrictModel> districtModels = mapper.mapAsList(
            districts,
            DistrictModel.class
        );
        reference.setDistricts(districtModels);

        Set<Long> provinceIds = new HashSet<>();
        provinceIds.add(purchaseOrder.getVendorAddress().getProvinceId());
        provinceIds.addAll(reference.getWarehouses().stream().map(PurchaseOrderReferenceResponse.Warehouse::getProvinceId).collect(Collectors.toSet()));
        List<Province> provinces = provinceRepository.findByIdIn(provinceIds);
        List<ProvinceModel> provinceModels = mapper.mapAsList(
            provinces,
            ProvinceModel.class
        );
        reference.setProvinces(provinceModels);

        User user = userRepository.getById(purchaseOrder.getCreatedBy());
        List<PurchaseOrderReferenceResponse.User> users = new ArrayList<>();
        users.add(mapper.map(user, PurchaseOrderReferenceResponse.User.class));

        reference.setUsers(users);
        return reference;
    }

    public PurchaseOrderReferenceResponse getReferences (List<PurchaseOrderResponse> purchaseOrderResponses) {
        List<Long> vendorIds = purchaseOrderResponses.stream()
            .map(PurchaseOrderResponse::getVendorId)
            .collect(Collectors.toList());

        List<Long> userIds = purchaseOrderResponses.stream()
            .map(PurchaseOrderResponse::getCreatedBy)
            .collect(Collectors.toList());

        List<Long> idsVendor = vendorIds.stream().distinct().collect(Collectors.toList());
        List<Vendor> vendorList = vendorRepository.findAllById(idsVendor);

        List<Long> idsUser = userIds.stream().distinct().collect(Collectors.toList());
        List<User> userList = userRepository.findAllById(idsUser);

        List<PurchaseOrderReferenceResponse.Vendor> vendors = mapper.mapAsList(vendorList, PurchaseOrderReferenceResponse.Vendor.class);
        List<PurchaseOrderReferenceResponse.User> users = mapper.mapAsList(userList, PurchaseOrderReferenceResponse.User.class);

        return new PurchaseOrderReferenceResponse(vendors, users);
    }


    public long countPurchaseOrder(PurchaseOrderFilterModel purchaseOrderFilterModel) {
        String ids = purchaseOrderFilterModel.getIds() != null && purchaseOrderFilterModel.getIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getIds(), ",") : "";
        String userIds = purchaseOrderFilterModel.getUserIds() != null  && purchaseOrderFilterModel.getUserIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getUserIds(), ",") : "";
        String vendorIds = purchaseOrderFilterModel.getVendorIds() != null  && purchaseOrderFilterModel.getVendorIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getVendorIds(), ",") : "";
        String warehouseIds = purchaseOrderFilterModel.getWarehouseIds() != null  && purchaseOrderFilterModel.getWarehouseIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getWarehouseIds(), ",") : "";
        String keyword = purchaseOrderFilterModel.getKeyword() == null ? "" : purchaseOrderFilterModel.getKeyword();
        String createdDateMin = purchaseOrderFilterModel.getCreatedDateMin() == null ? "" : purchaseOrderFilterModel.getCreatedDateMin();
        String createdDateMax = purchaseOrderFilterModel.getCreatedDateMax() == null ? "" : purchaseOrderFilterModel.getCreatedDateMax();
        String purchaseDateMin = purchaseOrderFilterModel.getPurchaseDateMin() == null ? "" : purchaseOrderFilterModel.getPurchaseDateMin();
        String purchaseDateMax = purchaseOrderFilterModel.getPurchaseDateMax() == null ? "" : purchaseOrderFilterModel.getPurchaseDateMax();
        String statuses = purchaseOrderFilterModel.getStatuses() != null  && purchaseOrderFilterModel.getStatuses().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getStatuses(), ",") : "";

        return purchaseOrderRepository.countPurchaseOrders(
            ids,
            userIds,
            vendorIds,
            warehouseIds,
            keyword,
            createdDateMin,
            createdDateMax,
            purchaseDateMin,
            purchaseDateMax,
            statuses
        );
    }

    public List<PurchaseOrderResponse> filterPurchaseOrders(PurchaseOrderFilterModel purchaseOrderFilterModel, Pageable pageable) {
        String ids = purchaseOrderFilterModel.getIds() != null && purchaseOrderFilterModel.getIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getIds(), ",") : "";
        String userIds = purchaseOrderFilterModel.getUserIds() != null  && purchaseOrderFilterModel.getUserIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getUserIds(), ",") : "";
        String vendorIds = purchaseOrderFilterModel.getVendorIds() != null  && purchaseOrderFilterModel.getVendorIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getVendorIds(), ",") : "";
        String warehouseIds = purchaseOrderFilterModel.getWarehouseIds() != null  && purchaseOrderFilterModel.getWarehouseIds().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getWarehouseIds(), ",") : "";
        String keyword = purchaseOrderFilterModel.getKeyword() == null ? "" : purchaseOrderFilterModel.getKeyword();
        String createdDateMin = purchaseOrderFilterModel.getCreatedDateMin() == null ? "" : purchaseOrderFilterModel.getCreatedDateMin();
        String createdDateMax = purchaseOrderFilterModel.getCreatedDateMax() == null ? "" : purchaseOrderFilterModel.getCreatedDateMax();
        String purchaseDateMin = purchaseOrderFilterModel.getPurchaseDateMin() == null ? "" : purchaseOrderFilterModel.getPurchaseDateMin();
        String purchaseDateMax = purchaseOrderFilterModel.getPurchaseDateMax() == null ? "" : purchaseOrderFilterModel.getPurchaseDateMax();
        String statuses = purchaseOrderFilterModel.getStatuses() != null  && purchaseOrderFilterModel.getStatuses().size() > 0 ?
            StringUtils.join(purchaseOrderFilterModel.getStatuses(), ",") : "";

        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.filterPurchaseOrders(
            ids,
            userIds,
            vendorIds,
            warehouseIds,
            keyword,
            createdDateMin,
            createdDateMax,
            purchaseDateMin,
            purchaseDateMax,
            statuses,
            pageable
        );
        return mapper.mapAsList(purchaseOrders, PurchaseOrderResponse.class);
    }

    public Map<String, Object> exportPdf(PurchaseOrder purchaseOrder) {

        PurchaseOrderModel purchaseOrderModel = mapper.map(purchaseOrder, PurchaseOrderModel.class);
        String vendorName = purchaseOrderModel.getVendorAddress().getLabel() + ", " + purchaseOrderModel.getVendorAddress().getLine1();

        PurchaseOrderReferenceResponse reference = getReference(purchaseOrder);
        for(WardModel model : reference.getWards()){
            if(model.getId() == purchaseOrder.getVendorAddress().getWardId().longValue()){
                vendorName += ", " +  model.getName();
            }
        }
        for(DistrictModel model : reference.getDistricts()){
            if(model.getId() == purchaseOrder.getVendorAddress().getDistrictId().longValue()){
                vendorName += ", " +  model.getName();
            }
        }
        for(ProvinceModel model : reference.getProvinces()){
            if(model.getId() == purchaseOrder.getVendorAddress().getProvinceId().longValue()){
                vendorName += ", " +  model.getName();
            }
        }
        purchaseOrderModel.setVendorName(vendorName);
        Optional<Vendor> vendor = vendorRepository.findById(purchaseOrderModel.getVendorId());
        PurchaseOrderModel.Vendor vendorResponse = mapper.map(vendor.get(), PurchaseOrderModel.Vendor.class);
        if(vendorResponse.getPhoneNumber().equals("unknown")){
            vendorResponse.setPhoneNumber("");
        }
        purchaseOrderModel.setVendor(vendorResponse);

        String warehouseName = "";
        for(PurchaseOrderReferenceResponse.Warehouse model : reference.getWarehouses()){
            if(model.getId().longValue() == purchaseOrder.getWarehouseId()){
                warehouseName += model.getLabel();
                if(!model.getPhoneNumber().equals("")){
                    warehouseName+= " - "  + model.getPhoneNumber();
                }
                if(!model.getLine1().equals("") && model.getLine1() != null){
                    warehouseName += ", " + model.getLine1();
                }
                for(WardModel wardModel : reference.getWards()){
                    if(model.getWardId().longValue() == wardModel.getId()){
                        warehouseName += ", " + wardModel.getName();
                    }
                }
                for(DistrictModel districtModel : reference.getDistricts()){
                    if(model.getDistrictId().longValue() == districtModel.getId()){
                        warehouseName += ", " + districtModel.getName();
                    }
                }

                for(ProvinceModel provinceModel : reference.getProvinces()){
                    if(model.getProvinceId() == provinceModel.getId()){
                        warehouseName += ", " + provinceModel.getName();
                    }
                }
            }
        }
        purchaseOrderModel.setWarehouseName(warehouseName);
        purchaseOrderModel.setIndexLineItem();

        List<Long> ids = purchaseOrderModel.getLineItems()
            .stream()
            .map(PurchaseOrderModel.PurchaseOrderLineItem::getVariantId).collect(Collectors.toList());

        List<VariantDto> variants = variantProjectionRepository.findAllById(ids);

        for (PurchaseOrderModel.PurchaseOrderLineItem lineItem : purchaseOrderModel.getLineItems()) {
            for(VariantDto variantDto : variants){
                if(lineItem.getVariantId().equals(variantDto.getId())){
                    lineItem.setUnit(variantDto.getUnit());
                    lineItem.setName(variantDto.getName());
                    lineItem.setBarcode(variantDto.getBarcode());
                    lineItem.setSku(variantDto.getSku());
                }
            }
        }
        Map<String, Object> purchaseOrders = new HashMap<>();
        purchaseOrders.put("purchaseOrder", purchaseOrderModel);
//                String content = resolveTemplate("purchaseOrder/purchaseOrder.html", purchaseOrders);

        return purchaseOrders;



//        ConvertModel model = new ConvertModel(content);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
//        HttpEntity request = new HttpEntity(model, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<byte[]> response = restTemplate.exchange(
//            converterUrl,
//            HttpMethod.POST,
//            request,
//            byte[].class
//        );
//
//        byte[] barray = response.getBody();
//        MediaS3DTO s3FileDto = cloudStorageService.generateMediaS3DTO(
//            barray,
//            String.valueOf(new Date().getTime()) + ".pdf",
//            ""
//        );
//        String uri = cloudStorageService.upload(s3FileDto);

//        return barray;
    }

    public byte[] batchExportPdf(List<Long> ids) throws IOException {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findByIdInAndDeletedFalse(ids);
        List<Map<String, Object>> purchaseOrderList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < purchaseOrders.size(); i += 1) {
            Map<String, Object> po = exportPdf(purchaseOrders.get(i));
            purchaseOrderList.add(po);
        }
        String content = resolveTemplate("purchaseOrder/purchaseOrderContent.html", purchaseOrders);
    }

    public String resolveTemplate(String templateName, Map<String, Object> variables){
        Context context = new Context();
        for(String key : variables.keySet()){
            context.setVariable(key, variables.get(key));
        }
        String content = templateEngine.process(templateName, context);
        return content;
    }
}
