package za.co.riggaroo.datecountdown;


import android.app.Activity;
import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import za.co.riggaroo.datecountdown.injection.AppInjector;

public class CountdownApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());//TODO Install a Crashlytics tree in production
        }
        AppInjector.init(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
