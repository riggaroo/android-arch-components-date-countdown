package za.co.riggaroo.datecountdown.ui.event.add

import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView

import org.threeten.bp.LocalDateTime

import javax.inject.Inject

import za.co.riggaroo.datecountdown.R
import za.co.riggaroo.datecountdown.injection.CountdownViewModelFactory
import za.co.riggaroo.datecountdown.injection.Injectable

class AddEventFragment : Fragment(), DatePickerDialog.OnDateSetListener, Injectable {

    @Inject
    lateinit var countdownViewModelFactory: ViewModelProvider.Factory
    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var buttonAddEvent: Button? = null
    private var buttonSetDate: Button? = null
    private var textViewCurrentDate: TextView? = null
    private var addEventViewModel: AddEventViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_event, container, false)

        setupViews(view)
        setupClickListeners()
        setupViewModel()
        return view
    }

    private fun setupViewModel() {
        addEventViewModel = ViewModelProviders.of(this, countdownViewModelFactory!!)
                .get(AddEventViewModel::class.java)
        editTextTitle?.setText(addEventViewModel?.eventName)
        editTextDescription?.setText(addEventViewModel?.eventDescription)
        textViewCurrentDate?.text = addEventViewModel?.eventDateTime!!.toString()
    }

    private fun setupClickListeners() {
        editTextTitle?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                addEventViewModel!!.eventName = s.toString()
            }
        })
        editTextDescription?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                addEventViewModel!!.eventDescription = s.toString()
            }
        })
        buttonAddEvent!!.setOnClickListener {
            addEventViewModel!!.addEvent()
            activity!!.finish()
        }
        buttonSetDate!!.setOnClickListener {
            val localDateTime = addEventViewModel!!.eventDateTime
            val datePickerDialog = DatePickerDialog(
                    context!!, this, localDateTime!!.year, localDateTime.monthValue - 1, localDateTime.dayOfMonth)

            datePickerDialog.show()
        }
    }

    private fun setupViews(view: View) {
        buttonAddEvent = view.findViewById(R.id.button_add)
        editTextTitle = view.findViewById(R.id.edit_text_title)
        editTextDescription = view.findViewById(R.id.edit_text_description)
        buttonSetDate = view.findViewById(R.id.button_set_date)
        textViewCurrentDate = view.findViewById(R.id.text_view_date_set)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        addEventViewModel?.eventDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0)
        textViewCurrentDate?.text = addEventViewModel?.eventDateTime?.toString()
    }


}
