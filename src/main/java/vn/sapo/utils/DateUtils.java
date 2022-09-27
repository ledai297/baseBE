package vn.sapo.utils;

import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import vn.sapo.exception.FormValidateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtils {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
//    private static final List<? extends DateTimeFormatter> DATE_FORMATS = Arrays.asList(
//        DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"),
//        DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"), DateTimeFormat.forPattern("yyyy-MM-dd"),
//        DateTimeFormat.forPattern("yyyyMMdd"), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"),
//        DateTimeFormat.forPattern("dd-MM-yyy"), DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));

    private static final List<? extends String> TIMEZONE_DATE_FORMATS = Arrays.asList(
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    );

    private static final List<? extends String> UTC_DATE_FORMATS = Arrays.asList(
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    );

    public String toString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        return simpleDateFormat.format(date);
    }

    public static String toString(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date currentDate = convertToDate(date);
        return simpleDateFormat.format(currentDate);
    }

    public static Date convertToDate(String source) throws FormValidateException {
        if (source == null) {
            throw new FormValidateException("Thời gian không đúng định dạng");
        }
        if (!StringUtils.isBlank(source)) {
            for (String fmt : TIMEZONE_DATE_FORMATS) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat(fmt);
                    return formatter.parse(source);
                } catch (IllegalArgumentException | ParseException ignored) {
                }
            }

            for (String fmt : UTC_DATE_FORMATS) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat(fmt);
                    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                    return formatter.parse(source);
                } catch (IllegalArgumentException | ParseException ignored) {
                }
            }
        }
        return null;
    }

    public static String instantToString(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSZ").withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }

    public static String instantToString(Instant instant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }

    public static int getDateKey(Date date, String targetTimeZoneId) {
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), ZoneId.of(targetTimeZoneId));
        String dateString = localDate.toString().replace("-", "");
        return Integer.parseInt(dateString);
    }

    public static String toString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String convertPattern(String date, String originalPattern, String newPattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(originalPattern);
        Date originalDate = simpleDateFormat.parse(date);
        return toString(originalDate, newPattern);
    }
}
