package za.co.riggaroo.datecountdown.ui.event.list


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import javax.inject.Inject

import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import za.co.riggaroo.datecountdown.data.entity.Event
import za.co.riggaroo.datecountdown.repository.EventRepository

class EventListViewModel @Inject constructor(var eventRepository: EventRepository) : ViewModel() {

    val events: LiveData<List<Event>>
        get() = eventRepository.getEvents()

    fun deleteEvent(event: Event) {
        eventRepository.deleteEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Timber.d("onComplete - deleted event")
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e, "OnError - deleted event: ")
                    }
                })
    }

}
