package vn.sapo.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM_ACCOUNT = "0";
    public static final Long SYSTEM_ACCOUNT_ID = -1L;
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final int MAX_PAGE_SIZE = 250;
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final String PHONE_NUMBER_PATTERN = "^[0-9\\(\\) +-]{10,15}$";
    public static final String REGEX_IDENTITY_NO = "^[0-9]*$";


    private Constants() {
    }
}
