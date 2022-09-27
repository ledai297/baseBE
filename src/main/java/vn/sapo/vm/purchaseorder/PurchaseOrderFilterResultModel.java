package vn.sapo.vm.purchaseorder;

import vn.sapo.vm.pagination.BasePaginationFilterResultModel;
import vn.sapo.vm.pagination.Pagination;

import java.util.List;

public class PurchaseOrderFilterResultModel extends BasePaginationFilterResultModel {
    private List<PurchaseOrderResponse> purchaseOrders;
    private PurchaseOrderReferenceResponse reference;


    public PurchaseOrderFilterResultModel(){

    }

    public PurchaseOrderFilterResultModel(List<PurchaseOrderResponse> purchaseOrders, Pagination pagination
    ){
        this.purchaseOrders = purchaseOrders;
        this.setPagination(pagination);
    }

    public PurchaseOrderFilterResultModel(List<PurchaseOrderResponse> purchaseOrders, PurchaseOrderReferenceResponse reference, Pagination pagination){
        this.purchaseOrders = purchaseOrders;
        this.reference = reference;
        this.setPagination(pagination);
    }

    public List<PurchaseOrderResponse> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrderResponse> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrderReferenceResponse getReference() {
        return reference;
    }

    public void setReference(PurchaseOrderReferenceResponse reference) {
        this.reference = reference;
    }
}
