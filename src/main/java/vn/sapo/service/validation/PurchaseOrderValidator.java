package vn.sapo.service.validation;

import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;
import vn.sapo.domain.purchaseorder.Address;
import vn.sapo.domain.purchaseorder.PurchaseOrder;
import vn.sapo.domain.vendor.Vendor;
import vn.sapo.exception.DomainValidateException;
import vn.sapo.exception.FormValidateException;
import vn.sapo.repository.WarehouseRepository;
import vn.sapo.repository.vendor.VendorRepository;
import vn.sapo.utils.ValidationUtils;

import java.util.List;

@Component
public class PurchaseOrderValidator {

    private final WarehouseRepository warehouseRepository;
    private final VendorRepository vendorRepository;
    private final MapperFacade mapper;

    public PurchaseOrderValidator(WarehouseRepository warehouseRepository,
                                  VendorRepository vendorRepository,
                                  MapperFacade mapper) {
        this.warehouseRepository = warehouseRepository;
        this.vendorRepository = vendorRepository;
        this.mapper = mapper;
    }

    public void validate(PurchaseOrder purchaseOrder) {
        if (!purchaseOrder.isCreated() && !purchaseOrder.isModified()) return;
        ValidationUtils.validateObject(purchaseOrder);
        if (purchaseOrder.getPurchaseDate().after(purchaseOrder.getExpectedDeliveryDate())) {
            throw new DomainValidateException("Thời gian Ngày giao hàng mong muốn không được nhỏ hơn Ngày mua hàng");
        }
        if (!warehouseRepository.existsById(purchaseOrder.getWarehouseId())) {
            throw new DomainValidateException("Kho nhập không tồn tại");
        }
//        validateVendorAddress(purchaseOrder.getVendorId(), purchaseOrder.getVendorAddress());
    }

    private void validateVendorAddress(long vendorId, Address vendorAddress) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new FormValidateException("Nhà cung cấp không tồn tại"));

        List<Address> vendorAddressesChecker = mapper.mapAsList(vendor.getWarehouses(), Address.class);
        if (vendorAddressesChecker.stream().noneMatch(address -> address.equals(vendorAddress))) {
            throw new DomainValidateException("Địa chỉ kho nhà cung cấp không tồn tại");
        }
    }
}
