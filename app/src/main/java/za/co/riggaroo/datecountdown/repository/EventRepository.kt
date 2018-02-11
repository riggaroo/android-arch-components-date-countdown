package za.co.riggaroo.datecountdown.repository


import android.arch.lifecycle.LiveData

import io.reactivex.Completable
import za.co.riggaroo.datecountdown.data.entity.Event

/**
 * @author rebeccafranks
 * @since 2017/04/21.
 */
interface EventRepository {

    fun getEvents(): LiveData<List<Event>>

    fun addEvent(event: Event?): Completable

    fun deleteEvent(event: Event?): Completable

}
