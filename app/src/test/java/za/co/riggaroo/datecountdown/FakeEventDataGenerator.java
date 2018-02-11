package za.co.riggaroo.datecountdown;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import za.co.riggaroo.datecountdown.data.entity.Event;

public class FakeEventDataGenerator {

    @NonNull
    public static MutableLiveData<List<Event>> getEventListMutableData() {
        List<Event> events = new ArrayList<>();
        Event event = new Event(1, "Name", "Desc", LocalDateTime.now());
        events.add(event);
        MutableLiveData<List<Event>> fakeEvents = new MutableLiveData<>();
        fakeEvents.setValue(events);
        return fakeEvents;
    }

    public static Event getFakeEvent(){
        return new Event(1, "Birthday", "Desc", LocalDateTime.now());
    }
}
