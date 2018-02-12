package za.co.riggaroo.datecountdown.util

import android.arch.lifecycle.MutableLiveData
import org.threeten.bp.LocalDateTime
import za.co.riggaroo.datecountdown.data.entity.Event
import java.util.ArrayList

object FakeEventDataGenerator {

    fun getEventListMutableData(): MutableLiveData<List<Event>> {
        val events = ArrayList<Event>()
        val event = Event(1, "Kate's Wedding", "Dress fancy", LocalDateTime.now().plusDays(3))
        events.add(event)
        val event2 = Event(1, "Rebecca's Birthday", "Bring Cake", LocalDateTime.now().plusDays(10))
        events.add(event2)
        val fakeEvents = MutableLiveData<List<Event>>()
        fakeEvents.postValue(events)
        return fakeEvents
    }

    fun getFakeEvent(): Event {
        return Event(1, "Birthday", "Desc", LocalDateTime.now())
    }
}