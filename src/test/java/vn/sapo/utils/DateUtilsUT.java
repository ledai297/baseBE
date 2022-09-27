package vn.sapo.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtilsUT {
    @Test
    public void testLocalDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,0,6,18,0,0);
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));

        Date date = Date.from(calendar.toInstant());
        System.out.println(date.toString());

        System.out.println(ZoneId.getAvailableZoneIds());
        LocalDate localDate = LocalDate.ofInstant(
            date.toInstant(),
            ZoneId.of("Asia/Ho_Chi_Minh")
        );
        System.out.println(localDate.toString());

        assert (0 == 0);
    }
    @Test
    public void testGenerateDateKey(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,0,6,18,0,0);
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));

        Date date = Date.from(calendar.toInstant());
        int dateKey = DateUtils.getDateKey(date, "Asia/Ho_Chi_Minh");
        assert (dateKey == 20210107);
    }
    @Test
    public void testGenerateDateKeyWithNewDate(){
        Date date = new Date();
        int dateKey = DateUtils.getDateKey(date, "Asia/Ho_Chi_Minh");
        assert (dateKey == 20210107);
    }
}
