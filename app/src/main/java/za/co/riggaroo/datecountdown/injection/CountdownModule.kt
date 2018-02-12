package za.co.riggaroo.datecountdown.injection

import android.arch.persistence.room.Room
import android.util.Log

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import za.co.riggaroo.datecountdown.CountdownApplication
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.data.db.EventDatabase
import za.co.riggaroo.datecountdown.repository.EventRepository
import za.co.riggaroo.datecountdown.repository.EventRepositoryImpl

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Module(includes = [(AndroidInjectionModule::class), (ViewModelModule::class)])
open class CountdownModule {

    @Provides
    open fun providesEventRepository(eventDao: EventDao): EventRepository {
        return EventRepositoryImpl(eventDao)
    }

    @Provides
    @Singleton
    open fun providesEventDao(eventDatabase: EventDatabase): EventDao {
        return eventDatabase.eventDao()
    }

    @Provides
    @Singleton
    open fun providesEventDatabase(context: CountdownApplication): EventDatabase {
        return Room.databaseBuilder(context.applicationContext, EventDatabase::class.java, "event_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
