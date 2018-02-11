package za.co.riggaroo.datecountdown.data.entity;

import org.junit.Test;
import org.threeten.bp.LocalDateTime;

public class EventTest {

    @Test
    public void getDaysUntil() {
        LocalDateTime christmas = LocalDateTime.of(2018, 12, 25, 11, 0, 0);

        Event event = new Event(1, "Christmas", "Day", christmas);


    }
}