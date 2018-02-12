package za.co.riggaroo.datecountdown.ui.event.list

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalDateTime

import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.SingleFragmentActivity
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule
import za.co.riggaroo.datecountdown.util.ViewModelUtil

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import za.co.riggaroo.datecountdown.ui.event.add.AddEventFragment
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel
import za.co.riggaroo.datecountdown.util.FakeEventDataGenerator

@RunWith(AndroidJUnit4::class)
class EventListFragmentTest {

    @JvmField
    @Rule
    var activityTestRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @JvmField
    @Rule
    var executorRule = TaskExecutorWithIdlingResourceRule()


    @Mock
    private lateinit var eventListViewModel: EventListViewModel

    @Before
    fun setup() {
        //eventListViewModel = mock(EventListViewModel::class.java)
        MockitoAnnotations.initMocks(this);
        val eventListFragment = EventListFragment()

        eventListFragment.countdownViewModelFactory = ViewModelUtil.createFor<EventListViewModel>(eventListViewModel)
        activityTestRule.activity.setFragment(eventListFragment)
    }


    @Test
    fun deleteEvent() {
       `when`(eventListViewModel.getEvents()).thenReturn(FakeEventDataGenerator.getEventListMutableData())

        onView(withId(R.id.button_delete)).perform(click())

        verify(eventListViewModel).deleteEvent(ArgumentMatchers.any())
    }

    @Test
    fun listEvents(){
      /*  `when`(eventListViewModel.events).thenReturn(FakeEventDataGenerator.getEventListMutableData())

        onView(withId(R.id.button_delete)).perform(click())

        verify(eventListViewModel).deleteEvent(ArgumentMatchers.any())*/
    }
}
