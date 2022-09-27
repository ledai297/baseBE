package vn.sapo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.config.Constants;
import vn.sapo.domain.abstractions.BaseEntity;
import vn.sapo.domain.purchaseorder.*;
import vn.sapo.domain.vendor.Vendor;
import vn.sapo.exception.FormValidateException;
import vn.sapo.exception.NotFoundException;
import vn.sapo.repository.purchaseorder.PurchaseOrderIdProvider;
import vn.sapo.repository.purchaseorder.PurchaseOrderLogRepository;
import vn.sapo.repository.purchaseorder.PurchaseOrderRepository;
import vn.sapo.repository.vendor.VendorRepository;
import vn.sapo.security.SecurityUtils;
import vn.sapo.statics.purchaseorder.PurchaseOrderLogAction;
import vn.sapo.vm.purchaseorder.*;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderWriteService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;
    private final PurchaseOrderIdProvider purchaseOrderIdProvider;
    private final PurchaseOrderLogRepository purchaseOrderLogRepository;
    private final ObjectMapper objectMapper;
    private final JsonHelper jsonHelper;

    public PurchaseOrderWriteService(
        PurchaseOrderRepository purchaseOrderRepository,
        VendorRepository vendorRepository,
        PurchaseOrderIdProvider purchaseOrderIdProvider,
        PurchaseOrderLogRepository purchaseOrderLogRepository,
        @Qualifier("snake_case") ObjectMapper objectMapper,
        JsonHelper jsonHelper
    ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.vendorRepository = vendorRepository;
        this.purchaseOrderIdProvider = purchaseOrderIdProvider;
        this.purchaseOrderLogRepository = purchaseOrderLogRepository;
        this.objectMapper = objectMapper;
        this.jsonHelper = jsonHelper;
    }

    public PurchaseOrder create(CreatePurchaseOrderRequest createRequest) {
        long id = purchaseOrderIdProvider.getNextPurchaseOrderId();
        String purchaseOrderCode = buildPurchaseOrderCode(createRequest.getVendorId());
        PurchaseOrder purchaseOrder = new PurchaseOrder(
            id,
            purchaseOrderCode,
            createRequest.getVendorId(),
            createRequest.getWarehouseId()
        );
        purchaseOrder.setVendorAddress(new Address(
            createRequest.getVendorAddress().getProvinceId(),
            createRequest.getVendorAddress().getDistrictId(),
            createRequest.getVendorAddress().getWardId(),
            createRequest.getVendorAddress().getLabel(),
            createRequest.getVendorAddress().getPhoneNumber(),
            createRequest.getVendorAddress().getLine1(),
            createRequest.getVendorAddress().getLine2()
        ));

        List<Long> lineItemIds = purchaseOrderIdProvider.getNextPurchaseOrderLineItemIds(
            createRequest.getLineItems().size()
        );
        Queue<Long> lineItemIdQueue = new LinkedList<>(lineItemIds);

        for (CreatePurchaseOrderLineItemRequest lineItemRequest : createRequest.getLineItems()) {
            purchaseOrder.addLineItem(
                lineItemIdQueue.poll(),
                lineItemRequest.getVariantId(),
                lineItemRequest.getQuantity(),
                lineItemRequest.getTaxRate(),
                lineItemRequest.getPrice()
            );
        }
        purchaseOrder.setPurchaseDate(createRequest.getPurchaseDate());
        purchaseOrder.setExpectedDeliveryDate(createRequest.getExpectedDeliveryDate());
        purchaseOrder.setNote((createRequest.getNote()));
        return purchaseOrder;
    }

    private String buildPurchaseOrderCode(long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new FormValidateException("Nhà cung cấp không tồn tại"));
        long currentSequence = purchaseOrderRepository.countByVendorId(vendorId);
        return String.format("PO_%s_%d", vendor.getCode(), currentSequence + 1);
    }

    @Transactional
    public void save(PurchaseOrder purchaseOrder) throws IOException {
        assert purchaseOrder != null && purchaseOrder.getId() != null;
        if (!purchaseOrder.isCreated() && !purchaseOrder.isModified()) return;
        purchaseOrderRepository.save(purchaseOrder);

        PurchaseOrderLog log = new PurchaseOrderLog();
        log.setPurchaseOrderId(purchaseOrder.getId());
        log.setVendorId(purchaseOrder.getVendorId());
        log.setWarehouseId(purchaseOrder.getWarehouseId());
        log.setAction(purchaseOrder.isCreated() ? PurchaseOrderLogAction.CREATE : PurchaseOrderLogAction.MODIFY);
        log.setActorId(SecurityUtils.getCurrentUserId().orElse(Constants.SYSTEM_ACCOUNT_ID));
        log.setCreatedAt(Instant.now());
        log.setDetail(objectMapper.writeValueAsString(purchaseOrder));
        log.setSchemaVersion(PurchaseOrderSchemaVersion.FIRST);
        purchaseOrderLogRepository.save(log);
    }

    public PurchaseOrder findById(long id) {
        return purchaseOrderRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy Đơn mua"));
    }

    public void update(
        PurchaseOrder purchaseOrder,
        JsonNode node,
        UpdatePurchaseOrderRequest updateRequest
    ) {
        if (jsonHelper.existField(node, "warehouse_id")) {
            purchaseOrder.setWarehouseId(updateRequest.getWarehouseId());
        }
        if (jsonHelper.existField(node, "purchase_date")) {
            purchaseOrder.setPurchaseDate(updateRequest.getPurchaseDate());
        }
        if (jsonHelper.existField(node, "expected_delivery_date")) {
            purchaseOrder.setExpectedDeliveryDate(updateRequest.getExpectedDeliveryDate());
        }
        if (jsonHelper.existField(node, "line_items")) {
            updateLineItem(purchaseOrder, node.path("line_items"), updateRequest);
        }
        if (jsonHelper.existField(node, "vendor_address")) {
            updateVendorAddress(purchaseOrder, node.path("vendor_address"), updateRequest);
        }
        if (jsonHelper.existField(node, "note")) {
            purchaseOrder.setNote(updateRequest.getNote());
        }
    }

    public void updateVendorAddress(PurchaseOrder purchaseOrder, JsonNode node, UpdatePurchaseOrderRequest updateRequest) {
        Address vendorAddress = purchaseOrder.getVendorAddress();
        UpdateAddressRequest addressRequest = updateRequest.getVendorAddress();
        Long provinceId = jsonHelper.existField(node, "province_id")
            ? addressRequest.getProvinceId()
            : vendorAddress == null ? null : vendorAddress.getProvinceId();
        Long districtId = jsonHelper.existField(node, "district_id")
            ? addressRequest.getDistrictId()
            : vendorAddress == null ? null : vendorAddress.getDistrictId();
        Long wardId = jsonHelper.existField(node, "ward_id")
            ? addressRequest.getWardId()
            : vendorAddress == null ? null : vendorAddress.getWardId();
        String line1 = jsonHelper.existField(node, "line1")
            ? addressRequest.getLine1()
            : vendorAddress == null ? null : vendorAddress.getLine1();
        String line2 = jsonHelper.existField(node, "line2")
            ? addressRequest.getLine2()
            : vendorAddress == null ? null : vendorAddress.getLine2();
        String label = jsonHelper.existField(node, "label")
            ? addressRequest.getLabel()
            : vendorAddress == null ? null : vendorAddress.getLabel();
        String phoneNumber = jsonHelper.existField(node, "phone_number")
            ? addressRequest.getPhoneNumber()
            : vendorAddress == null ? null : vendorAddress.getPhoneNumber();
        Address newAddress = new Address(
            provinceId,
            districtId,
            wardId,
            label,
            phoneNumber,
            line1,
            line2
        );
        purchaseOrder.setVendorAddress(newAddress);
    }

    public void updateLineItem(PurchaseOrder purchaseOrder, JsonNode node, UpdatePurchaseOrderRequest updateRequest) {
        List<Long> currentLineItemIds = purchaseOrder.getLineItems().stream()
            .map(BaseEntity::getId)
            .collect(Collectors.toList());
        List<Long> targetLineItemIds = updateRequest.getLineItems().stream()
            .map(UpdatePurchaseOrderLineItemRequest::getId)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        long newLineItemCount = updateRequest.getLineItems().stream()
            .filter(li -> li.getId() == null || !currentLineItemIds.contains(li.getId()))
            .count();
        List<Long> newLineItemIds = purchaseOrderIdProvider.getNextPurchaseOrderLineItemIds(
            (int) newLineItemCount
        );
        Queue<Long> newLineItemIdQueue = new LinkedList<>(newLineItemIds);

        List<Long> removedLineItemIds = currentLineItemIds.stream()
            .filter(itemId -> !targetLineItemIds.contains(itemId))
            .collect(Collectors.toList());

        for (Long removedLineItemId : removedLineItemIds)
            purchaseOrder.removeLineItem(removedLineItemId);

        List<JsonNode> lineItemNodes = IteratorUtils.toList(node.elements());
        int index = 0;
        for (JsonNode lineItemNode : lineItemNodes) {
            UpdatePurchaseOrderLineItemRequest lineItemRequest = updateRequest.getLineItems().get(index);
            Optional<PurchaseOrderLineItem> purchaseOrderLineItemOptional = purchaseOrder.getLineItems().stream()
                .filter(li -> li.getId().equals(lineItemRequest.getId()))
                .findFirst();

            if (purchaseOrderLineItemOptional.isPresent()) {
                PurchaseOrderLineItem lineItem = purchaseOrderLineItemOptional.get();
                if (jsonHelper.existField(lineItemNode, "quantity")) {
                    lineItem.setQuantity(lineItemRequest.getQuantity());
                }
                if (jsonHelper.existField(lineItemNode, "tax_rate")) {
                    lineItem.setTaxRate(lineItemRequest.getTaxRate());
                }
                if (jsonHelper.existField(lineItemNode, "price")) {
                    lineItem.setPrice(lineItemRequest.getPrice());
                }
                if (lineItem.isModified()) {
                    purchaseOrder.updateLineItem(lineItem);
                }
            } else {
                purchaseOrder.addLineItem(
                    newLineItemIdQueue.poll(),
                    lineItemRequest.getVariantId(),
                    lineItemRequest.getQuantity(),
                    lineItemRequest.getTaxRate(),
                    lineItemRequest.getPrice()
                );
            }
            index++;
        }
    }
}
