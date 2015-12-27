package com.example.nortti.politrange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.objects.DatePerson;
import com.example.nortti.politrange.objects.DayPerson;

import java.util.ArrayList;

public class DayAdapter extends BaseExpandableListAdapter {
    Context c;
    private ArrayList<DatePerson> dayPersonItem;


    public DayAdapter(Context c, ArrayList<DatePerson> dayPersonItem) {
        this.c = c;
        this.dayPersonItem = new ArrayList<DatePerson>();
        this.dayPersonItem.addAll(dayPersonItem);


    }


    @Override
    public int getGroupCount() {
        return dayPersonItem.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<DayPerson>dayPersons = dayPersonItem.get(i).getDayPersons();
        return dayPersons.size();
    }

    @Override
    public Object getGroup(int i) {
        return dayPersonItem.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        ArrayList<DayPerson>dayPersons = dayPersonItem.get(i).getDayPersons();
        return dayPersons.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        DatePerson date = (DatePerson)getGroup(i);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.day_head,null);
        }
        TextView tvHead = (TextView)view.findViewById(R.id.head);
        tvHead.setText(date.getName().trim());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        DayPerson dayPerson = (DayPerson)getChild(i,i1);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.day_item,null);
        }
        TextView tvName = (TextView)view.findViewById(R.id.dayName);
        TextView tvRank = (TextView)view.findViewById(R.id.dayIndex);
        tvName.setText(dayPerson.getName().trim());
        tvRank.setText(String.valueOf(dayPerson.getRank()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
