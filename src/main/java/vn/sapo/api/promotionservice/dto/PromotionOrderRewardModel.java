package vn.sapo.api.promotionservice.dto;

public class PromotionOrderRewardModel {
    private long programId;
    private String programCode;
    private boolean matched;
    private Object conditionReward;
    private PromotionRewardModel reward;
    private String type;
    private Double discountMaximumAmount;

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public Object getConditionReward() {
        return conditionReward;
    }

    public void setConditionReward(Object conditionReward) {
        this.conditionReward = conditionReward;
    }

    public PromotionRewardModel getReward() {
        return reward;
    }

    public void setReward(PromotionRewardModel reward) {
        this.reward = reward;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscountMaximumAmount() {
        return discountMaximumAmount;
    }

    public void setDiscountMaximumAmount(Double discountMaximumAmount) {
        this.discountMaximumAmount = discountMaximumAmount;
    }
}
