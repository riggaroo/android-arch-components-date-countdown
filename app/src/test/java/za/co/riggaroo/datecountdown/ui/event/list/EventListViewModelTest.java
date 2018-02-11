package za.co.riggaroo.datecountdown.ui.event.list;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import za.co.riggaroo.datecountdown.FakeEventDataGenerator;
import za.co.riggaroo.datecountdown.LiveDataUtils;
import za.co.riggaroo.datecountdown.data.entity.Event;
import za.co.riggaroo.datecountdown.repository.EventRepository;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */

public class EventListViewModelTest {

    EventListViewModel eventListViewModel;

    @Mock
    EventRepository eventRepository;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @BeforeClass
    public static void setUpClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventListViewModel = new EventListViewModel();
        eventListViewModel.eventRepository = eventRepository;

    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
    }

    @Test
    public void getEvents() throws InterruptedException {
        MutableLiveData<List<Event>> fakeEvents = FakeEventDataGenerator.getEventListMutableData();
        when(eventRepository.getEvents()).thenReturn(fakeEvents);

        List<Event> eventsReturned = LiveDataUtils.getValue(eventListViewModel.getEvents());

        verify(eventRepository).getEvents();
        assertEquals(1, eventsReturned.size());
        assertEquals("Name", eventsReturned.get(0).getName());
    }



    @Test
    public void deleteEvent() {
        when(eventRepository.deleteEvent(any())).thenReturn(Completable.complete());

        eventListViewModel.deleteEvent(FakeEventDataGenerator.getFakeEvent());

        verify(eventRepository).deleteEvent(any());
    }



}
