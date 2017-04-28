package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vil on 2017-04-29.
 */

public class RestAdapter extends BaseAdapter{
    ArrayList<restaurant> data;
    Context c;

    public RestAdapter(ArrayList<restaurant> data, Context c){
        this.data = data;
        this.c = c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.itemName);
        TextView num = (TextView)convertView.findViewById(R.id.itemNum);
        ImageView img = (ImageView)convertView.findViewById(R.id.itemImg);
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkbox);

        restaurant restData = data.get(position);

        name.setText(restData.getName());
        num.setText(restData.getTel());

        if(restData.getOption()==1){
            img.setImageResource(R.drawable.chicken);
        }else if(restData.getOption()==2){
            img.setImageResource(R.drawable.pizza);
        }else{
            img.setImageResource(R.drawable.hamburger);
        }

        return convertView;
    }

    Comparator<restaurant> nameAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant o1, restaurant o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };

    Comparator<restaurant> optionAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant o1, restaurant o2) {
            return o1.getOption()-o2.getOption();
        }
    };

    public void setNameAscSort(){
        Collections.sort(data, nameAsc);
        this.notifyDataSetChanged();
    }

    public void setOptionAscSort(){
        Collections.sort(data, optionAsc);
        this.notifyDataSetChanged();
    }


}
