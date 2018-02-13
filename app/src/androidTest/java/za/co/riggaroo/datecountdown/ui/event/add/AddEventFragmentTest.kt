package za.co.riggaroo.datecountdown.ui.event.add


import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import za.co.riggaroo.datecountdown.util.any

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.mockito.Mockito.*
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.util.EspressoDaggerMockRule

@RunWith(AndroidJUnit4::class)
class AddEventFragmentTest {

    @JvmField
    @Rule
    var activityTestRule = ActivityTestRule(AddEventActivity::class.java, true, false)

    @JvmField
    @Rule
    var executorRule = TaskExecutorWithIdlingResourceRule()

    @JvmField
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val espressoDaggerMockRule = EspressoDaggerMockRule()

    var eventDao: EventDao = mock(EventDao::class.java)

    @Test
    fun addEvent() {
        activityTestRule.launchActivity(null)

        onView(withId(R.id.edit_text_title)).perform(typeText("Christmas Day"), closeSoftKeyboard())

        onView(withId(R.id.edit_text_description)).perform(typeText("Celebration Time"), closeSoftKeyboard())

        onView(withId(R.id.button_set_date)).perform(click())

        onView(withText("OK")).perform(scrollTo(), click())

        onView(withId(R.id.button_add)).perform(click())

        verify(eventDao).addEvent(any())
    }

}
