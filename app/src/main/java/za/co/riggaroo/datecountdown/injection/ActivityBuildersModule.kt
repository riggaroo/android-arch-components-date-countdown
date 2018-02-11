package za.co.riggaroo.datecountdown.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import za.co.riggaroo.datecountdown.ui.event.add.AddEventActivity
import za.co.riggaroo.datecountdown.ui.event.list.EventListActivity

/**
 * @author rebeccafranks
 * @since 2018/01/03.
 */

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun bindAddEventActivity(): AddEventActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    abstract fun bindEventListActivity(): EventListActivity

}
