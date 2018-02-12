package za.co.riggaroo.datecountdown


import android.app.Activity
import android.app.Application

import com.jakewharton.threetenabp.AndroidThreeTen

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import za.co.riggaroo.datecountdown.injection.AppInjector
import za.co.riggaroo.datecountdown.injection.DaggerCountdownComponent

open class CountdownApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())//TODO Install a Crashlytics tree in production
        }
        initializeApplication()
    }

    open fun initializeApplication() {
        DaggerCountdownComponent.builder().application(this)
                .build().inject(this)
        AppInjector.init(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}
