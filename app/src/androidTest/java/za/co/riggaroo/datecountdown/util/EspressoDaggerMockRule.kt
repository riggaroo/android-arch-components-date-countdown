package za.co.riggaroo.datecountdown.util

import android.support.test.InstrumentationRegistry
import it.cosenonjaviste.daggermock.DaggerMockRule
import it.cosenonjaviste.daggermock.DaggerMockRule.BuilderCustomizer
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.injection.CountdownComponent
import za.co.riggaroo.datecountdown.injection.CountdownModule

class EspressoDaggerMockRule : DaggerMockRule<CountdownComponent>(CountdownComponent::class.java, CountdownModule()) {

    private val app: CountdownApplication
        get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as CountdownApplication

    init {
        customizeBuilder(BuilderCustomizer<CountdownComponent.Builder> { builder -> builder.application(app) })
        set { component -> component.inject(app) }
    }
}