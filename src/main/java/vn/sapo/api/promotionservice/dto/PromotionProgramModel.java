package vn.sapo.api.promotionservice.dto;

import java.util.Date;

public class PromotionProgramModel {
    private long id;
    private String code;
    private String status;
    private String type;
    private Date startedDate;
    private Date expiredDate;
    private Long usageLimit;
    private Long usageLimitPerMerchant;
    private Boolean onePerMerchant;
    private String rewardType;
    private Double discountMaximumAmount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Long getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Long usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Long getUsageLimitPerMerchant() {
        return usageLimitPerMerchant;
    }

    public void setUsageLimitPerMerchant(Long usageLimitPerMerchant) {
        this.usageLimitPerMerchant = usageLimitPerMerchant;
    }

    public Boolean getOnePerMerchant() {
        return onePerMerchant;
    }

    public void setOnePerMerchant(Boolean onePerMerchant) {
        this.onePerMerchant = onePerMerchant;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Double getDiscountMaximumAmount() {
        return discountMaximumAmount;
    }

    public void setDiscountMaximumAmount(Double discountMaximumAmount) {
        this.discountMaximumAmount = discountMaximumAmount;
    }
}
