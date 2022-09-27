package vn.sapo.api.googleidentity;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.sapo.api.googleidentity.dto.GoogleIdTokenPayloadModel;
import vn.sapo.config.FacebookApiClientConfiguration;

@FeignClient(
    name = "google",
    url = "https://oauth2.googleapis.com"
)
public interface GoogleApiClient {

    @GetMapping("/tokeninfo")
    GoogleIdTokenPayloadModel verifyIdToken(@RequestParam("id_token") String idToken);
}
