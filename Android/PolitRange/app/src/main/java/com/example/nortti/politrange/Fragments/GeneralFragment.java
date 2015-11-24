package com.example.nortti.politrange.Fragments;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nortti.politrange.General.Gen;
import com.example.nortti.politrange.General.GenAdapter;
import com.example.nortti.politrange.R;
import com.example.nortti.politrange.Site.Site;
import com.example.nortti.politrange.Site.SiteAdapter;
import com.example.nortti.politrange.SwitchSpinner;

import java.util.ArrayList;

public class GeneralFragment extends Fragment implements OnClickListener, OnItemSelectedListener
{
    private ArrayList<Site> sites;
    private ArrayList<Gen> gens;
    private Button genApply;
    private Spinner spinner;
    private SiteAdapter siteAdapter;
    private String[] siteTitle;
    private String[] siteUrls;
    private TypedArray imgLogo;
    private String[] genName;
    private String[] genIndexL;
    private String[] genIndexR;
    private String[] genIndexT;
    SwitchSpinner switchSpinner;
    private ListView genList;
    private GenAdapter genAdapter;
    private View header;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.general_fragment, null);

        sites = new ArrayList<Site>();
        siteTitle = getResources().getStringArray(R.array.site);
        siteUrls = getResources().getStringArray(R.array.urls);
        imgLogo = getResources().obtainTypedArray(R.array.logos);

        for (int i = 0; i < siteTitle.length; i++)
        {
            Site site = new Site(siteTitle[i], siteUrls[i], imgLogo.getResourceId(i, -1));
            sites.add(site);
        }
        header = inflater.inflate(R.layout.gen_head, null);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        siteAdapter = new SiteAdapter(getActivity(), sites);
        spinner.setAdapter(siteAdapter);
        spinner.setOnItemSelectedListener(this);
        imgLogo.recycle();


        genApply = (Button) v.findViewById(R.id.genApply);
        genApply.setOnClickListener(this);

        gens = new ArrayList<Gen>();
        genName = getResources().getStringArray(R.array.person);
        genIndexL = getResources().getStringArray(R.array.rangeL);
        genIndexR = getResources().getStringArray(R.array.rangeR);
        genIndexT = getResources().getStringArray(R.array.rangeT);


        genList = (ListView) v.findViewById(R.id.genList);
        genList.addHeaderView(header);

        genAdapter = new GenAdapter(getActivity(), gens);


        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (position)
        {
            case 0:

                switchSpinner = new SwitchSpinner(genName,genIndexL);

                break;
            case 1:
                switchSpinner = new SwitchSpinner(genName,genIndexR);
                break;
            case 2:
                switchSpinner = new SwitchSpinner(genName,genIndexT);
                break;
        }
    }

   @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.genApply:

                gens.clear();
                genList.setAdapter(genAdapter);

                for (int a = 0; a < genName.length; a++)
                {
                    Gen gen = new Gen(switchSpinner.getName()[a], switchSpinner.getIndex()[a]);
                    gens.add(gen);
                }
                break;
        }
    }
}
