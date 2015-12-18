package com.example.nortti.politrange.intefaces.impls;

import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Person;
import com.example.nortti.politrange.objects.Site;
import com.example.nortti.politrange.utils.WebApiAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PersonCatalog implements ICatalog{
    private final String COMMAND_PREFIX = "/api/persons/";
    private final WebApiAdapter apiAdapter = new WebApiAdapter(COMMAND_PREFIX);
    private ArrayList<Person> catalogList = new ArrayList<Person>();
    private  Site site;

    public PersonCatalog(Site site) {
        this.site = site;
    }


    @Override
    public ArrayList<Person> getCatalogList() {
        return catalogList;
    }

    public void populateData() {
        JSONArray jsonObject = null;

        try {
            jsonObject = (JSONArray)(new JSONParser()).parse(apiAdapter.select(null));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        catalogList.clear();
        Iterator<JSONObject> iterator = jsonObject.iterator();

        while(iterator.hasNext()) {
            JSONObject o = iterator.next();
            catalogList.add(new Person((int)(long)o.get("personId"), (String)o.get("name")));
        }

    }
}
