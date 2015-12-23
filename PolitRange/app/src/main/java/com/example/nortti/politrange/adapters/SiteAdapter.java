package com.example.nortti.politrange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nortti.politrange.R;
import com.example.nortti.politrange.objects.Site;

import java.util.ArrayList;

public class SiteAdapter extends BaseAdapter {
    private ArrayList<Site> spinItem;
    Context c;

    public SiteAdapter(Context c, ArrayList<Site> spinItem){
        this.c = c;
        this.spinItem = spinItem;
    }

    @Override
    public int getCount() {
        return spinItem.size();
    }

    @Override
    public Site getItem(int position) {
        return spinItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.spin_item, null);
        }
        fillView(convertView,position);

        return convertView;
    }

    private void fillView(View v, int position) {
        final Site s = getItem(position);

        TextView tvTitle = (TextView) v.findViewById(R.id.siteTitle);
        tvTitle.setText(s.getName());

       // ImageView imgLogo = (ImageView) v.findViewById(R.id.imgLogo);
        //imgLogo.setImageResource(s.getPic_id());

    }
}
