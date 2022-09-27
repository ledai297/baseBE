package vn.sapo.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberUtilsUT {
    @Test
    public void convertIntegerToLong(){
        Integer number = 1;
        Long longValue = number.longValue();

        assert(longValue == 1);
    }

    @Test
    public void roundBigDecimal(){
        MathContext mathContext = new MathContext(2, RoundingMode.HALF_UP);
        Long longValue1 = new BigDecimal("1.25").round(mathContext).longValue();
        assert (longValue1 == 1);

        Long longValue2 = new BigDecimal("1.75").setScale(0, RoundingMode.HALF_UP).longValue();
        assert (longValue2 == 2);

        Long longValue3 = new BigDecimal("1.5").setScale(0, RoundingMode.HALF_UP).longValue();
        assert (longValue3 == 2);

        Long longValue4 = new BigDecimal("1.49").setScale(0, RoundingMode.HALF_UP).longValue();
        assert (longValue4 == 1);
    }
}
