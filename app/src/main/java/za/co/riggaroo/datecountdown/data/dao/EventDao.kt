package za.co.riggaroo.datecountdown.data.dao


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import org.threeten.bp.LocalDateTime

import za.co.riggaroo.datecountdown.data.entity.Event

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface EventDao {

    @Query("SELECT * FROM " + Event.TABLE_NAME + " WHERE " + Event.DATE_FIELD + " > :minDate")
    fun getEvents(minDate: LocalDateTime): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEvent(event: Event)

    @Delete
    fun deleteEvent(event: Event)

    @Update(onConflict = REPLACE)
    fun updateEvent(event: Event)

}
