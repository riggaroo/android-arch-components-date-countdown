package za.co.riggaroo.datecountdown.injection

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.ui.event.add.AddEventViewModel
import za.co.riggaroo.datecountdown.ui.event.list.EventListViewModel

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (CountdownModule::class),
    (ActivityBuildersModule::class)])
interface CountdownComponent {

    fun inject(countdownApplication: CountdownApplication)

    @Component.Builder
    interface Builder {

        fun countdownModule(countdownModule: CountdownModule): Builder


        @BindsInstance
        fun application(application: CountdownApplication): Builder

        fun build(): CountdownComponent

    }
}