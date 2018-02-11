package za.co.riggaroo.datecountdown.ui.event.list

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import javax.inject.Inject

import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.data.entity.Event
import za.co.riggaroo.datecountdown.injection.CountdownViewModelFactory
import za.co.riggaroo.datecountdown.injection.Injectable
import za.co.riggaroo.datecountdown.ui.event.add.AddEventActivity
import java.util.*

class EventListFragment : Fragment(), Injectable {
    @Inject
    lateinit var countdownViewModelFactory: CountdownViewModelFactory

    private var adapter: EventAdapter? = null
    private var eventListViewModel: EventListViewModel? = null
    private val deleteClickListener = View.OnClickListener { v : View ->
        val event = v.getTag() as Event
        eventListViewModel!!.deleteEvent(event)
    }

    private val itemClickListener = View.OnClickListener { v : View ->
        val event = v.getTag() as Event

        Toast.makeText(context, "Clicked:" + event.name, Toast.LENGTH_LONG).show()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_list_events, container, false)
        setupRecyclerView(v)
        val floatingActionButton = v.findViewById<FloatingActionButton>(R.id.fab_add)
        floatingActionButton.setOnClickListener { startActivity(Intent(context, AddEventActivity::class.java)) }
        eventListViewModel = ViewModelProviders.of(this, countdownViewModelFactory!!).get(EventListViewModel::class.java)


        eventListViewModel?.events?.observe(this, android.arch.lifecycle.Observer<List<Event>> { events ->
            Log.d(TAG, "Events Changed:" + events)
            events?.let {
                adapter?.setItems(events)
            }
        })
        return v
    }


    private fun setupRecyclerView(v: View) {
        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_view_list_events)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = EventAdapter(ArrayList(), recyclerView.context, itemClickListener, deleteClickListener)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private val TAG = "EventListFragment"
    }

}
