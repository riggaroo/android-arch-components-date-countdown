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
 * @since 2017/05/12.
 */
@Module
public class MockCountdownModule {

    private final CountdownApplication application;

    public MockCountdownModule(CountdownApplication countdownApplication) {
        this.application = countdownApplication;
    }

    @Provides
    Context getApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    EventDatabase provideEventDatabase(Context context) {
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), EventDatabase.class).build();
    }

    @Provides
    @Singleton
    EventRepository providesEventRepository(EventDatabase eventDatabase) {
        return new EventRepositoryImpl(eventDatabase);
    }

}
