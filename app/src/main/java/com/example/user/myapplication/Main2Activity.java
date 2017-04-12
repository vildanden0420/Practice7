package com.example.user.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText etname, ettel, etmenu1,etmenu2, etmenu3, etaddr;
    RadioButton radio1, radio2, radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("나의 맛집");

        init();

    }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void  onClick(View v){
            if(v.getId() ==R.id.btnAdd){

                Intent intent = new Intent();

                int option;

                if(radio1.isChecked()){
                    option =1;
                }else if(radio2.isChecked()){
                    option=2;
                }else{
                    option=3;
                }

                String name = etname.getText().toString();
                String tel = ettel.getText().toString();
                String addr = etaddr.getText().toString();
                String menu[] = new String[3];
                menu[0] = etmenu1.getText().toString();
                menu[1] = etmenu2.getText().toString();
                menu[2] = etmenu3.getText().toString();
                String date = getDate();

                restaurant rest = new restaurant(name, tel, addr, menu, date, option);

                intent.putExtra("restaurant", rest);
                setResult(RESULT_OK, intent);
                finish();

            }else if(v.getId() == R.id.btnCancel){

                finish();
        }

    }

    public void init(){
        etname = (EditText)findViewById(R.id.etname);
        ettel = (EditText)findViewById(R.id.ettel);
        etmenu1 = (EditText)findViewById(R.id.etmenu1);
        etmenu2 = (EditText)findViewById(R.id.etmenu2);
        etmenu3 = (EditText)findViewById(R.id.etmenu3);
        etaddr = (EditText)findViewById(R.id.etaddr);

        radio1 = (RadioButton)findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        radio3 = (RadioButton)findViewById(R.id.radio3);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate(){
        SimpleDateFormat curDate = new SimpleDateFormat("yyyyMMdd");
        String date = curDate.format(new Date());
        return date;
    }

}
