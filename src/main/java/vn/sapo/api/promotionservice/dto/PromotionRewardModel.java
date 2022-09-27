package vn.sapo.api.promotionservice.dto;

import vn.sapo.statics.order.PromotionRewardType;

public class PromotionRewardModel {
    private PromotionRewardType type;
    private String value;

    public PromotionRewardType getType() {
        return type;
    }

    public void setType(PromotionRewardType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
