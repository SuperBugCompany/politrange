package com.example.nortti.politrange.views;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.adapters.DayAdapter;
import com.example.nortti.politrange.adapters.SiteAdapter;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.intefaces.impls.DayCatalog;
import com.example.nortti.politrange.intefaces.impls.SitesCatalog;
import com.example.nortti.politrange.objects.DatePerson;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;

public class DailyFragment extends Fragment implements OnClickListener,OnItemSelectedListener {
    public static final String TITLE = "Не найдена ссылка!";
    public static final String MESSAGE = "Извините, сервис временно не доступен";
    private ArrayList<DatePerson> dayPersons;
    private Spinner spinner;
    private FrameLayout frame1;
    private FrameLayout frame2;
    private ImageButton butSince;
    private ImageButton butTo;
    private EditText etSince;
    private EditText etTo;
    private SiteAdapter adapter;
    private View header;
    private View footer;
    private String[] dayName;
    private TextView sumInt;
    private ExpandableListView dayList;
    private DayAdapter dayAdapter;
    private Button dayApply;
    private int Summ;
    private ICatalog sitesCatalogImpl;
    public ICatalog daysCatalogImpl;
    SinceDate sinceDate;
    ToDate toDate;
    FragmentManager fm;


    public void setDataSource(ICatalog sitesCatalogImpl) {
        this.sitesCatalogImpl = sitesCatalogImpl;
        spinData();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.daily_fragment, null);

        fm = getActivity().getFragmentManager();
        dayPersons = new ArrayList<DatePerson>();
        dayName = getResources().getStringArray(R.array.date);
        dayApply = (Button) v.findViewById(R.id.butApply);
        dayApply.setOnClickListener(this);



        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        butSince = (ImageButton) frame1.findViewById(R.id.butSince);
        butSince.setOnClickListener(this);
        etSince = (EditText) frame1.findViewById(R.id.etSince);

        frame2 = (FrameLayout) v.findViewById(R.id.frame2);
        butTo = (ImageButton) frame2.findViewById(R.id.butTo);
        butTo.setOnClickListener(this);
        etTo = (EditText) frame2.findViewById(R.id.etTo);

        dayList = (ExpandableListView) v.findViewById(R.id.dayList);


        spinner = (Spinner) v.findViewById(R.id.wSpin);
        spinner.setOnItemSelectedListener(this);


        dayAdapter = new DayAdapter(getActivity(), dayPersons);

        setDataSource(new SitesCatalog());
        return v;
    }

    private void spinData() {
        sitesCatalogImpl.populateData();
        spinner.setAdapter(new SiteAdapter(getActivity(), sitesCatalogImpl.getDateList()));

    }

    private void listData(Site site){
        daysCatalogImpl = new DayCatalog(site, sinceDate.getFormattedDate(),toDate.getFormattedDate());
        daysCatalogImpl.populateData();
        dayList.setAdapter(new DayAdapter(getActivity(), daysCatalogImpl.getDateList()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        int siteIndex = spinner.getSelectedItemPosition();
        switch (v.getId()) {
            case R.id.butSince:
                sinceDate = new SinceDate();
                sinceDate.show(fm,"datePicker");

                break;
            case R.id.butTo:
                toDate = new ToDate();
                toDate.show(fm, "datePicker");

                break;
            case R.id.butApply:
                listData((Site)sitesCatalogImpl.getDateList().get(siteIndex));
                break;
        }
    }



}
