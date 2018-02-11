package za.co.riggaroo.datecountdown.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import za.co.riggaroo.datecountdown.FakeEventDataGenerator
import za.co.riggaroo.datecountdown.LiveDataUtils
import za.co.riggaroo.datecountdown.data.dao.EventDao

class EventRepositoryImplTest {

    @Mock
    private lateinit var eventDao: EventDao

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var eventRepository: EventRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        eventRepository = EventRepositoryImpl(eventDao)
    }

    @Test
    fun addEvent_TriggersDbAdd() {
        val event = FakeEventDataGenerator.getFakeEvent()
        eventRepository.addEvent(event).test().onComplete()

        verify<EventDao>(eventDao).addEvent(event)
    }

    @Test
    fun addEvent_EventNull_ThrowsIllegalArgumentException() {
        eventRepository.addEvent(null)
                .test()
                .assertError(IllegalArgumentException::class.java)

        verify<EventDao>(eventDao, never()).addEvent(any())
    }

    @Test
    @Throws(InterruptedException::class)
    fun getEvent_DaoGetEvents() {

        val fakeEvents = FakeEventDataGenerator.getEventListMutableData()
        `when`(eventDao.getEvents(any())).thenReturn(fakeEvents)

        val eventsReturned = LiveDataUtils.getValue(eventRepository.getEvents())

        verify(eventDao).getEvents(any())
        assertArrayEquals(fakeEvents.value?.toTypedArray(), eventsReturned.toTypedArray())
    }

    @Test
    fun deleteEvent_TriggersDbDelete() {
        val event = FakeEventDataGenerator.getFakeEvent()
        eventRepository.deleteEvent(event).test().onComplete()

        verify<EventDao>(eventDao).deleteEvent(event)
    }

    @Test
    fun deleteEvent_EventNull_ThrowsIllegalArgumentException() {
        eventRepository.deleteEvent(null)
                .test()
                .assertError(IllegalArgumentException::class.java)
    }


    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}