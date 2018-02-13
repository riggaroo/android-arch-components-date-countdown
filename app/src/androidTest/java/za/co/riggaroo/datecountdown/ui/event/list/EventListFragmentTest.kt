package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.util.EspressoDaggerMockRule
import za.co.riggaroo.datecountdown.util.FakeEventDataGenerator
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule
import za.co.riggaroo.datecountdown.util.clickChildViewWithId
import za.co.riggaroo.datecountdown.util.any

@RunWith(AndroidJUnit4::class)
class EventListFragmentTest {

    @JvmField
    @Rule
    var activityTestRule = IntentsTestRule(EventListActivity::class.java, false, false)

    @JvmField
    @Rule
    var executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val espressoDaggerMockRule = EspressoDaggerMockRule()

    var eventDao: EventDao = mock(EventDao::class.java)

    @Test
    fun testDisplayFakeEvents() {
        `when`(eventDao.getEvents(any())).thenReturn(FakeEventDataGenerator.getEventListMutableData())

        activityTestRule.launchActivity(null)

        onView(withText("A party")).perform(click())
        onView(withText("Rebecca's Birthday")).perform(click())
    }

    @Test
    fun testDeleteEvent() {
        val events = FakeEventDataGenerator.getEventListMutableData()
        `when`(eventDao.getEvents(any())).thenReturn(events)

        activityTestRule.launchActivity(null)

        onView(withId(R.id.recycler_view_list_events))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<EventViewHolder>
                        (0, clickChildViewWithId(R.id.button_delete)))
        Thread.sleep(2000) //TODO look into why the Idling resource is not working for Rx
        verify(eventDao).deleteEvent(any())
    }

}