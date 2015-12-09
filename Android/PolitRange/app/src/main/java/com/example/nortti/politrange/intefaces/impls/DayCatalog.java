package com.example.nortti.politrange.intefaces.impls;

import com.example.nortti.politrange.Daily.Day;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;
import java.util.Iterator;

public class DayCatalog implements ICatalog{
    private ArrayList<Day> catalogList = new ArrayList<Day>();
    private Site site;

    @Override
    public ArrayList<Day> getCatalogList() {
        Iterator<Day> dayIterator = catalogList.iterator();
        while (dayIterator.hasNext()){
            Day day = dayIterator.next();
            if (day.getSiteId() != site.getId()){
                dayIterator.remove();
            }
        }
        return catalogList;
    }

    @Override
    public void fillData() {

    }
}
