package za.co.riggaroo.datecountdown.ui.event.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import za.co.riggaroo.datecountdown.R;
import za.co.riggaroo.datecountdown.injection.Injectable;

public class AddEventActivity extends AppCompatActivity implements HasSupportFragmentInjector, Injectable {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
