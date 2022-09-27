package vn.sapo.vm.purchaseorder;

import org.hibernate.validator.constraints.Length;
import vn.sapo.exception.NotFoundException;
import vn.sapo.statics.order.OrderCancellationReason;
import vn.sapo.statics.purchaseorder.PurchaseOrderCancellationReason;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PurchaseOrderCancelRequestModel {
    @NotNull
    private PurchaseOrderCancellationReason reason;
    @Length(max = 999)
    private String reasonDetail;

    public PurchaseOrderCancellationReason getReason() {
        return reason;
    }

    public void setReason(PurchaseOrderCancellationReason reason) {
        this.reason = reason;
    }

    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail;
    }

}
