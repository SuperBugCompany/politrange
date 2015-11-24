package com.example.nortti.politrange.Daily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nortti.politrange.General.Gen;
import com.example.nortti.politrange.R;

import java.util.ArrayList;

public class DayAdapter extends BaseAdapter {
    Context c;
    private ArrayList<Day> dayItem;

    public DayAdapter(Context c, ArrayList<Day> dayItem) {
        this.c = c;
        this.dayItem = dayItem;
    }


    @Override
    public int getCount() {
        return dayItem.size();
    }

    @Override
    public Day getItem(int position) {
        return dayItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.day_item,null);
        }
        fillView(convertView,position);

        return convertView;
    }
    private void fillView(View v, int position) {
        final Day d = getItem(position);

        TextView tvDay = (TextView) v.findViewById(R.id.dayDate);
        tvDay.setText(d.getDayDate());

        TextView tvIndex = (TextView) v.findViewById(R.id.dayIndex);
        tvIndex.setText(d.getDayNum());


    }
}
