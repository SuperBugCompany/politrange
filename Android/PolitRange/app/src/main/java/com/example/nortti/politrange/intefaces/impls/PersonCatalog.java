package com.example.nortti.politrange.intefaces.impls;

import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Person;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;
import java.util.Iterator;

public class PersonCatalog implements ICatalog{

    private ArrayList<Person> catalogList = new ArrayList<Person>();
    private  Site site;

    public PersonCatalog(Site site) {
        this.site = site;
    }


    @Override
    public ArrayList<Person> getCatalogList() {
        Iterator<Person> personIterator = catalogList.iterator();
        while (personIterator.hasNext()){
            Person person = personIterator.next();
            if (person.getSiteId() != site.getId()){
                personIterator.remove();
            }
        }
        return catalogList;
    }

    @Override
    public void fillData() {
        catalogList.add(new Person(1,"Путин",14670,1));
        catalogList.add(new Person(2,"Медведев",7392,1));
        catalogList.add(new Person(3,"Навальный",4170,1));
        catalogList.add(new Person(4,"Путин",8690,2));
        catalogList.add(new Person(5,"Медведев",4329,2));
        catalogList.add(new Person(6,"Навальный",3861,2));
        catalogList.add(new Person(7,"Путин",5639,3));
        catalogList.add(new Person(8,"Медведев",5860,3));
        catalogList.add(new Person(9,"Навальный",2672,3));
    }
}
