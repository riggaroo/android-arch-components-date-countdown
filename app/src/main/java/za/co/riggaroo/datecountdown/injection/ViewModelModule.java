package za.co.riggaroo.datecountdown.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel;
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel;

/**
 * @author rebeccafranks
 * @since 2018/01/02.
 */
@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddEventViewModel.class)
    abstract ViewModel bindAddEventViewModel(AddEventViewModel addEventViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel.class)
    abstract ViewModel bindEventListViewModel(EventListViewModel eventListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(CountdownViewModelFactory factory);

}
