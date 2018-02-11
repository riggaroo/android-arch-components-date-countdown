package za.co.riggaroo.datecountdown.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel

/**
 * @author rebeccafranks
 * @since 2018/01/02.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddEventViewModel::class)
    abstract fun bindAddEventViewModel(addEventViewModel: AddEventViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindEventListViewModel(eventListViewModel: EventListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CountdownViewModelFactory): ViewModelProvider.Factory

}
