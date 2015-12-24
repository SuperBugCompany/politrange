package com.example.nortti.politrange.views;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.adapters.GenAdapter;
import com.example.nortti.politrange.adapters.SiteAdapter;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.intefaces.impls.PersonCatalog;
import com.example.nortti.politrange.intefaces.impls.SitesCatalog;
import com.example.nortti.politrange.objects.Site;

public class GeneralFragment extends Fragment implements OnClickListener, OnItemSelectedListener {

    private Button genApply;
    private Spinner spinner;
    private ListView genList;
    private View header;
    private ICatalog siteCatalogImpl;
    private ICatalog personCatalogImpl;




    public void setSpinnerSource(ICatalog siteCatalogImpl) {
        this.siteCatalogImpl = siteCatalogImpl;
        spinData();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.general_fragment, container, false);

        header = inflater.inflate(R.layout.gen_head, null);

        spinner = (Spinner) v.findViewById(R.id.wSpin);
        spinner.setOnItemSelectedListener(this);
        genApply = (Button) v.findViewById(R.id.genApply);
        genApply.setOnClickListener(this);

        genList = (ListView) v.findViewById(R.id.genList);
        genList.addHeaderView(header);
        this.setSpinnerSource(new SitesCatalog());
        Intent i = new Intent();
        i.putExtra("spin", spinner.getSelectedItemPosition() + 1);
        return v;
    }

    private void spinData() {
        siteCatalogImpl.populateData();
        spinner.setAdapter(new SiteAdapter(getActivity(), siteCatalogImpl.getCatalogList()));
    }

    private void listData(Site site) {
        personCatalogImpl = new PersonCatalog(site);
        personCatalogImpl.populateData();
        genList.setAdapter(new GenAdapter(getActivity(), personCatalogImpl.getCatalogList()));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {
        int siteIndex = spinner.getSelectedItemPosition();
        switch (v.getId()) {
            case R.id.genApply:
                listData((Site) siteCatalogImpl.getCatalogList().get(siteIndex));
                break;
        }
    }
}
