package za.co.riggaroo.datecountdown.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import za.co.riggaroo.datecountdown.CountdownApplication;
import za.co.riggaroo.datecountdown.db.EventDatabase;
import za.co.riggaroo.datecountdown.repository.EventRepository;
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Module(includes = {AndroidInjectionModule.class, ViewModelModule.class})
public class CountdownModule {

    @Provides
    EventRepository providesEventRepository(EventDatabase eventDatabase) {
        return new EventRepositoryImpl(eventDatabase.eventDao());
    }

    @Provides
    EventDatabase providesEventDatabase(CountdownApplication context) {
        return Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class, "event_db").build();
    }
}
