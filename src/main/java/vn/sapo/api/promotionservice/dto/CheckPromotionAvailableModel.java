package vn.sapo.api.promotionservice.dto;

public class CheckPromotionAvailableModel {
    private Long programId;
    private String programCode;
    private PromotionOrderModel order;

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public PromotionOrderModel getOrder() {
        return order;
    }

    public void setOrder(PromotionOrderModel order) {
        this.order = order;
    }
}
