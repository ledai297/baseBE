package vn.sapo.vm.purchaseorder;


import java.util.List;

public class PurchaseOrderActionLogResultModel {
    List<PurchaseOrderActionLogModel> actionLogs;
    PurchaseOrderActionLogReferenceModel reference;

    public List<PurchaseOrderActionLogModel> getActionLogs() {
        return actionLogs;
    }

    public void setActionLogs(List<PurchaseOrderActionLogModel> actionLogs) {
        this.actionLogs = actionLogs;
    }

    public PurchaseOrderActionLogReferenceModel getReference() {
        return reference;
    }

    public void setReference(PurchaseOrderActionLogReferenceModel reference) {
        this.reference = reference;
    }
}
