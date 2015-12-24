package com.example.nortti.politrange.intefaces.impls;


import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.R;
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

public class SitesCatalog implements ICatalog{

    private final String COMMAND_PREFIX = "/api/sites";
    private final WebApiAdapter siteAdapter = new WebApiAdapter(COMMAND_PREFIX);
    private ArrayList<Site> catalogList = new ArrayList<Site>();

    public ArrayList<Site> getCatalogList() {
        return catalogList;
    }

    @Override
    public void populateData() {
        JSONArray jsonObject = null;
        try {
            jsonObject = (JSONArray) new JSONParser().parse(siteAdapter.select(null));
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        catalogList.clear();
        Iterator<JSONObject> iterator = jsonObject.iterator();
        while (iterator.hasNext()) {
            JSONObject o = iterator.next();
            catalogList.add(new Site((int) (long) o.get("siteId"), (String) o.get("name")));
        }
    }


}
