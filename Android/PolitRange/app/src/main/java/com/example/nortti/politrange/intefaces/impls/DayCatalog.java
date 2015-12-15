package com.example.nortti.politrange.intefaces.impls;

import com.example.nortti.politrange.objects.Day;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;
import java.util.Iterator;

public class DayCatalog implements ICatalog{
    private ArrayList<Day> catalogList = new ArrayList<Day>();
    private Site site;

    public DayCatalog(Site site) {
        this.site = site;
    }

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
    public void populateData() {
        catalogList.add(new Day(1,"Lenta.ru",1,"13.11.2012",2467));
        catalogList.add(new Day(2,"Lenta.ru/rubrics/russia/",1,"17.05.2014",459));
        catalogList.add(new Day(3,"Lenta.ru/rubrics/media/",1,"12.11.2009",630));
        catalogList.add(new Day(4,"Rbc.ru",2,"21.08.2013",537));
        catalogList.add(new Day(5,"Rbc.ru/shares/",2,"30.09.2014",2504));
        catalogList.add(new Day(6,"Realty.rbc.ru/",2,"23.02.2015",157));
        catalogList.add(new Day(7,"Tass.ru/",3,"19.04.2015",3478));
        catalogList.add(new Day(8,"Tass.ru/mezhdunarodnaya-panorama",3,"29.05.2015",693));
        catalogList.add(new Day(9,"Tass.ru/ekonomika",3,"01.12.2015",457));

    }
}
