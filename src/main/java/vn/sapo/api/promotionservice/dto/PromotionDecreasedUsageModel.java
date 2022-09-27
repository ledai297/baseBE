package vn.sapo.api.promotionservice.dto;

import vn.sapo.statics.order.PromotionTargetType;

public class PromotionDecreasedUsageModel {
    private Long programId;
    private String programCode;
    private Long orderId;
    private PromotionTargetType targetType;
    private Long targetId;
    private Long merchantId;
    private Long actorId;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public PromotionTargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(PromotionTargetType targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
}
