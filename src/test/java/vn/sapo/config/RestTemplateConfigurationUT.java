package vn.sapo.config;

import com.fasterxml.jackson.databind.*;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import vn.sapo.api.sapocore.model.product.SpCreateProductRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestTemplateConfigurationUT {
    @Test
    public void serializeObjectWithRoot(){
        ObjectMapper objectMapper = getObjectMapperForSapoCoreApiClient();

        SpCreateProductRequest spProduct = new SpCreateProductRequest();

        try{
            String json = objectMapper.writeValueAsString(spProduct);
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false);
        }
    }
    @Test
    public void deserializeJsonWithRoot(){
        ObjectMapper objectMapper = getObjectMapperForSapoCoreApiClient();

        SpCreateProductRequest spProduct = new SpCreateProductRequest();
        spProduct.setName("name");

        try{
            String json = objectMapper.writeValueAsString(spProduct);
            SpCreateProductRequest deserializedObject = objectMapper.readValue(json, SpCreateProductRequest.class);
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false);
        }
    }
    private ObjectMapper getObjectMapperForSapoCoreApiClient(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        return objectMapper;
    }

}
