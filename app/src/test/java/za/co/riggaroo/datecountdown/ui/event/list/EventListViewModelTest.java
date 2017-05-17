package za.co.riggaroo.datecountdown.ui.event.list;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import za.co.riggaroo.datecountdown.entity.Event;
import za.co.riggaroo.datecountdown.injection.CountdownComponent;
import za.co.riggaroo.datecountdown.repository.EventRepository;
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel;

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
        MutableLiveData<List<Event>> fakeEvents = getEventListMutableData();
        when(eventRepository.getEvents()).thenReturn(fakeEvents);

        eventListViewModel.inject(new CountdownComponent() {
            @Override
            public void inject(EventListViewModel eventListViewModel) {
                eventListViewModel.eventRepository = eventRepository;
            }

            @Override
            public void inject(AddEventViewModel addEventViewModel) {

            }
        });
        List<Event> eventsReturned = getValue(eventListViewModel.getEvents());

        verify(eventRepository).getEvents();
        assertEquals(1, eventsReturned.size());
        assertEquals("Name", eventsReturned.get(0).getName());
    }

    @NonNull
    private MutableLiveData<List<Event>> getEventListMutableData() {
        List<Event> events = new ArrayList<>();
        Event event = new Event(1, "Name", "Desc", LocalDateTime.now());
        events.add(event);
        MutableLiveData<List<Event>> fakeEvents = new MutableLiveData<>();
        fakeEvents.setValue(events);
        return fakeEvents;
    }

    @Test
    public void deleteEvent() {
        when(eventRepository.deleteEvent(any())).thenReturn(Completable.complete());

        eventListViewModel.deleteEvent(new Event(1, "Name", "Description", LocalDateTime.now()));

        verify(eventRepository).deleteEvent(any());
    }

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
