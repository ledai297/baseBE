package vn.sapo.statics;

import java.util.HashMap;
import java.util.Map;

public enum Ship60Status {
    NEW_ORDER("new_order"),
    RIDER_ASSIGNED("rider_assigned"),
    PICKUP_ATTEMPT_FAILED_1_ST("1st_pickup_attempt_failed"),
    PICKUP_ATTEMPT_FAILED_2_ND("2nd_pickup_attempt_failed"),
    PICKUP_ATTEMPT_FAILED_3_RD("3rd_pickup_attempt_failed"),
    PICKED_UP("picked_up"),
    SHIPPED_TO_DELIVERY_HUB("shipped_to_delivery_hub"),
    PICKUP_FAILED("pickup_failed"),
    DELIVERING("delivering"),
    DELIVERED("delivered"),
    SEND_ATTEMPT_FAILED_1_ST("1st_send_attempt_failed"),
    SEND_ATTEMPT_FAILED_2_ND("2nd_send_attempt_failed"),
    SEND_ATTEMPT_FAILED_3_RD("3rd_send_attempt_failed"),
    DELIVERY_FAILED("delivery_failed"),
    BEING_RETURNED_TO_SENDER("being_returned_to_sender"),
    PACKAGE_RETURNED("package_returned"),
    RETURN_FAILED("return_failed"),
    PACKAGE_LOST("package_lost"),
    ORDER_CANCELED("order_canceled");

    public final String value;

    Ship60Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    private static final Map<String, Ship60Status> BY_VALUE = new HashMap<>();

    static {
        for (Ship60Status ship60Status : values()) {
            BY_VALUE.put(ship60Status.value, ship60Status);
        }
    }

    public static Ship60Status nameOfValue(String value) {
        return BY_VALUE.get(value);
    }
}
