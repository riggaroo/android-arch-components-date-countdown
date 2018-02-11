package za.co.riggaroo.datecountdown.util

import android.arch.lifecycle.MutableLiveData
import org.threeten.bp.LocalDateTime
import za.co.riggaroo.datecountdown.data.entity.Event
import java.util.ArrayList

object FakeEventDataGenerator {

    fun getEventListMutableData(): MutableLiveData<List<Event>> {
        val events = ArrayList<Event>()
        val event = Event(1, "Name", "Desc", LocalDateTime.now())
        events.add(event)
        val fakeEvents = MutableLiveData<List<Event>>()
        fakeEvents.value = events
        return fakeEvents
    }

    fun getFakeEvent(): Event {
        return Event(1, "Birthday", "Desc", LocalDateTime.now())
    }
}