package com.example.user.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView name, menu1, menu2, menu3, tel, url, regdate;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("나의 맛집");

        init();

    }

    public void onClick(View v){
        Intent intent = getIntent();
        restaurant rest = intent.getParcelableExtra("restaurant");

        if(v.getId()==R.id.btnback){
            finish();
        }else if(v.getId()==R.id.imageView2){
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/"+rest.getTel()));
            startActivity(callIntent);
        }else if(v.getId()==R.id.imageView3){
            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rest.getAddr()));
            startActivity(urlIntent);
        }
    }

    public void init(){
        name = (TextView) findViewById(R.id.txtname);
        menu1 = (TextView) findViewById(R.id.etmenu1);
        menu2 = (TextView) findViewById(R.id.etmenu2);
        menu3 = (TextView) findViewById(R.id.etmenu3);
        tel = (TextView) findViewById(R.id.tvTel);
        url = (TextView) findViewById(R.id.tvURL);
        regdate = (TextView) findViewById(R.id.tvRegdate);

        img = (ImageView)findViewById(R.id.imgno);

        Intent intent = getIntent();
        restaurant rest = intent.getParcelableExtra("restaurant");

        name.setText(rest.getName());
        menu1.setText(rest.getMenu()[0]);
        menu2.setText(rest.getMenu()[1]);
        menu3.setText(rest.getMenu()[2]);
        tel.setText(rest.getTel());
        url.setText(rest.getAddr());
        regdate.setText(rest.getDate());

        if(rest.getOption() == 1){
            img.setImageResource(R.drawable.chicken);
        }
        else if (rest.getOption() == 2){
            img.setImageResource(R.drawable.pizza);
        }
        else if (rest.getOption() == 3){
            img.setImageResource(R.drawable.hamburger);
        }

    }
}
