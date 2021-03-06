package com.project.nortti.politrange.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.nortti.politrange.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SinceDate extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    String formattedDate, etText;
    String date;
    SimpleDateFormat sdf;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        sdf = new SimpleDateFormat("MM.dd.yyyy");
        formattedDate = sdf.format(c.getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        EditText etSince = (EditText) getActivity().findViewById(R.id.etSince);
        etText = sdf1.format(c.getTime());
        etSince.setText(etText);
    }

    public String getFormattedDate() {
        this.formattedDate = formattedDate;
        return formattedDate;
    }
}
