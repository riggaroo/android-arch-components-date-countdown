package za.co.riggaroo.datecountdown.ui.event.list;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import za.co.riggaroo.datecountdown.data.entity.Event;
import za.co.riggaroo.datecountdown.repository.EventRepository;

public class EventListViewModel extends ViewModel {

    @Inject
    EventRepository eventRepository;

    @Inject
    public EventListViewModel() {
    }

    LiveData<List<Event>> getEvents() {
        return eventRepository.getEvents();
    }

    void deleteEvent(Event event) {
        eventRepository.deleteEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete - deleted event");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "OnError - deleted event: ");
                    }
                });
    }

}
