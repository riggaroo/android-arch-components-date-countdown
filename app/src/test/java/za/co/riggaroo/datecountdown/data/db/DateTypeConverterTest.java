package za.co.riggaroo.datecountdown.data.db;

import org.junit.Test;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import static org.junit.Assert.*;

public class DateTypeConverterTest {

    @Test
    public void convertDateToLong(){
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1486728000L, 0, ZoneOffset.UTC);

        long timestamp = DateTypeConverter.INSTANCE.toTimestamp(localDateTime);

        assertEquals(1486728000L, timestamp);
    }
    @Test
    public void convertDateToLong_DateNull_ReturnsNull(){
        Long timestamp = DateTypeConverter.INSTANCE.toTimestamp(null);

        assertNull(timestamp);
    }

    @Test
    public void convertLongToDate(){
        long timestamp = 1486728000L;
        LocalDateTime dateTime = DateTypeConverter.INSTANCE.toDate(timestamp);

        assertEquals(LocalDateTime.ofEpochSecond(1486728000L, 0, ZoneOffset.UTC), dateTime);
    }
    @Test
    public void convertLongToDate_LongNull_ReturnsNull(){
        LocalDateTime localDateTime = DateTypeConverter.INSTANCE.toDate(null);

        assertNull(localDateTime);
    }

}