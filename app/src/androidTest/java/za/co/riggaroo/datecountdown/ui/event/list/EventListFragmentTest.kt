package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.injection.CountdownModule
import za.co.riggaroo.datecountdown.util.FakeEventDataGenerator
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule

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
        onView(withText("Kate's Wedding")).perform(click())
        onView(withText("Rebecca's Birthday")).perform(click())
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}