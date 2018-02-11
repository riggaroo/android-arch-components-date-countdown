package za.co.riggaroo.datecountdown.data.db

import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

import org.junit.Assert.*

class DateTypeConverterTest {

    @Test
    fun convertDateToLong() {
        val localDateTime = LocalDateTime.ofEpochSecond(1486728000L, 0, ZoneOffset.UTC)

        val timestamp = DateTypeConverter().toTimestamp(localDateTime)

        assertEquals(1486728000L, timestamp)
    }

    @Test
    fun convertDateToLong_DateNull_ReturnsNull() {
        val timestamp = DateTypeConverter().toTimestamp(null)

        assertNull(timestamp)
    }

    @Test
    fun convertLongToDate() {
        val timestamp = 1486728000L
        val dateTime = DateTypeConverter().toDate(timestamp)

        assertEquals(LocalDateTime.ofEpochSecond(1486728000L, 0, ZoneOffset.UTC), dateTime)
    }

    @Test
    fun convertLongToDate_LongNull_ReturnsNull() {
        val localDateTime = DateTypeConverter().toDate(null)

        assertNull(localDateTime)
    }

}