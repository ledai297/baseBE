package vn.sapo.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUtils {
    private static final int DEF_COUNT = 20;

    private static final SecureRandom SECURE_RANDOM;

    public static final String NUMBER = "0123456789";
    public static final String ALPHABET_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHABET_LOWER_CASE_NUMBER = ALPHABET_LOWER_CASE + NUMBER;
    public static final String ALPHABET_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABET_UPPER_CASE_NUMBER = ALPHABET_UPPER_CASE + NUMBER;
    public static final String ALPHABET_UPPER_LOWER_CASE_NUMBER = ALPHABET_LOWER_CASE + ALPHABET_UPPER_CASE + NUMBER;

    private static final char[] symbols = (ALPHABET_UPPER_LOWER_CASE_NUMBER).toCharArray();

    static {
        SECURE_RANDOM = new SecureRandom();
        SECURE_RANDOM.nextBytes(new byte[64]);
    }

    private RandomUtils() {
    }

    public static String random() {
        return RandomStringUtils.random(DEF_COUNT, 0, 0, true, true, null, SECURE_RANDOM);
    }

    public static String random(int length, String prefix, String suffix) {
        if (length < 1) return "";
        char[] buf = new char[length];
        Random random = new SecureRandom();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        String code = new String(buf);
        if (StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(suffix)) {
            return prefix + code + suffix;
        } else if (StringUtils.isNotBlank(prefix) && StringUtils.isBlank(suffix)) {
            return prefix + code;
        } else if (StringUtils.isBlank(prefix) && StringUtils.isNotBlank(suffix)) {
            return code + suffix;
        } else {
            return code;
        }
    }

    public static String random(String symbol, int length, String prefix, String suffix) {
        if (length < 1) return "";
        char[] symbols = (symbol).toCharArray();
        char[] buf = new char[length];
        Random random = new SecureRandom();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        String code = new String(buf);
        if (StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(suffix)) {
            return prefix + code + suffix;
        } else if (StringUtils.isNotBlank(prefix) && StringUtils.isBlank(suffix)) {
            return prefix + code;
        } else if (StringUtils.isBlank(prefix) && StringUtils.isNotBlank(suffix)) {
            return code + suffix;
        } else {
            return code;
        }
    }
}
