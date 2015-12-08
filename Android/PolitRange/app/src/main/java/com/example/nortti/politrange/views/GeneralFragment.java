package com.example.nortti.politrange.views;

import android.app.Fragment;
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

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.adapters.SiteAdapter;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.intefaces.impls.SitesCatalog;

public class GeneralFragment extends Fragment implements OnClickListener, OnItemSelectedListener
{

    private Button genApply;
    private Spinner spinner;
    private ListView genList;
    private View header;
    private ICatalog sitesCatalogImpl;


    public void setDataSource(ICatalog sitesCatalogImpl) {
        this.sitesCatalogImpl = sitesCatalogImpl;
        fillData();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.general_fragment, null);

        header = inflater.inflate(R.layout.gen_head, null);

        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        genApply = (Button) v.findViewById(R.id.genApply);
        genApply.setOnClickListener(this);

        genList = (ListView) v.findViewById(R.id.genList);
        genList.addHeaderView(header);
        setDataSource(new SitesCatalog());
        return v;
    }


    private void fillData() {
        sitesCatalogImpl.fillData();
        spinner.setAdapter(new SiteAdapter(getActivity(), sitesCatalogImpl.getCatalogList()));

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

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

                break;
        }
    }
}
