package vn.sapo.vm.purchaseorder;

public class PurchaseOrderPdfModel {
    private byte[] bytes;

    public PurchaseOrderPdfModel(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
