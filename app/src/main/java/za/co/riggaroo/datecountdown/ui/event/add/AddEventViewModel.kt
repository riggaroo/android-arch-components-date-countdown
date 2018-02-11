package za.co.riggaroo.datecountdown.ui.event.add

import android.arch.lifecycle.ViewModel

import org.threeten.bp.LocalDateTime

import javax.inject.Inject

import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import za.co.riggaroo.datecountdown.data.entity.Event
import za.co.riggaroo.datecountdown.repository.EventRepository


class AddEventViewModel @Inject constructor(var eventRepository: EventRepository) : ViewModel() {

    var eventName: String? = null
    var eventDescription: String? = null
    var eventDateTime: LocalDateTime? = null


    init {
        eventDateTime = LocalDateTime.now()
    }

    fun addEvent() {
        val event = Event(0, eventName!!, eventDescription!!, eventDateTime!!)
        eventRepository.addEvent(event).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                        Timber.d("onComplete - successfully added event")
                    }

                    override fun onError(e: Throwable) {
                        Timber.d(e, "onError - add:")
                    }
                })
    }

}
