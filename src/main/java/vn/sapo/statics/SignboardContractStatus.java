package vn.sapo.statics;

public enum SignboardContractStatus {
    DRAFT("Chờ duyệt"),
    CANCELLED("Đã hủy"),
    ACTIVATED("Đã duyệt"),
    DEACTIVATED("Đã dừng"),
    EXPIRED("Đã hết hạn");

    private final String description;

    SignboardContractStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
