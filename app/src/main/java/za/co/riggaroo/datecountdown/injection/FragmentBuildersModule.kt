package za.co.riggaroo.datecountdown.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import za.co.riggaroo.datecountdown.ui.event.add.AddEventFragment
import za.co.riggaroo.datecountdown.ui.event.list.EventListFragment

/**
 * @author rebeccafranks
 * @since 2018/01/03.
 */
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeEventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddEventFragment(): AddEventFragment
}
