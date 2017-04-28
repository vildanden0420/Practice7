package com.example.user.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;

/**
 * Created by User on 2017-04-12.
 */

public class restaurant implements Parcelable {

    private String name, tel, addr, date;
    private String[] menu = new String[3];
    private int option;
    private int c;

    protected restaurant(Parcel in) {
        name = in.readString();
        tel = in.readString();
        addr = in.readString();
        menu = in.createStringArray();
        date = in.readString();
        option = in.readInt();
        //c = in.readInt();
    }

    public restaurant(String name, String tel, String addr, String[] menu, String date, int option){
        this.name = name;
        this.tel = tel;
        this.addr = addr;
        this.menu = menu;
        this.date = date;
        this.option = option;
        //this.c = c;
    }

    public static final Creator<restaurant> CREATOR = new Creator<restaurant>() {
        @Override
        public restaurant createFromParcel(Parcel in) {
            return new restaurant(in);
        }

        @Override
        public restaurant[] newArray(int size) {
            return new restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
        dest.writeString(addr);
        dest.writeStringArray(menu);
        dest.writeString(date);
        dest.writeInt(option);

    }


    public String getName(){
        return name;
    }

    public String getTel(){
        return tel;
    }

    public String getAddr(){
        return addr;
    }

    public String[] getMenu(){
        return menu;
    }

    public String getDate(){
        return date;
    }

    public int getOption(){
        return option;
    }



}
