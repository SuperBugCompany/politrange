package com.example.nortti.politrange.intefaces.impls;

import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Person;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;

public class PersonCatalog implements ICatalog{

    private ArrayList<Person> catalogList = new ArrayList<Person>();

    @Override
    public ArrayList getCatalogList() {
        return catalogList;
    }

    @Override
    public void fillData() {
        catalogList.add(new Person(1,"Путин",14670));
        catalogList.add(new Person(2,"Медведев",7392));
        catalogList.add(new Person(3,"Навальный",4170));
    }
}
