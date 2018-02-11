package za.co.riggaroo.datecountdown.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDateTime;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import za.co.riggaroo.datecountdown.data.db.EventDatabase;
import za.co.riggaroo.datecountdown.data.entity.Event;

import static junit.framework.Assert.assertEquals;

/**
 * @author rebeccafranks
 * @since 2017/04/21.
 */
@RunWith(AndroidJUnit4.class)
public class EventDaoTest  {

    EventDao eventDao;


    EventDatabase eventDatabase;

    @Before
    public void setup() {
        eventDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                EventDatabase.class).build();

        eventDao = eventDatabase.eventDao();
    }


    @Test
    public void addEvent_SuccessfullyAddsEvent() throws InterruptedException {
        Event event = generateEventTestData(0, "Wedding");
        eventDao.addEvent(event);

        List<Event> eventRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()));

        assertEquals(event.getName(), eventRetrieved.get(0).getName());
        eventDao.deleteEvent(eventRetrieved.get(0));
    }

    @Test
    public void deleteEvent_SuccessfullyDeletesEvent() throws InterruptedException {
        Event event = generateEventTestData(0, "Vacation");
        eventDao.addEvent(event);

        List<Event> eventRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()));
        assertEquals(event.getName(), eventRetrieved.get(0).getName());

        eventDao.deleteEvent(eventRetrieved.get(0));
        List<Event> eventRetrievedAfterUpdate = getValue(eventDao.getEvents(LocalDateTime.now()));

        assertEquals(0, eventRetrievedAfterUpdate.size());
    }


    @Test
    public void updateEvent_SuccessfullyUpdatesEvent() throws InterruptedException {
        Event event = generateEventTestData(0, "First Name");
        eventDao.addEvent(event);

        List<Event> eventsRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()));
        Event eventRetrieved = eventsRetrieved.get(0);
        assertEquals(event.getName(), eventRetrieved.getName());
        String newName = "New Name";
        Event newEventUpdated = generateEventTestData(eventRetrieved.getId(), newName);

        eventDao.updateEvent(newEventUpdated);
        List<Event> eventRetrievedAfterUpdate = getValue(eventDao.getEvents(LocalDateTime.now()));

        assertEquals(newName, eventRetrievedAfterUpdate.get(0).getName());
        eventDao.deleteEvent(eventRetrievedAfterUpdate.get(0));
    }


    Event generateEventTestData(int id, String name) {
        return new Event(id, name, "Test Description", LocalDateTime.now().plusDays(7));
    }

    /**
     * This is used to make sure the method waits till data is available from the observer.
     */
    public static <T> T getValue(LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
