package za.co.riggaroo.datecountdown.repository

import android.arch.lifecycle.LiveData

import org.threeten.bp.LocalDateTime

import javax.inject.Inject

import io.reactivex.Completable
import za.co.riggaroo.datecountdown.data.dao.EventDao
import za.co.riggaroo.datecountdown.data.entity.Event

/**
 * @author rebeccafranks
 * @since 2017/04/21.
 */
class EventRepositoryImpl @Inject constructor(val eventDao: EventDao) : EventRepository {

    override fun addEvent(event: Event?): Completable {
        return if (event == null) {
            Completable.error(IllegalArgumentException("Event cannot be null"))
        } else Completable.fromAction { eventDao.addEvent(event) }
    }

    override fun getEvents(): LiveData<List<Event>> {
        //Here is where we would do more complex logic, like getting events from a cache
        //then inserting into the database etc. In this example we just go straight to the dao.
        return eventDao.getEvents(LocalDateTime.now())
    }

    override fun deleteEvent(event: Event?): Completable {
        return if (event == null) {
            Completable.error(IllegalArgumentException("Event cannot be null"))
        } else Completable.fromAction { eventDao.deleteEvent(event) }
    }


}
