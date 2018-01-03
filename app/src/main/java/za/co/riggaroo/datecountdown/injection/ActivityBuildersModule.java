package za.co.riggaroo.datecountdown.injection;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import za.co.riggaroo.datecountdown.ui.event.add.AddEventActivity;
import za.co.riggaroo.datecountdown.ui.event.list.EventListActivity;

/**
 * @author rebeccafranks
 * @since 2018/01/03.
 */

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract AddEventActivity bindAddEventActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract EventListActivity bindEventListActivity();

}
