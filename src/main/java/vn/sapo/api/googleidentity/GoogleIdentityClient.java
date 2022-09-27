package vn.sapo.api.googleidentity;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vn.sapo.api.googleidentity.dto.GoogleIdTokenPayloadModel;
import vn.sapo.api.googleidentity.dto.GoogleTokenInformationModel;
import vn.sapo.config.GoogleIdentityClientConfiguration;
import vn.sapo.exception.UnauthorizedException;

import java.util.Collections;

@Component
public class GoogleIdentityClient {
    private final GoogleIdentityClientConfiguration configuration;
    private final GoogleApiClient googleApiClient;

    public GoogleIdentityClient(GoogleIdentityClientConfiguration configuration,
                                GoogleApiClient googleApiClient) {
        this.configuration = configuration;
        this.googleApiClient = googleApiClient;
    }

    public GoogleTokenInformationModel  getUser(String idTokenString, String userAgent) throws Exception {
        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
            .setAudience(Collections.singletonList(getGoogleIdentityClientId(userAgent)))
            .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new UnauthorizedException("Invalid ID token.");
        }
        GoogleIdToken.Payload payload = idToken.getPayload();
        GoogleTokenInformationModel tokenInfoModel = new GoogleTokenInformationModel();
        tokenInfoModel.setEmail(payload.getEmail());
        tokenInfoModel.setAvatarUri((String) payload.get("picture"));
        tokenInfoModel.setName((String) payload.get("name"));
        tokenInfoModel.setUserId(payload.getSubject());
        return tokenInfoModel;
    }

    private String getGoogleIdentityClientId(String userAgent) {
        GoogleIdentityClientConfiguration.GoogleIdentityClient identityClient = configuration.getIdentities()
            .stream()
            .filter(client -> StringUtils.contains(userAgent, client.getClientType()))
            .findFirst().orElse(null);
        if (identityClient != null) {
            return identityClient.getClientId();
        }

        return configuration.getIdentities()
            .stream()
            .filter(googleIdentityClient -> StringUtils.equals(googleIdentityClient.getClientType(), "web"))
            .map(GoogleIdentityClientConfiguration.GoogleIdentityClient::getClientId)
            .findFirst().orElse("");
    }

    public GoogleTokenInformationModel getUser(String idTokenString) throws Exception {
        try {
            GoogleIdTokenPayloadModel payload = googleApiClient.verifyIdToken(idTokenString);
            GoogleTokenInformationModel tokenInfoModel = new GoogleTokenInformationModel();
            tokenInfoModel.setEmail(payload.getEmail());
            tokenInfoModel.setAvatarUri(payload.getPicture());
            tokenInfoModel.setName(payload.getName());
            tokenInfoModel.setUserId(payload.getSub());
            return tokenInfoModel;
        } catch (Exception ex) {
            if (ex instanceof FeignException) {
                int status = ((FeignException) ex).status();
                if (status == HttpStatus.BAD_REQUEST.value()) {
                    throw new UnauthorizedException("Invalid ID token.");
                }
            }
            throw new RuntimeException("An unexpected exception happen, please try again later");
        }
    }
}
