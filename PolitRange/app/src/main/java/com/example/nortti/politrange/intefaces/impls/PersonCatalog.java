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

public class PersonCatalog implements ICatalog {


    private String COMMAND_PREFIX = "/api/stats/";
    private final WebApiAdapter apiAdapter;
    private ArrayList<Person> catalogList = new ArrayList<Person>();
    Site site;

    public PersonCatalog(Site site) {
        this.site = site;
        COMMAND_PREFIX += String.valueOf(site.getId());
        apiAdapter = new WebApiAdapter(COMMAND_PREFIX);
    }

    @Override
    public ArrayList<Person> getDateList() {
        return catalogList;
    }

    public void populateData() {
        JSONArray jsonObject = null;

        try {
            jsonObject = (JSONArray) (new JSONParser()).parse(apiAdapter.select(null));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        catalogList.clear();
        Iterator<JSONObject> iterator = jsonObject.iterator();

        while (iterator.hasNext()) {
            JSONObject o = iterator.next();
            catalogList.add(new Person((String) o.get("personName"), (int) (long) o.get("rank")));
        }

    }


}
