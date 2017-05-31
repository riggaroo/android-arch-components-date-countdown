package za.co.riggaroo.datecountdown.ui.event.list;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import za.co.riggaroo.datecountdown.CountdownApplication;
import za.co.riggaroo.datecountdown.R;
import za.co.riggaroo.datecountdown.entity.Event;
import za.co.riggaroo.datecountdown.injection.CountdownFactory;
import za.co.riggaroo.datecountdown.ui.event.add.AddEventActivity;

public class EventListFragment extends LifecycleFragment {

    private static final String TAG = "EventListFragment";
    private EventAdapter adapter;
    private EventListViewModel eventListViewModel;

    private View.OnClickListener deleteClickListener = v -> {
        Event event = (Event) v.getTag();
        eventListViewModel.deleteEvent(event);
    };

    private View.OnClickListener itemClickListener = v -> {
        Event event = (Event) v.getTag();

        Toast.makeText(getContext(), "Clicked:" + event.getName(), Toast.LENGTH_LONG).show();
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_events, container, false);
        setupRecyclerView(v);
        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(v1 -> startActivity(new Intent(getContext(), AddEventActivity.class)));
        CountdownApplication application = (CountdownApplication) getActivity().getApplication();
        eventListViewModel = ViewModelProviders.of(this, new CountdownFactory(application)).get(EventListViewModel.class);

        eventListViewModel.getEvents().observe(this, events -> {
            Log.d(TAG, "Events Changed:" + events);
            adapter.setItems(events);
        });

        return v;
    }

    private void setupRecyclerView(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list_events);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EventAdapter(new ArrayList<>(), getContext(), itemClickListener, deleteClickListener);
        recyclerView.setAdapter(adapter);
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
