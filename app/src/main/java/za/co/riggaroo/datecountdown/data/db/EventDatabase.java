package za.co.riggaroo.datecountdown.data.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import za.co.riggaroo.datecountdown.data.dao.EventDao;
import za.co.riggaroo.datecountdown.data.entity.Event;

@Database(entities = {Event.class}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class EventDatabase extends RoomDatabase {

    public abstract EventDao eventDao();

}
