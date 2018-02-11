package za.co.riggaroo.datecountdown.ui.event.add;

import android.arch.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import za.co.riggaroo.datecountdown.data.entity.Event;
import za.co.riggaroo.datecountdown.repository.EventRepository;


public class AddEventViewModel extends ViewModel {

    @Inject
    EventRepository eventRepository;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventDateTime ;


    @Inject
    AddEventViewModel() {
        eventDateTime = LocalDateTime.now();
    }

    String getEventName() {
        return eventName;
    }

    void setEventName(String eventName) {
        this.eventName = eventName;
    }

    String getEventDescription() {
        return eventDescription;
    }

    void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    void addEvent() {
        Event event = new Event(0, eventName, eventDescription, eventDateTime);
        eventRepository.addEvent(event).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - successfully added event");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e, "onError - add:");
                    }
                });
    }

}
