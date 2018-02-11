package za.co.riggaroo.datecountdown.ui.event.add;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.threeten.bp.LocalDateTime;

import za.co.riggaroo.datecountdown.R;
import za.co.riggaroo.datecountdown.SingleFragmentActivity;
import za.co.riggaroo.datecountdown.util.TaskExecutorWithIdlingResourceRule;
import za.co.riggaroo.datecountdown.util.ViewModelUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class AddEventFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityTestRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);
    @Rule
    public TaskExecutorWithIdlingResourceRule executorRule =
            new TaskExecutorWithIdlingResourceRule();

    private AddEventViewModel addEventViewModel;

    @Before
    public void setup(){
        addEventViewModel = mock(AddEventViewModel.class);
        AddEventFragment addEventFragment = new AddEventFragment();

        addEventFragment.countdownViewModelFactory = ViewModelUtil.createFor(addEventViewModel);
        activityTestRule.getActivity().setFragment(addEventFragment);
    }


    @Test
    public void addEvent(){
        when(addEventViewModel.getEventDateTime()).thenReturn(LocalDateTime.now());

        onView(withId(R.id.edit_text_title)).perform(typeText("Christmas Day"), closeSoftKeyboard());

        onView(withId(R.id.edit_text_description)).perform(typeText("Celebration Time"), closeSoftKeyboard());

        onView(withId(R.id.button_set_date)).perform(click());

        onView(withText("OK")).perform(scrollTo(), click());

        onView(withId(R.id.button_add)).perform(click());
    }
}
