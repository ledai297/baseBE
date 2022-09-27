package vn.sapo.utils;

import org.apache.commons.codec.digest.HmacUtils;
import org.eclipse.jetty.util.UrlEncoded;
import org.junit.Test;

import java.util.Base64;

import static org.apache.commons.codec.digest.HmacAlgorithms.HMAC_SHA_256;

public class HmacUT {
    @Test
    public void hmac(){
        String secret = "4ac2d7e6a4214ec987e8f9d76729b2e3";
        String code = "21A0Jh";
        String tenant="trainv01.mysapo.vn";
        String inputHmac = "UssLteYHCH%2bkA%2bZqidQgKLMk9MGToNnCj%2fb4ZAMMwJE%3d";
        String inputHmacUrlDecoded = UrlEncoded.decodeString(inputHmac);
        String timestamp = "1605167607";

        String inputText = String.format(
            "code=%s&tenant=%s&timestamp=%s",
            code,
            tenant,
            timestamp
        );

        String calculatedHmac = TextUtils.hmac256(inputText, secret);

        boolean matched = inputHmacUrlDecoded.equals(calculatedHmac);
        assert (matched);
    }

    @Test
    public void hmacSapoCoreWebhook(){
        String secret = "4ac2d7e6a4214ec987e8f9d76729b2e3";
        String inputHmac = "dmTwjommzSy1J1ciN2H4K9zXKTYrLhM89Lud0Fi1PhI=";

        String inputText = "{\"id\":34007220,\"tenant_id\":368386,\"created_on\":\"2020-11-12T09:07:26Z\",\"modified_on\":\"2020-11-12T09:07:26Z\",\"status\":\"active\",\"brand\":null,\"description\":null,\"image_path\":null,\"image_name\":null,\"name\":\"test xem sao\",\"opt1\":\"Kích thước\",\"opt2\":null,\"opt3\":null,\"category\":null,\"category_code\":null,\"tags\":\"\",\"product_type\":\"normal\",\"variants\":[{\"id\":50938179,\"tenant_id\":368386,\"location_id\":374616,\"created_on\":\"2020-11-12T09:07:26Z\",\"modified_on\":\"2020-11-12T09:07:26Z\",\"category_id\":null,\"brand_id\":null,\"product_id\":34007220,\"composite\":false,\"init_price\":0.0,\"init_stock\":0.0,\"variant_retail_price\":0.0,\"variant_whole_price\":0.0,\"variant_import_price\":0.0,\"image_id\":null,\"name\":\"test xem sao\",\"opt1\":\"Mặc định\",\"opt2\":null,\"opt3\":null,\"product_name\":\"test xem sao\",\"product_status\":null,\"status\":\"active\",\"sellable\":true,\"sku\":\"PVN01\",\"barcode\":\"PVN01\",\"taxable\":true,\"weight_value\":0.0,\"weight_unit\":\"g\",\"unit\":null,\"packsize\":false,\"packsize_quantity\":null,\"packsize_root_id\":null,\"product_type\":\"normal\",\"variant_prices\":[{\"id\":155212126,\"value\":0.0,\"name\":\"Giá nhập\",\"price_list_id\":1122283,\"price_list\":{\"id\":1122283,\"tenant_id\":368386,\"created_on\":\"2020-11-12T00:51:11Z\",\"modified_on\":\"2020-11-12T00:51:11Z\",\"code\":\"GIANHAP\",\"name\":\"Giá nhập\",\"is_cost\":true,\"currency_id\":368377,\"status\":\"default\",\"init\":true}},{\"id\":155212127,\"value\":0.0,\"name\":\"Giá bán buôn\",\"price_list_id\":1122282,\"price_list\":{\"id\":1122282,\"tenant_id\":368386,\"created_on\":\"2020-11-12T00:51:11Z\",\"modified_on\":\"2020-11-12T00:51:11Z\",\"code\":\"BANBUON\",\"name\":\"Giá bán buôn\",\"is_cost\":false,\"currency_id\":368377,\"status\":\"active\",\"init\":true}},{\"id\":155212128,\"value\":0.0,\"name\":\"Giá bán lẻ\",\"price_list_id\":1122284,\"price_list\":{\"id\":1122284,\"tenant_id\":368386,\"created_on\":\"2020-11-12T00:51:11Z\",\"modified_on\":\"2020-11-12T00:51:11Z\",\"code\":\"BANLE\",\"name\":\"Giá bán lẻ\",\"is_cost\":false,\"currency_id\":368377,\"status\":\"default\",\"init\":true}}],\"inventories\":[{\"location_id\":374616,\"variant_id\":50938179,\"mac\":0,\"amount\":0,\"on_hand\":0,\"available\":0,\"committed\":0,\"incoming\":0,\"onway\":0,\"reorder_point\":0,\"name\":null,\"min_value\":0,\"max_value\":0,\"bin_location\":null}],\"images\":null,\"composite_items\":null}],\"options\":[{\"id\":33300466,\"name\":\"Kích thước\",\"position\":1,\"values\":[\"Mặc định\"]}],\"images\":[]}";

        String calculatedHmac = TextUtils.hmac256(inputText, secret);

        boolean matched = inputHmac.equals(calculatedHmac);
        assert (matched);
    }
}
