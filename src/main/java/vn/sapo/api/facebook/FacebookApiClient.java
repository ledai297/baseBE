package vn.sapo.api.facebook;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.sapo.api.facebook.dto.FbAccountModel;
import vn.sapo.config.FacebookApiClientConfiguration;

@FeignClient(
    name = "facebook",
    url = "https://graph.facebook.com",
    configuration = FacebookApiClientConfiguration.class
)
public interface FacebookApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    FbAccountModel getAccount(
        @RequestParam("access_token") String accessToken,
        @RequestParam("fields") String fields
    );
}
