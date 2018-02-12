package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.*
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import za.co.riggaroo.datecountdown.FakeEventDataGenerator
import za.co.riggaroo.datecountdown.LiveDataUtils
import za.co.riggaroo.datecountdown.data.entity.Event
import za.co.riggaroo.datecountdown.repository.EventRepository

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */

class EventListViewModelTest {

    lateinit var eventListViewModel: EventListViewModel

    @Mock
    lateinit var eventRepository: EventRepository

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        eventListViewModel = EventListViewModel(eventRepository)
    }

    @Test
    @Throws(InterruptedException::class)
    fun getEvents() {
        val fakeEvents = FakeEventDataGenerator.getEventListMutableData()
        `when`(eventRepository.getEvents()).thenReturn(fakeEvents)

        val eventsReturned = LiveDataUtils.getValue(eventListViewModel.getEvents())

        verify(eventRepository).getEvents()
        assertEquals(1, eventsReturned.size)
        assertEquals("Name", eventsReturned[0].name)
    }


    @Test
    fun deleteEvent() {
        `when`(eventRepository.deleteEvent(any())).thenReturn(Completable.complete())

        eventListViewModel.deleteEvent(FakeEventDataGenerator.getFakeEvent())

        verify(eventRepository).deleteEvent(any())
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            RxAndroidPlugins.reset()
        }
    }
}
