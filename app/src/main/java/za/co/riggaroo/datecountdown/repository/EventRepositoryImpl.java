package za.co.riggaroo.datecountdown.repository;

import android.arch.lifecycle.LiveData;

import org.threeten.bp.LocalDateTime;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import za.co.riggaroo.datecountdown.data.dao.EventDao;
import za.co.riggaroo.datecountdown.data.entity.Event;

/**
 * @author rebeccafranks
 * @since 2017/04/21.
 */
public class EventRepositoryImpl implements EventRepository {

    @Inject
    EventDao eventDao;

    public EventRepositoryImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Completable addEvent(Event event) {
        if (event == null){
            return Completable.error(new IllegalArgumentException("Event cannot be null"));
        }
        return Completable.fromAction(() -> eventDao.addEvent(event));
    }

    @Override
    public LiveData<List<Event>> getEvents() {
        //Here is where we would do more complex logic, like getting events from a cache
        //then inserting into the database etc. In this example we just go straight to the dao.
        return eventDao.getEvents(LocalDateTime.now());
    }

    @Override
    public Completable deleteEvent(Event event) {
        if (event == null){
            return Completable.error(new IllegalArgumentException("Event cannot be null"));
        }
        return Completable.fromAction(() -> eventDao.deleteEvent(event));
    }


}
