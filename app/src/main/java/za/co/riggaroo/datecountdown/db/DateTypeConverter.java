package za.co.riggaroo.datecountdown.db;


import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateTypeConverter {

    @TypeConverter
    public static LocalDateTime toDate(String timestamp) {
        return timestamp == null ? null : LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @TypeConverter
    public static String toTimestamp(LocalDateTime date) {
        return date == null ? null : date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}

