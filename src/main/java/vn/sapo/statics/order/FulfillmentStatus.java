package vn.sapo.statics.order;

public enum FulfillmentStatus {
    DRAFT, //vừa tạo
    PACKED, //Vendor xác nhận
    PICKED, //shipper đã lấy
    RECEIVED, //shipper đã giao
    CANCELLED, //đã hủy
    REJECTED
}
