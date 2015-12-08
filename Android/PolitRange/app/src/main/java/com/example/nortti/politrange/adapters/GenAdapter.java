package com.example.nortti.politrange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nortti.politrange.General.Gen;
import com.example.nortti.politrange.R;

import java.util.ArrayList;

public class GenAdapter extends BaseAdapter {
    Context c;
    private ArrayList<Gen> genItem;

    public GenAdapter(Context c, ArrayList<Gen> genItem) {
        this.c = c;
        this.genItem = genItem;
    }

    @Override
    public int getCount() {
        return genItem.size();
    }

    @Override
    public Gen getItem(int position) {
        return genItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.gen_item,null);
        }
        fillView(convertView,position);

        return convertView;
    }

    private void fillView(View v, int position) {
        final Gen g = getItem(position);

        TextView tvName = (TextView) v.findViewById(R.id.genName);
        tvName.setText(g.getGenName());

        TextView tvIndex = (TextView) v.findViewById(R.id.genIndex);
        tvIndex.setText(g.getGenIndex());


    }
}
