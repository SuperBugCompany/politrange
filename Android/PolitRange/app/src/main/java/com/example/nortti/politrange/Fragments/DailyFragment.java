package com.example.nortti.politrange.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.Site.Site;
import com.example.nortti.politrange.Site.SiteAdapter;

import java.util.ArrayList;

public class DailyFragment extends Fragment implements OnClickListener {
    private ArrayList<Site> sites;
    private Spinner spinner;
    private FrameLayout frame1;
    private FrameLayout frame2;
    private ImageButton butSince;
    private ImageButton butTo;
    private EditText etSince;
    private EditText etTo;
    private SiteAdapter adapter;
    private String[] siteTitle;
    private String[] siteUrls;
    private TypedArray imgLogo;
    int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    String date;
    FragmentTransaction ft;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.daily_fragment, null);
        ft = getFragmentManager().beginTransaction();
        sites = new ArrayList<Site>();
        siteTitle = getResources().getStringArray(R.array.site);
        siteUrls = getResources().getStringArray(R.array.urls);
        imgLogo = getResources().obtainTypedArray(R.array.logos);

        for (int i = 0; i < siteTitle.length; i++) {
            Site site = new Site(siteTitle[i], siteUrls[i], imgLogo.getResourceId(i, -1));
            sites.add(site);
        }
        frame1 = (FrameLayout) v.findViewById(R.id.frame1);
        butSince = (ImageButton) frame1.findViewById(R.id.butSince);
        butSince.setOnClickListener(this);
        etSince = (EditText) frame1.findViewById(R.id.etSince);


        frame2 = (FrameLayout) v.findViewById(R.id.frame2);
        butTo = (ImageButton) frame2.findViewById(R.id.butTo);
        butTo.setOnClickListener(this);
        etTo = (EditText) frame2.findViewById(R.id.etTo);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        adapter = new SiteAdapter(getActivity(), sites);
        spinner.setAdapter(adapter);
        imgLogo.recycle();

        return v;
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
        }
    }


}
