package vn.sapo.api.sapocore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.sapo.api.sapocore.model.location.SpLocationFilterResult;
import vn.sapo.api.sapocore.model.pricelist.SpPriceListFilterResponse;
import vn.sapo.api.sapocore.model.product.*;
import vn.sapo.api.sapocore.model.tenant.SpGetTenantResult;
import vn.sapo.api.sapocore.model.token.SpGetTokenRequest;
import vn.sapo.api.sapocore.model.token.SpGetTokenResponse;
import vn.sapo.api.sapocore.model.webhook.SpCreateWebhookRequest;
import vn.sapo.api.sapocore.model.webhook.SpCreateWebhookResult;
import vn.sapo.api.sapocore.model.webhook.SpCreateWebhookResultWrapper;
import vn.sapo.domain.SapoTenant;

@Component
public class SapoCoreApiClient {
//    @Autowired
//    @Qualifier("sapo-core")
    private RestTemplate restTemplate;

//    @Autowired
    private Environment env;
    private Logger sapoCoreLogger = LoggerFactory.getLogger("ExternalAuthentiction-SapoCore");

    public SapoCoreApiClient(
        @Qualifier("sapo-core") RestTemplate restTemplate,
        Environment env
    ){
        this.env = env;
        this.restTemplate = restTemplate;
    }

    private HttpEntity initRequest(String accessToken, Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Sapo-Access-Token", accessToken);
        headers.add("Content-Type", "application/json");
        HttpEntity request = new HttpEntity(body, headers);
        return request;
    }
    private HttpEntity initLocatedRequest(String accessToken, int locationId, Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Sapo-Access-Token", accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("X-Sapo-LocationId", String.valueOf(locationId));
        HttpEntity request = new HttpEntity(body, headers);
        return request;
    }
    private void addHeaderLocation(HttpEntity request, int locationId){
        request.getHeaders().add("X-Sapo-LocationId", String.valueOf(locationId));
        return;
    }
    private String getUri(SapoTenant tenant, String path){
        String uri = String.format("https://%s/%s", tenant.getDomain(), path);
        return uri;
    }

    public SpGetTokenResponse getAccessToken(String domain, String code) throws Exception{
        String uri = String.format("https://%s/admin/oauth/access_token", domain);
        SpGetTokenRequest getTokenRequest = new SpGetTokenRequest();
        getTokenRequest.setCode(code);
        getTokenRequest.setClientId(env.getProperty("external.sapo-core.application.client-id"));
        getTokenRequest.setClientSecret(env.getProperty("external.sapo-core.application.client-secret"));

        sapoCoreLogger.info(String.format("ClientId: %s", env.getProperty("external.sapo-core.application.client-id")));
        sapoCoreLogger.info(String.format("ClientSecret: %s", env.getProperty("external.sapo-core.application.client-secret")));
//        HttpEntity<SpGetTokenRequest> request = new HttpEntity(getTokenRequest);
//        ResponseEntity<SpGetTokenResponse> response = restTemplate
//            .exchange(uri, HttpMethod.POST, request, SpGetTokenResponse.class);

        SpGetTokenResponse result = restTemplate.postForObject(uri, getTokenRequest, SpGetTokenResponse.class);
        return result;
    }
    public SpGetTenantResult getTenant(String accessToken, String domain){
        String uri = String.format("https://%s/admin/tenant.json", domain);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Sapo-Access-Token", accessToken);
//        HttpEntity request = new HttpEntity(null, headers);

        HttpEntity request = initRequest(accessToken, null);

        ResponseEntity<SpGetTenantResult> response = restTemplate
            .exchange(uri, HttpMethod.GET, request, SpGetTenantResult.class);
        SpGetTenantResult result = response.getBody();

        return result;
    }

    public SpCreateVariantResponse createVariant(SapoTenant tenant, int locationId, int productId, SpCreateVariantRequest requestModel){
        String path = String.format("admin/products/%d/variants.json", productId);
        String uri = getUri(tenant, path);

        HttpEntity request = initLocatedRequest(tenant.getAccessToken(), locationId, new SpWrapperCreateVariantRequest(requestModel));

        ResponseEntity<SpWrapperCreateVariantResponse> response = restTemplate
            .exchange(uri, HttpMethod.POST, request, SpWrapperCreateVariantResponse.class);
        SpCreateVariantResponse result = response.getBody().getVariant();
        return result;
    }

    public SpLocationFilterResult getAllLocations(SapoTenant tenant){
        String uri = getUri(tenant, "admin/locations.json");

        HttpEntity request = initRequest(tenant.getAccessToken(), null);

        ResponseEntity<SpLocationFilterResult> response = restTemplate
            .exchange(uri, HttpMethod.GET, request, SpLocationFilterResult.class);
        SpLocationFilterResult result = response.getBody();
        return result;
    }

    public SpProductResponse createProduct(SapoTenant tenant, int locationId, SpCreateProductRequest product){
        String uri = getUri(tenant, "admin/products.json");

        HttpEntity request = initLocatedRequest(tenant.getAccessToken(), locationId, new SpWrapperCreateProductRequest(product));

        ResponseEntity<SpWrapperProductResponse> response = restTemplate
            .exchange(uri, HttpMethod.POST, request, SpWrapperProductResponse.class);
        SpProductResponse result = response.getBody().getProduct();
        return result;

//        ResponseEntity<ProductResponse> response = restTemplate
//            .exchange(uri, HttpMethod.POST, request, SpProductResponse.class);
//        Object result = response.getBody();
//
//        return null;

    }

    public SpProductResponse getProduct(SapoTenant tenant, int productId){
        String uri = getUri(tenant, "admin/products/{productId}.json");

        HttpEntity request = initRequest(tenant.getAccessToken(), null);

        ResponseEntity<SpWrapperProductResponse> response = restTemplate
            .exchange(uri, HttpMethod.GET, request, SpWrapperProductResponse.class, productId);
        SpProductResponse result = response.getBody().getProduct();
        return result;
    }

    public SpCreateImageResponse createProductImage(SapoTenant tenant, int productId, SpCreateImageRequest imageModel){
        String uri = getUri(tenant, "admin/products/{productId}/images.json");
        HttpEntity request = initRequest(tenant.getAccessToken(), new SpWrapperCreateImageRequest(imageModel));

        ResponseEntity<SpWrapperCreateImageResponse> response = restTemplate
            .exchange(uri, HttpMethod.POST, request, SpWrapperCreateImageResponse.class, productId);
        SpCreateImageResponse result = response.getBody().getImage();
        return result;
    }

    public SpUpdateVariantResponse updateVariant(SapoTenant tenant, int productId, int variantId, SpUpdateVariantImageRequest variant){
        String uri = getUri(tenant, "admin/products/{productId}/variants/{variantId}.json");
        HttpEntity request = initRequest(tenant.getAccessToken(), new SpWrapperUpdateVariantImageRequest(variant));

        ResponseEntity<SpWrapperUpdateVariantResponse> response = restTemplate
            .exchange(uri, HttpMethod.PUT, request, SpWrapperUpdateVariantResponse.class, productId, variantId);
        SpUpdateVariantResponse result = response.getBody().getVariant();
        return result;
    }

    public SpPriceListFilterResponse getAllPriceList(SapoTenant tenant){
        String uri = getUri(tenant, "admin/price_lists.json?limit={limit}");

        HttpEntity request = initRequest(tenant.getAccessToken(), null);

        ResponseEntity<SpPriceListFilterResponse> response = restTemplate
            .exchange(uri, HttpMethod.GET, request, SpPriceListFilterResponse.class, 200);
        SpPriceListFilterResponse result = response.getBody();
        return result;
    }

    public SpCreateWebhookResult addWebhook(SapoTenant tenant, SpCreateWebhookRequest model){
        String uri = getUri(tenant, "admin/webhooks.json");

        HttpEntity request = initRequest(tenant.getAccessToken(), model);
        ResponseEntity<SpCreateWebhookResultWrapper> response = restTemplate
            .exchange(uri, HttpMethod.POST, request, SpCreateWebhookResultWrapper.class);
        SpCreateWebhookResult result = response.getBody().getWebhook();
        return result;
    }
}
