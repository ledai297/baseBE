package vn.sapo.web.rest;

import com.fasterxml.jackson.databind.JsonNode;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.spring5.SpringTemplateEngine;
import vn.sapo.domain.Mk360Variant;
import vn.sapo.domain.User;
import vn.sapo.domain.VariantReplacement;
import vn.sapo.domain.Warehouse;
import vn.sapo.domain.purchaseorder.PurchaseOrder;
import vn.sapo.domain.purchaseorder.PurchaseOrderLog;
import vn.sapo.domain.vendor.Vendor;
import vn.sapo.exception.DomainValidateException;
import vn.sapo.exception.NotFoundException;
import vn.sapo.projection.product.VariantDto;
import vn.sapo.repository.*;
import vn.sapo.repository.purchaseorder.PurchaseOrderLogRepository;
import vn.sapo.repository.purchaseorder.PurchaseOrderRepository;
import vn.sapo.repository.vendor.VendorRepository;
import vn.sapo.service.JsonHelper;
import vn.sapo.service.MediaService;
import vn.sapo.service.PurchaseOrderReadService;
import vn.sapo.service.PurchaseOrderWriteService;
import vn.sapo.service.validation.PurchaseOrderValidator;
import vn.sapo.statics.StaticProvince;
import vn.sapo.utils.RequestUtils;
import vn.sapo.vm.address.DistrictModel;
import vn.sapo.vm.address.ProvinceModel;
import vn.sapo.vm.address.WardModel;
import vn.sapo.vm.pagination.Pagination;
import vn.sapo.vm.purchaseorder.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase_orders")
public class PurchaseOrderResource {
    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

    private final PurchaseOrderWriteService purchaseOrderWriteService;
    private final PurchaseOrderValidator purchaseOrderValidator;
    private final PurchaseOrderReadService purchaseOrderReadService;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderLogRepository purchaseOrderLogRepository;
    private final VendorRepository vendorRepository;
    private final WarehouseRepository warehouseRepository;
    private final VariantProjectionRepository variantProjectionRepository;
    private final UserRepository userRepository;
    private final MediaService cloudStorageService;
    private final SpringTemplateEngine templateEngine;
    private final MapperFacade mapper;
    private final JsonHelper jsonHelper;
    private final Mk360VariantRepository mk360VariantRepository;
    @Value("${external.html-to-pdf.endpoint}")
    private String converterUrl;
    private final VariantReplacemantRepository variantReplacemantRepository;

    public PurchaseOrderResource(PurchaseOrderWriteService purchaseOrderWriteService,
                                 PurchaseOrderValidator purchaseOrderValidator,
                                 PurchaseOrderReadService purchaseOrderReadService,
                                 PurchaseOrderRepository purchaseOrderRepository,
                                 PurchaseOrderLogRepository purchaseOrderLogRepository,
                                 VendorRepository vendorRepository,
                                 WarehouseRepository warehouseRepository,
                                 VariantProjectionRepository variantProjectionRepository,
                                 UserRepository userRepository,
                                 MediaService cloudStorageService,
                                 SpringTemplateEngine templateEngine,
                                 MapperFacade mapper,
                                 JsonHelper jsonHelper,
                                 Mk360VariantRepository mk360VariantRepository,
                                 VariantReplacemantRepository variantReplacemantRepository) {
        this.purchaseOrderWriteService = purchaseOrderWriteService;
        this.purchaseOrderValidator = purchaseOrderValidator;
        this.purchaseOrderReadService = purchaseOrderReadService;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderLogRepository = purchaseOrderLogRepository;
        this.vendorRepository = vendorRepository;
        this.warehouseRepository = warehouseRepository;
        this.variantProjectionRepository = variantProjectionRepository;
        this.userRepository = userRepository;
        this.cloudStorageService = cloudStorageService;
        this.templateEngine = templateEngine;
        this.mapper = mapper;
        this.jsonHelper = jsonHelper;
        this.mk360VariantRepository = mk360VariantRepository;
        this.variantReplacemantRepository = variantReplacemantRepository;
    }

    @PostMapping
    public PurchaseOrderSingleResponse create(@RequestBody @Valid CreatePurchaseOrderRequest createRequest) throws IOException {
        PurchaseOrder purchaseOrder = purchaseOrderWriteService.create(createRequest);
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @GetMapping
    public PurchaseOrderFilterResultModel filterPurchaseOrder(PurchaseOrderFilterModel purchaseOrderFilterModel) {
        long count = purchaseOrderReadService.countPurchaseOrder(purchaseOrderFilterModel);
        Pageable pageable = PageRequest.of(purchaseOrderFilterModel.getPage() - 1, purchaseOrderFilterModel.getSize(), Sort.unsorted());

        if (count == 0) {
            return new PurchaseOrderFilterResultModel(
                new ArrayList<>(),
                new Pagination(
                    count,
                    purchaseOrderFilterModel.getPage(),
                    purchaseOrderFilterModel.getSize()
                )
            );
        }
        List<PurchaseOrderResponse> purchaseOrderModels = purchaseOrderReadService.filterPurchaseOrders(purchaseOrderFilterModel, pageable);
        Pagination pagination = new Pagination(count, purchaseOrderFilterModel.getPage(), purchaseOrderFilterModel.getSize());
        PurchaseOrderReferenceResponse references = purchaseOrderReadService.getReferences(purchaseOrderModels);

        return new PurchaseOrderFilterResultModel(purchaseOrderModels, references, pagination);
    }

    @PutMapping("/{id}")
    public PurchaseOrderSingleResponse update(
        @PathVariable long id,
        @RequestBody @Valid UpdatePurchaseOrderRequest updateRequest,
        HttpServletRequest request
    ) throws IOException {
        PurchaseOrder purchaseOrder = purchaseOrderWriteService.findById(id);
        String rawBody = RequestUtils.getBody(request);
        JsonNode node = jsonHelper.getJsonNode(rawBody, null);
        purchaseOrderWriteService.update(purchaseOrder, node, updateRequest);
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @PostMapping("{id}/cancellation")
    public PurchaseOrderSingleResponse cancelPurchaseOrder(
        @PathVariable("id") long id,
        @RequestBody PurchaseOrderCancelRequestModel model
    ) throws IOException {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));

        if(model.getReasonDetail().length() > 255){
            throw new NotFoundException("Nội dung lý do hủy tối đa 255 ký tự!");
        }
        purchaseOrder.cancelByAdmin(model.getReason(), model.getReasonDetail());
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @PostMapping("{id}/confirm")
    public PurchaseOrderSingleResponse confirmPurchaseOrder(@PathVariable("id") long id) throws IOException{
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));

        List<Long> variantIds = new ArrayList<>();
        purchaseOrder.getLineItems().stream().forEach(li -> variantIds.add(li.getVariantId()));

        List<Mk360Variant> mk360Variants = mk360VariantRepository.findByMkVariantIdIn(variantIds).orElse(new ArrayList<>());
        List<Long> mk360VariantIds = mk360Variants.stream().map(Mk360Variant::getMkVariantId).collect(Collectors.toList());
        List<VariantDto> purchaseOrderVariants = variantProjectionRepository.findByIdIn(variantIds, VariantDto.class);
        List<VariantDto> variant360NotExist = purchaseOrderVariants.stream().filter(v -> !mk360VariantIds.contains(v.getId())).collect(Collectors.toList());
        if (variant360NotExist.size() > 0) {
            List<String> variantSkusNotExist = variant360NotExist.stream().map(VariantDto::getSku).collect(Collectors.toList());
            throw new DomainValidateException("Sản phẩm " +
                String.join(", ", variantSkusNotExist) +
                " không tồn tại trên hệ thống 360");
        }

        purchaseOrder.confirmPurchaseOrder();
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @PostMapping("{id}/reject")
    public PurchaseOrderSingleResponse rejectPurchaseOrder(@PathVariable("id") long id) throws IOException{
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        purchaseOrder.rejectPurchaseOrder();
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @PostMapping("{id}/complete")
    public PurchaseOrderSingleResponse completePurchaseOrder(@PathVariable("id") long id) throws IOException{
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        purchaseOrder.deliverPurchaseOrder();
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);

        PurchaseOrder purchaseOrderNew = purchaseOrderRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        purchaseOrderNew.completePurchaseOrder();
        purchaseOrderValidator.validate(purchaseOrderNew);
        purchaseOrderWriteService.save(purchaseOrderNew);

        PurchaseOrderResponse response = mapper.map(purchaseOrderNew, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrderNew);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @GetMapping("{id}")
    public PurchaseOrderSingleResponse getPurchaseOrderById(@PathVariable("id") long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
        return new PurchaseOrderSingleResponse(response, reference);
    }

    @GetMapping("{id}/action_log")
    public PurchaseOrderActionLogResultModel purchaseOrderActionLog(@PathVariable("id") long id){
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id);
        if(purchaseOrder.isEmpty()){
            throw new NotFoundException("Không tìm thấy đơn mua");
        }
        List<PurchaseOrderLog> purchaseOrderLogs = purchaseOrderLogRepository.findAllByPurchaseOrderIdOrderByIdAsc(id);
        List<PurchaseOrderActionLogModel> purchaseOrderActionLogModels = mapper.mapAsList(purchaseOrderLogs, PurchaseOrderActionLogModel.class);
        PurchaseOrderActionLogResultModel response = new PurchaseOrderActionLogResultModel();
        response.setActionLogs(purchaseOrderActionLogModels);

        List<Long> userIds = purchaseOrderActionLogModels.stream()
            .map(PurchaseOrderActionLogModel::getActorId)
            .collect(Collectors.toList());
        List<Long> idsUser = userIds.stream().distinct().collect(Collectors.toList());
        List<User> userList = userRepository.findAllById(idsUser);

        List<PurchaseOrderActionLogReferenceModel.User> users = mapper.mapAsList(userList, PurchaseOrderActionLogReferenceModel.User.class);
        PurchaseOrderActionLogReferenceModel reference = new PurchaseOrderActionLogReferenceModel();
        reference.setUsers(users);
        response.setReference(reference);
        return response;
    }

    @PostMapping("{id}/delete")
    public PurchaseOrderSingleResponse deletePurchaseOrder(@PathVariable("id") long id) throws IOException{
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        if(purchaseOrder.getDeleted().booleanValue()){
            throw new NotFoundException("Đơn mua đã bị xóa!");
        }
        purchaseOrder.deletePurchaseOrder();
        purchaseOrderValidator.validate(purchaseOrder);
        purchaseOrderWriteService.save(purchaseOrder);
        PurchaseOrderResponse response = mapper.map(purchaseOrder, PurchaseOrderResponse.class);
        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);

        return new PurchaseOrderSingleResponse(response, reference);
    }

    @GetMapping("{id}/pdf")
    public PurchaseOrderPdfModel exportPDFPurchaseOrder(@PathVariable("id") long id) throws IOException{
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Đơn mua không tồn tại"));
        PurchaseOrderModel purchaseOrderModel = mapper.map(purchaseOrder, PurchaseOrderModel.class);
        String vendorName = purchaseOrderModel.getVendorAddress().getLabel() + ", " + purchaseOrderModel.getVendorAddress().getLine1();

        PurchaseOrderReferenceResponse reference = purchaseOrderReadService.getReference(purchaseOrder);
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

        String content = purchaseOrderReadService.resolveTemplate("purchaseOrder/purchaseOrder.html", purchaseOrders);

        ConvertModel model = new ConvertModel(content);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity request = new HttpEntity(model, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(
            converterUrl,
            HttpMethod.POST,
            request,
            byte[].class
        );

        byte[] barray = response.getBody();
//        MediaS3DTO s3FileDto = cloudStorageService.generateMediaS3DTO(
//            barray,
//            String.valueOf(new Date().getTime()) + ".pdf",
//            ""
//        );
//        String uri = cloudStorageService.upload(s3FileDto);

        return new PurchaseOrderPdfModel(barray);
    }

    @GetMapping("pdf")
    public PurchaseOrderPdfModel batchExportPDFPurchaseOrder(PurchaseOrderExportPDFModel model) throws IOException {
        byte[] result = purchaseOrderReadService.batchExportPdf(model.getIds());
        return new PurchaseOrderPdfModel(result);
    }
}
