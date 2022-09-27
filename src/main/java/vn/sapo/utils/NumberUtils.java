package vn.sapo.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;

public class NumberUtils {

    public static BigDecimal parseToDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Integer) {
            return new BigDecimal((int) value);
        }
        if (value instanceof Long) {
            return new BigDecimal((long) value);
        }
        if (value instanceof Float) {
            return new BigDecimal((float) value);
        }
        if (value instanceof Double) {
            return new BigDecimal((double) value);
        }
        return null;
    }

    public static BigDecimal parseToDecimal(String value) {
        return StringUtils.isNotBlank(value) ? new BigDecimal(value) : null;
    }

    public static boolean equals(BigDecimal num1, BigDecimal num2) {
        if (num1 == null && num2 == null) return true;
        if (num1 == null || num2 == null) return false;
        return num1.compareTo(num2) == 0;
    }

    public static BigDecimal min(BigDecimal num1, BigDecimal num2) {
        assert num1 != null;
        assert num2 != null;
        return num1.compareTo(num2) < 0 ? num1 : num2;
    }

    public static boolean isInteger(BigDecimal value) {
        int intValue = value.intValue();
        BigDecimal convertedValue = new BigDecimal(intValue);
        return value.compareTo(convertedValue) == 0;
    }

    public static int compareBigDecimal(BigDecimal num1, BigDecimal num2) {
        Comparator<BigDecimal> comparator = Comparator.nullsFirst(Comparator.naturalOrder());
        return comparator.compare(num1, num2);
    }

    public static int compareInteger(Integer num1, Integer num2) {
        Comparator<Integer> comparator = Comparator.nullsFirst(Comparator.naturalOrder());
        return comparator.compare(num1, num2);
    }

    public static int compareLong(Long num1, Long num2) {
        Comparator<Long> comparator = Comparator.nullsFirst(Comparator.naturalOrder());
        return comparator.compare(num1, num2);
    }

    public static int compareDouble(Double num1, Double num2) {
        Comparator<Double> comparator = Comparator.nullsFirst(Comparator.naturalOrder());
        return comparator.compare(num1, num2);
    }

    public static boolean notNullOrZero(BigDecimal bigDecimal) {
        return bigDecimal != null && compareBigDecimal(bigDecimal, BigDecimal.ZERO) != 0;
    }

    public static boolean isNullOrZero(BigDecimal bigDecimal) {
        return bigDecimal == null || compareBigDecimal(bigDecimal, BigDecimal.ZERO) == 0;
    }
}
