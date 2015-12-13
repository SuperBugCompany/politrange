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
    private final WebApiAdapter apiAdapter = new WebApiAdapter("/api/persons/");
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
            jsonObject = (JSONArray)(new JSONParser()).parse(this.apiAdapter.select((String)null));
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        this.catalogList.clear();
        Iterator iterator = jsonObject.iterator();

        while(iterator.hasNext()) {
            JSONObject o = (JSONObject)iterator.next();
            this.catalogList.add(new Person((int)((Long)o.get("personId")).longValue(), (String)o.get("name")));
        }

    }
}
