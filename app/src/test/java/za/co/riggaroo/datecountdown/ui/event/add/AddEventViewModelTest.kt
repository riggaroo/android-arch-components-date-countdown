package za.co.riggaroo.datecountdown.ui.event.add

import android.arch.core.executor.testing.InstantTaskExecutorRule

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.threeten.bp.LocalDateTime

import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.mockito.ArgumentMatchers
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.repository.EventRepository
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel

import org.mockito.Matchers.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

/**
 * @author rebeccafranks
 * @since 2017/05/09.
 */
class AddEventViewModelTest {

    lateinit var addEventViewModel: AddEventViewModel

    @Mock
    lateinit var eventRepository: EventRepository

    @JvmField
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        addEventViewModel = AddEventViewModel(eventRepository)
        addEventViewModel.eventRepository = eventRepository
    }

    @Test
    fun addEvent() {
        `when`(eventRepository.addEvent(ArgumentMatchers.any())).thenReturn(Completable.complete())

        addEventViewModel.eventDescription = "Wedding"
        addEventViewModel.eventName = "Kates Wedding"
        addEventViewModel.eventDateTime = LocalDateTime.now()

        addEventViewModel.addEvent()

        verify<EventRepository>(eventRepository).addEvent(ArgumentMatchers.any())
    }

    companion object {


        @JvmStatic
        @BeforeClass
        fun setUpClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        }

        @JvmStatic
        @AfterClass
        fun tearDownClass() {
            RxAndroidPlugins.reset()
        }
    }
}