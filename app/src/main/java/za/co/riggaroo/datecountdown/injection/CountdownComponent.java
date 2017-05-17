package za.co.riggaroo.datecountdown.injection;

import javax.inject.Singleton;

import dagger.Component;
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel;
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Singleton
@Component(modules = {CountdownModule.class})
public interface CountdownComponent {

    void inject(EventListViewModel eventListViewModel);

    void inject(AddEventViewModel addEventViewModel);

    interface Injectable {
        void inject(CountdownComponent countdownComponent);
    }
}
