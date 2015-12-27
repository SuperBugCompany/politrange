package com.example.nortti.politrange.intefaces.impls;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class DayCatalog implements ICatalog{
    private String COMMAND_PREFIX = "/api/stats/";
    private final WebApiAdapter apiAdapter;
    private static final String TAG = "myLogs";
    private ArrayList<Day> catalogList = new ArrayList<Day>();
    String SinceDate;
    String ToDate;
    Site site;

    public DayCatalog(Site site,String SinceDate, String ToDate) {
        this.site = site;
        this.SinceDate = SinceDate;
        this.ToDate = ToDate;
        COMMAND_PREFIX += String.valueOf(site.getId())+"?begin="+String.valueOf(SinceDate)+"&end="+String.valueOf(ToDate);
        apiAdapter = new WebApiAdapter(COMMAND_PREFIX);
    }

    @Override
    public ArrayList<Day> getCatalogList() {
        return catalogList;
    }

    @Override
    public void populateData(){
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
            String date = o.get("pageFoundDate").toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
            String dd = null;
            Date date1 = null;
            try {
                date1 = format.parse(date);
                dd = format1.format(date1);
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

           // Log.d(TAG, dd);
            catalogList.add(new Day(dd,(int)(long)o.get("rank")));
        }
    }
}
