package com.example.nortti.politrange.intefaces.impls;

import android.support.v4.app.Fragment;

import com.example.nortti.politrange.objects.Day;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Person;
import com.example.nortti.politrange.objects.Site;
import com.example.nortti.politrange.utils.WebApiAdapter;
import com.example.nortti.politrange.views.GeneralFragment;
import com.example.nortti.politrange.views.SinceDate;
import com.example.nortti.politrange.views.ToDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class DayCatalog implements ICatalog{
    private String COMMAND_PREFIX = "/api/stats/";
    private final WebApiAdapter apiAdapter;
    private ArrayList<Day> catalogList = new ArrayList<Day>();
    String dFormat;
    Site site;
    SinceDate sinceDate;
    ToDate toDate;
    SimpleDateFormat sdf;

    public DayCatalog(Site site) {
        this.site = site;

        sinceDate = new SinceDate();
        toDate = new ToDate();
        COMMAND_PREFIX += String.valueOf(site.getId())+"?begin="+sinceDate.getFormattedDate()+"&end="+toDate.getFormattedDate();
        apiAdapter = new WebApiAdapter(COMMAND_PREFIX);
    }

    @Override
    public ArrayList<Day> getCatalogList() {
        return catalogList;
    }

    @Override
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

        while (iterator.hasNext()){
            JSONObject o = iterator.next();
            sdf = new SimpleDateFormat("dd.MM.yyyy");
            dFormat = sdf.format(o.get("pageFoundDate"));
            catalogList.add(new Day((int)o.get("pageFoundDate"),(int)(long)o.get("rank")));
        }
    }
}
