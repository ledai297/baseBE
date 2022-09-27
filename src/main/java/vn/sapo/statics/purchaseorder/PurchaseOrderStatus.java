package vn.sapo.statics.purchaseorder;

public enum PurchaseOrderStatus {
    DRAFT("Chờ xác nhận"),
    REJECTED("Đã từ chối"),
    CONFIRMED("Đã xác nhận"),
    DELIVERING("Đang vận chuyển"),
    CANCELLED("Đã hủy"),
    COMPLETED("Đã hoàn tất"),
    DELETED("Đã xóa");

    private final String description;

    PurchaseOrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
