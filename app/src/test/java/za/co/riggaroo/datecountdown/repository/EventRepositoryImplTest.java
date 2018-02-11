package za.co.riggaroo.datecountdown.repository;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import za.co.riggaroo.datecountdown.FakeEventDataGenerator;
import za.co.riggaroo.datecountdown.LiveDataUtils;
import za.co.riggaroo.datecountdown.data.dao.EventDao;
import za.co.riggaroo.datecountdown.data.entity.Event;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventRepositoryImplTest {

    @Mock
    private EventDao eventDao;
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private EventRepository eventRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        eventRepository = new EventRepositoryImpl(eventDao);
    }

    @Test
    public void addEvent_TriggersDbAdd(){
        Event event = FakeEventDataGenerator.getFakeEvent();
        eventRepository.addEvent(event).test().onComplete();

        verify(eventDao).addEvent(event);
    }
    @Test
    public void addEvent_EventNull_ThrowsIllegalArgumentException(){
        eventRepository.addEvent(null)
                .test()
                .assertError(IllegalArgumentException.class);

        verify(eventDao, never()).addEvent(null);
    }

    @Test
    public void getEvent_DaoGetEvents() throws InterruptedException {

        MutableLiveData<List<Event>> fakeEvents = FakeEventDataGenerator.getEventListMutableData();
        when(eventDao.getEvents(any())).thenReturn(fakeEvents);

        List<Event> eventsReturned = LiveDataUtils.getValue(eventRepository.getEvents());

        verify(eventDao).getEvents(any());
        assertArrayEquals(fakeEvents.getValue().toArray(), eventsReturned.toArray());
    }

    @Test
    public void deleteEvent_TriggersDbDelete(){
        Event event = FakeEventDataGenerator.getFakeEvent();
        eventRepository.deleteEvent(event).test().onComplete();

        verify(eventDao).deleteEvent(event);
    }
    @Test
    public void deleteEvent_EventNull_ThrowsIllegalArgumentException(){
        eventRepository.deleteEvent(null)
                .test()
                .assertError(IllegalArgumentException.class);

        verify(eventDao, never()).addEvent(null);
    }

}