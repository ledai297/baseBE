package vn.sapo.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectUtilsUT {
    @Test
    public void equals_Null_Null(){
        boolean result = ObjectUtils.equals(null, null);
        assertThat(result == true);
    }
    @Test
    public void equals_String_null(){
        boolean result = ObjectUtils.equals("123", null);
        assertThat(result == false);
    }
    @Test
    public void equals_String_String(){
        boolean result1 = ObjectUtils.equals("123", "123");
        assertThat(result1 == true);

        boolean result2 = ObjectUtils.equals("1234", "123");
        assertThat(result1 == false);
    }
    @Test
    public void equals_int_null(){
        boolean result = ObjectUtils.equals(1, null);
        assertThat(result == false);
    }
}
