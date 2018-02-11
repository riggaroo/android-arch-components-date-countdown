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

class CountdownApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())//TODO Install a Crashlytics tree in production
        }
        AppInjector.init(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}
