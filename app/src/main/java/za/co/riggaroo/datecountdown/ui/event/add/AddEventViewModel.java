package za.co.riggaroo.datecountdown.ui.event.add;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import za.co.riggaroo.datecountdown.entity.Event;
import za.co.riggaroo.datecountdown.injection.CountdownComponent;
import za.co.riggaroo.datecountdown.repository.EventRepository;


public class AddEventViewModel extends ViewModel implements CountdownComponent.Injectable {

    @Inject
    EventRepository eventRepository;
    private MutableLiveData<String> eventName = new MutableLiveData<>();
    private MutableLiveData<String> eventDescription = new MutableLiveData<>();
    private MutableLiveData<LocalDateTime> eventDateTime = new MutableLiveData<>();


    public AddEventViewModel() {
        eventDateTime.setValue(LocalDateTime.now());
    }

    public LiveData<String> getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName.setValue(eventName);
    }

    public LiveData<String> getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription.setValue(eventDescription);
    }

    public MutableLiveData<LocalDateTime> getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime.setValue(eventDateTime);
    }

    public void addEvent() {
        Event event = new Event(0, eventName.getValue(), eventDescription.getValue(), eventDateTime.getValue());
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
                        Timber.d("onError - add:", e);
                    }
                });
    }


    @Override
    public void inject(CountdownComponent countdownComponent) {
        countdownComponent.inject(this);
    }
}
