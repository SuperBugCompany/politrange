package com.example.nortti.politrange.intefaces.impls;


import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.R;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;

public class SitesCatalog implements ICatalog{

  private ArrayList<Site> catalogList = new ArrayList<Site>();

    public ArrayList<Site> getCatalogList() {
        return catalogList;
    }
    public void fillData(){
        catalogList.add(new Site(1,"Lenta.ru",R.drawable.ic_lenta));
        catalogList.add(new Site(2,"Rbc.ru",R.drawable.ic_rbc));
        catalogList.add(new Site(3,"Tass.ru",R.drawable.ic_tass));
    }
}
