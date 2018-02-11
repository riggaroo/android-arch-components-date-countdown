package za.co.riggaroo.datecountdown.data.db


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.data.entity.Event

@Database(entities = arrayOf(Event::class), version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

}
