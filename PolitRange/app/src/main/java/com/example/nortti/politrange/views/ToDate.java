package com.example.nortti.politrange.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nortti.politrange.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ToDate extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    String formattedDate, etText;
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

        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
        formattedDate = sdf.format(c.getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
        EditText etTo = (EditText) getActivity().findViewById(R.id.etTo);
        etText = sdf1.format(c.getTime());
        etTo.setText(etText);

    }

    public String getFormattedDate() {
        return formattedDate;
    }
}
