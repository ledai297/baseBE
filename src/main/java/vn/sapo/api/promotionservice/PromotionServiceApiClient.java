package vn.sapo.api.promotionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.sapo.api.promotionservice.dto.CheckPromotionAvailableModel;
import vn.sapo.api.promotionservice.dto.PromotionDecreasedUsageModel;
import vn.sapo.api.promotionservice.dto.PromotionIncreasedUsageModel;
import vn.sapo.api.promotionservice.dto.PromotionOrderRewardModel;

@FeignClient(
    name = "promotion-service",
    url = "${feign.client.config.interservice.url}"
)
public interface PromotionServiceApiClient {

    @PostMapping("/api/promotion_programs/usage_increments")
    void increase(@RequestBody PromotionIncreasedUsageModel usageModel);

    @PostMapping("/api/promotion_programs/usage_decrements")
    void decrease(@RequestBody PromotionDecreasedUsageModel usageModel);

    @PostMapping("/api/promotion_programs/availabilities/checking")
    PromotionOrderRewardModel checkPromotionAvailable(@RequestBody CheckPromotionAvailableModel model);
}
