package com.project.nortti.politrange.intefaces.impls;

import com.project.nortti.politrange.intefaces.ICatalog;
import com.project.nortti.politrange.objects.DatePerson;
import com.project.nortti.politrange.objects.DayPerson;
import com.project.nortti.politrange.objects.Site;
import com.project.nortti.politrange.utils.WebApiAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class DayCatalog implements ICatalog{
    private String COMMAND_PREFIX = "/api/stats/";
    private final WebApiAdapter apiAdapter;
    private static final String TAG = "myLogs";
    private ArrayList<DatePerson> dateList = new ArrayList<>();
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
    public ArrayList<DatePerson> getDateList() {
        return dateList;
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

        dateList.clear();
        Iterator<JSONObject> iterator = jsonObject.iterator();

        while (iterator.hasNext()){
            ArrayList<DayPerson> personList = new ArrayList<>();
            JSONObject o = iterator.next();
            JSONArray array = (JSONArray) o.get("persons");
            Iterator<JSONObject>it = array.iterator();
            while (it.hasNext()){
                JSONObject a = it.next();
                personList.add(new DayPerson((String) a.get("name"),(int)(long)a.get("rank")));
            }



            String date = o.get("date").toString();
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

            dateList.add(new DatePerson(dd,personList));

        }
    }

}
