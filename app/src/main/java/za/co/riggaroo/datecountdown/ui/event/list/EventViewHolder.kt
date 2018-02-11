package za.co.riggaroo.datecountdown.ui.event.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

import za.co.riggaroo.datecountdown.R

class EventViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    var eventTextView: TextView = v.findViewById<View>(R.id.text_view_event_name) as TextView
    var countdownTextView: TextView = v.findViewById<View>(R.id.text_view_countdown) as TextView
    var descriptionTextView: TextView = v.findViewById<View>(R.id.text_view_event_description) as TextView
    var deleteButton: ImageButton = v.findViewById<View>(R.id.button_delete) as ImageButton

}
