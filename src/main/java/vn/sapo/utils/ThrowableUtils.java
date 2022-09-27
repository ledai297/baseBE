package vn.sapo.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ThrowableUtils {
    public static String getExceptionExplain(Exception e){
        return String.format("%s | %s | %s", e.toString(), e.getMessage(), ExceptionUtils.getStackTrace(e));
    }
}
