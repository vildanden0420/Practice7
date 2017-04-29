package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by vil on 2017-04-29.
 */

public class RestAdapter extends BaseAdapter implements Filterable{
    ArrayList<restaurant> data;
    ArrayList<restaurant> filteredData;
    Context c;
    Filter listFilter;

    public RestAdapter(ArrayList<restaurant> data, Context c){
        this.data = data;
        this.c = c;
        this.filteredData = data;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
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

        restaurant restData = filteredData.get(position);

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


    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint == null || constraint.length()==0){
                results.values = data;
                results.count = data.size();
            }else{
                ArrayList<restaurant> dataList = new ArrayList<restaurant>();

                for(restaurant item : data){
                    if(item.getName().toUpperCase().contains(constraint.toString().toUpperCase())){
                        dataList.add(item);
                    }
                }

                results.values = dataList;
                results.count = dataList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<restaurant>) results.values;

            if(results.count>0){
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }
    }
}
