package com.example.nortti.politrange.intefaces.impls;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.objects.Day;
import com.example.nortti.politrange.intefaces.ICatalog;
import com.example.nortti.politrange.objects.Person;
import com.example.nortti.politrange.objects.Site;
import com.example.nortti.politrange.utils.WebApiAdapter;
import com.example.nortti.politrange.views.DailyFragment;
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
    Site site;
    ViewGroup cont;

    ToDate toDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public DayCatalog(Site site) {
        SinceDate sinceDate = new SinceDate();
        this.site = site;
       String date1 = sinceDate.getFormattedDate();
        COMMAND_PREFIX += String.valueOf(site.getId())+"?begin="+date1+"&end=12.11.2016";
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
            catalogList.add(new Day((String)o.get("pageFoundDate"),(int)(long)o.get("rank")));
        }
    }
}
