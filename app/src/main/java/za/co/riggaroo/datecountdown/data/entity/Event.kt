package za.co.riggaroo.datecountdown.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import za.co.riggaroo.datecountdown.data.entity.Event.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class Event(@PrimaryKey(autoGenerate = true)
            val id: Int,
            val name: String,
            val description: String,
            @ColumnInfo(name = DATE_FIELD)
            val date: LocalDateTime) {

    fun daysUntil(): Long {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), date)
    }

    companion object {
       const val TABLE_NAME = "events"
       const val DATE_FIELD = "date"
    }
}
