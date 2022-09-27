package vn.sapo.vm.purchaseorder;

public class PurchaseOrderSingleResponse {
    private final PurchaseOrderResponse purchaseOrder;
    private final PurchaseOrderReferenceResponse reference;

    public PurchaseOrderSingleResponse(PurchaseOrderResponse purchaseOrder,
                                       PurchaseOrderReferenceResponse reference) {
        this.purchaseOrder = purchaseOrder;
        this.reference = reference;
    }

    public PurchaseOrderResponse getPurchaseOrder() {
        return purchaseOrder;
    }

    public PurchaseOrderReferenceResponse getReference() {
        return reference;
    }
}
