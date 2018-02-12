package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.injection.CountdownModule
import za.co.riggaroo.datecountdown.util.FakeEventDataGenerator
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule
import za.co.riggaroo.datecountdown.util.clickChildViewWithId


@RunWith(AndroidJUnit4::class)
class EventListFragmentTest {

    @JvmField
    @Rule
    var activityTestRule =  IntentsTestRule(EventListActivity::class.java, false, false)

    @JvmField
    @Rule
    var executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val espressoDaggerMockRule = DaggerMock.rule<CountdownComponent>(CountdownModule()) {
        set { component -> component.inject(app) }
        customizeBuilder<CountdownComponent.Builder> { it.application(app) }
    }

    val app: CountdownApplication get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CountdownApplication

    var eventDao : EventDao = mock(EventDao::class.java)

    @Test
    fun testDisplayFakeEvents(){
        `when`(eventDao.getEvents(any())).thenReturn(FakeEventDataGenerator.getEventListMutableData())

        activityTestRule.launchActivity(null)

        onView(withText("A party")).perform(click())
        onView(withText("Rebecca's Birthday")).perform(click())
    }

    @Test
    fun testDeleteEvent(){
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

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

}