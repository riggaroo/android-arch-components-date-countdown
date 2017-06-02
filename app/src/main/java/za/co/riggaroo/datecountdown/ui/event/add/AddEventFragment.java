package za.co.riggaroo.datecountdown.ui.event.add;

import android.app.DatePickerDialog;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.threeten.bp.LocalDateTime;

import za.co.riggaroo.datecountdown.CountdownApplication;
import za.co.riggaroo.datecountdown.R;
import za.co.riggaroo.datecountdown.injection.CountdownFactory;

public class AddEventFragment extends LifecycleFragment implements DatePickerDialog.OnDateSetListener {


    private EditText editTextTitle, editTextDescription;
    private Button buttonAddEvent, buttonSetDate;
    private TextView textViewCurrentDate;
    private AddEventViewModel addEventViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        setupViews(view);
        setupClickListeners();
        setupViewModel();
        return view;
    }

    private void setupViewModel() {
        CountdownApplication countdownApplication = (CountdownApplication) getActivity().getApplication();
        addEventViewModel = ViewModelProviders.of(this, new CountdownFactory(countdownApplication))
                .get(AddEventViewModel.class);
        editTextTitle.setText(addEventViewModel.getEventName());
        editTextDescription.setText(addEventViewModel.getEventDescription());
        textViewCurrentDate.setText(addEventViewModel.getEventDateTime().toString());
    }

    private void setupClickListeners() {
        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addEventViewModel.setEventName(s.toString());
            }
        });
        editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addEventViewModel.setEventDescription(s.toString());
            }
        });
        buttonAddEvent.setOnClickListener(v -> {
            addEventViewModel.addEvent();
            getActivity().finish();
        });
        buttonSetDate.setOnClickListener(v -> {
            LocalDateTime localDateTime = addEventViewModel.getEventDateTime();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(), this, localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth());

            datePickerDialog.show();
        });
    }

    private void setupViews(View view) {
        buttonAddEvent = (Button) view.findViewById(R.id.button_add);
        editTextTitle = (EditText) view.findViewById(R.id.edit_text_title);
        editTextDescription = (EditText) view.findViewById(R.id.edit_text_description);
        buttonSetDate = (Button) view.findViewById(R.id.button_set_date);
        textViewCurrentDate = (TextView) view.findViewById(R.id.text_view_date_set);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        addEventViewModel.setEventDateTime(LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0));
        textViewCurrentDate.setText(addEventViewModel.getEventDateTime().toString());
    }


}
