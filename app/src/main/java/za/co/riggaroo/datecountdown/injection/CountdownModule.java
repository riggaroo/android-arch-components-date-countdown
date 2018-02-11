package za.co.riggaroo.datecountdown.injection;

import android.arch.persistence.room.Room;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import za.co.riggaroo.datecountdown.CountdownApplication;
import za.co.riggaroo.datecountdown.data.dao.EventDao;
import za.co.riggaroo.datecountdown.data.db.EventDatabase;
import za.co.riggaroo.datecountdown.repository.EventRepository;
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Module(includes = {AndroidInjectionModule.class, ViewModelModule.class})
public class CountdownModule {

    @Provides
    EventRepository providesEventRepository(EventDao eventDao) {
        Log.d("CountdownModule", "providesEventRepository:" + eventDao);
        return new EventRepositoryImpl(eventDao);
    }

    @Provides
    @Singleton
    EventDao providesEventDao(EventDatabase eventDatabase){
        Log.d("CountdownModule", "providesEventDao:" + eventDatabase);
        return eventDatabase.eventDao();
    }

    @Provides
    @Singleton
    EventDatabase providesEventDatabase(CountdownApplication context) {
        Log.d("CountdownModule", "providesEventDatabase");
        return Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class, "event_db").build();
    }
}
