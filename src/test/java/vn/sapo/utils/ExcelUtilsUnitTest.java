package vn.sapo.utils;

import org.apache.poi.ss.util.CellReference;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExcelUtilsUnitTest {
    @Test
    public void getColumnIndexFromColumnLabel(){
        int result = CellReference.convertColStringToIndex("AI");
        assertThat(result >= 0);
    }
}
