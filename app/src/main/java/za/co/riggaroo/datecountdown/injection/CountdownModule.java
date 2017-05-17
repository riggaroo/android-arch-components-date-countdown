package za.co.riggaroo.datecountdown.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import za.co.riggaroo.datecountdown.CountdownApplication;
import za.co.riggaroo.datecountdown.db.EventDatabase;
import za.co.riggaroo.datecountdown.repository.EventRepository;
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Module
public class CountdownModule {

    private CountdownApplication countdownApplication;

    public CountdownModule(CountdownApplication countdownApplication) {
        this.countdownApplication = countdownApplication;
    }

    @Provides
    Context applicationContext() {
        return countdownApplication;
    }

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventDatabase eventDatabase) {
        return new EventRepositoryImpl(eventDatabase);
    }

    @Provides
    @Singleton
    EventDatabase providesEventDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class, "event_db").build();
    }
}
