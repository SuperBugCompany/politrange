package com.example.nortti.politrange.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nortti.politrange.Daily.Day;
import com.example.nortti.politrange.adapters.DayAdapter;
import com.example.nortti.politrange.R;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.intefaces.impls.SitesCatalog;
import com.example.nortti.politrange.objects.Site;
import com.example.nortti.politrange.adapters.SiteAdapter;
import com.example.nortti.politrange.SwitchSpinner;

import java.util.ArrayList;

public class DailyFragment extends Fragment implements OnClickListener,OnItemSelectedListener {
    private ArrayList<Day> days;
    private Spinner spinner;
    private FrameLayout frame1;
    private FrameLayout frame2;
    private ImageButton butSince;
    private ImageButton butTo;
    private EditText etSince;
    private EditText etTo;
    private SiteAdapter adapter;
    FragmentTransaction ft;
    private View header;
    private View footer;
    private String[] dayName;
    private TextView sumInt;
    private ListView dayList;
    private DayAdapter dayAdapter;
    SwitchSpinner switchSpinner;
    private Button dayApply;
    private int Summ;
    private ICatalog sitesCatalogImpl;

    public void setDataSource(ICatalog sitesCatalogImpl) {
        this.sitesCatalogImpl = sitesCatalogImpl;
        fillData();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.daily_fragment, null);
        ft = getFragmentManager().beginTransaction();


        days = new ArrayList<Day>();
        dayName = getResources().getStringArray(R.array.date);
        dayApply = (Button) v.findViewById(R.id.butApply);
        dayApply.setOnClickListener(this);
        header = inflater.inflate(R.layout.day_head, null);
        footer = inflater.inflate(R.layout.day_foot, null);
        sumInt = (TextView) footer.findViewById(R.id.summInt);

        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        butSince = (ImageButton) frame1.findViewById(R.id.butSince);
        butSince.setOnClickListener(this);
        etSince = (EditText) frame1.findViewById(R.id.etSince);

        frame2 = (FrameLayout) v.findViewById(R.id.frame2);
        butTo = (ImageButton) frame2.findViewById(R.id.butTo);
        butTo.setOnClickListener(this);
        etTo = (EditText) frame2.findViewById(R.id.etTo);

        dayList = (ListView) v.findViewById(R.id.dayList);
        dayList.addHeaderView(header);
        dayList.addFooterView(footer);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);


        dayAdapter = new DayAdapter(getActivity(),days);

        setDataSource(new SitesCatalog());
        return v;
    }

    private void fillData() {
        sitesCatalogImpl.fillData();
        spinner.setAdapter(new SiteAdapter(getActivity(), sitesCatalogImpl.getCatalogList()));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position)
        {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butSince:
                SinceDate sinceDate = new SinceDate();
                sinceDate.show(getFragmentManager(), "datePicker");

                break;
            case R.id.butTo:
                ToDate toDate = new ToDate();
                toDate.show(getFragmentManager(),"datePicker");
                break;
            /*case R.id.butApply:

                Summ = 0;
                days.clear();
                dayList.setAdapter(dayAdapter);

                for (int a = 0; a < dayName.length; a++)
                {
                    Day day = new Day(switchSpinner.getName()[a], switchSpinner.getIndex()[a]);
                    int b = Integer.parseInt(switchSpinner.getIndex()[a]);
                    Summ +=b;
                    days.add(day);
                }
                sumInt.setText(String.valueOf(Summ));
                break;*/
        }
    }



}
