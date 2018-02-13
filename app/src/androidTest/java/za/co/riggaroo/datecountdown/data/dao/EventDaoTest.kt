package za.co.riggaroo.datecountdown.data.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDateTime
import za.co.riggaroo.datecountdown.data.db.EventDatabase
import za.co.riggaroo.datecountdown.data.entity.Event
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * @author rebeccafranks
 * @since 2017/04/21.
 */
@RunWith(AndroidJUnit4::class)
class EventDaoTest {

    lateinit var eventDao: EventDao

    lateinit var eventDatabase: EventDatabase

    @Before
    fun setup() {
        eventDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                EventDatabase::class.java).build()

        eventDao = eventDatabase.eventDao()
    }


    @Test
    fun addEvent_SuccessfullyAddsEvent() {
        val event = generateEventTestData(0, "Wedding")
        eventDao.addEvent(event)

        val eventRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()))

        assertEquals(event.name, eventRetrieved[0].name)
        eventDao.deleteEvent(eventRetrieved[0])
    }

    @Test
    fun deleteEvent_SuccessfullyDeletesEvent() {
        val event = generateEventTestData(0, "Vacation")
        eventDao.addEvent(event)

        val eventRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()))
        assertEquals(event.name, eventRetrieved[0].name)

        eventDao.deleteEvent(eventRetrieved[0])
        val eventRetrievedAfterUpdate = getValue(eventDao.getEvents(LocalDateTime.now()))

        assertEquals(0, eventRetrievedAfterUpdate.size)
    }


    @Test
    @Throws(InterruptedException::class)
    fun updateEvent_SuccessfullyUpdatesEvent() {
        val event = generateEventTestData(0, "First Name")
        eventDao.addEvent(event)

        val eventsRetrieved = getValue(eventDao.getEvents(LocalDateTime.now()))
        val (id, name) = eventsRetrieved[0]
        assertEquals(event.name, name)
        val newName = "New Name"
        val newEventUpdated = generateEventTestData(id, newName)

        eventDao.updateEvent(newEventUpdated)
        val eventRetrievedAfterUpdate = getValue(eventDao.getEvents(LocalDateTime.now()))

        assertEquals(newName, eventRetrievedAfterUpdate[0].name)
        eventDao.deleteEvent(eventRetrievedAfterUpdate[0])
    }


    internal fun generateEventTestData(id: Int, name: String): Event {
        return Event(id, name, "Test Description", LocalDateTime.now().plusDays(7))
    }

    companion object {

        /**
         * This is used to make sure the method waits till data is available from the observer.
         */
        @Throws(InterruptedException::class)
        fun <T> getValue(liveData: LiveData<T>): T {
            val data = arrayOfNulls<Any>(1)
            val latch = CountDownLatch(1)
            val observer = object : Observer<T> {
                override fun onChanged(o: T?) {
                    data[0] = o
                    latch.countDown()
                    liveData.removeObserver(this)
                }
            }
            liveData.observeForever(observer)
            latch.await(2, TimeUnit.SECONDS)

            return data[0] as T
        }
    }
}
