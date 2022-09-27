package vn.sapo.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.sapo.security.client.model.AuthorizeModel;
import vn.sapo.security.client.model.AuthorizeResultModel;

@FeignClient(
    name = "authorization",
    url = "${feign.client.config.authorization.url}",
    configuration = AuthorizationApiClientConfiguration.class
)
public interface AuthorizationApiClient {
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/authorize"
    )
    AuthorizeResultModel authorizeRequest(
        @RequestBody AuthorizeModel model
    );
}
