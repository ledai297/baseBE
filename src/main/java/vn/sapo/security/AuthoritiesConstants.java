package vn.sapo.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {
    public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String MARKET_VISITOR = "ROLE_MARKET_VISITOR";
    public static final String PROJECT = "ROLE_PROJECT";
    public static final String SALE_MANAGER = "ROLE_SALE_MANAGER";
    public static final String SALE_GROUP_LEADER = "ROLE_SALE_GROUP_LEADER";
    public static final String SALE_EMPLOYEE = "ROLE_SALE_EMPLOYEE";
    public static final String PURCHASING_MANAGER = "ROLE_PURCHASING_MANAGER";
    public static final String PURCHASING_GROUP_LEADER = "ROLE_PURCHASING_GROUP_LEADER";
    public static final String PURCHASING_EMPLOYEE = "ROLE_PURCHASING_EMPLOYEE";
    public static final String OPERATION_MANAGER = "ROLE_OPERATION_MANAGER";
    public static final String OPERATION_GROUP_LEADER = "ROLE_OPERATION_GROUP_LEADER";
    public static final String OPERATION_EMPLOYEE = "ROLE_OPERATION_EMPLOYEE";
    public static final String CUSTOMER_SUCCESS_MANAGER = "ROLE_CUSTOMER_SUCCESS_MANAGER";
    public static final String CUSTOMER_SUCCESS_GROUP_LEADER = "ROLE_CUSTOMER_SUCCESS_GROUP_LEADER";
    public static final String CUSTOMER_SUCCESS_EMPLOYEE = "ROLE_CUSTOMER_SUCCESS_EMPLOYEE";
    public static final String ACCOUNTING_MANAGER = "ROLE_ACCOUNTING_MANAGER";
    public static final String ACCOUNTING_GROUP_LEADER = "ROLE_ACCOUNTING_GROUP_LEADER";
    public static final String ACCOUNTING_EMPLOYEE = "ROLE_ACCOUNTING_EMPLOYEE";
    public static final String SETTING_AUTHORIZATION = "ROLE_SETTING_AUTHORIZATION";

    private AuthoritiesConstants() {
    }
}
