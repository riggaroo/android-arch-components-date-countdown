package za.co.riggaroo.datecountdown.ui.event.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import za.co.riggaroo.datecountdown.R;
import za.co.riggaroo.datecountdown.injection.Injectable;

public class EventListActivity extends AppCompatActivity implements HasSupportFragmentInjector, Injectable {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
