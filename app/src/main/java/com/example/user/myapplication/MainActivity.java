package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int _REST_REQUEST = 1;
    ListView listView;
    TextView tv;

    ArrayList<String> restName = new ArrayList<>();
    ArrayList<restaurant> restList = new ArrayList<>();
    RestAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        init();

    }

    public void onClick(View v){
        if(v.getId() == R.id.b1){
            Intent intent = new Intent(this, Main2Activity.class);
            this.startActivityForResult(intent, _REST_REQUEST);
        }else if(v.getId() == R.id.b2){
            adapter.setNameAscSort();
        }else if(v.getId() == R.id.b3){
            adapter.setOptionAscSort();
        }

    }

    public void init(){
        listView = (ListView)findViewById(R.id.listview);
        tv = (TextView)findViewById(R.id.tv);

        adapter = new RestAdapter(restList,this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("restaurant", restList.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int pos = position;
                dlg.setTitle("삭제확인")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setIcon(R.drawable.potato)
                        .setPositiveButton("취소", null)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restList.remove(pos);
                                restName.remove(pos);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();
                return true;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REST_REQUEST) {
            if (resultCode == RESULT_OK) {
                restaurant rest = data.getParcelableExtra("restaurant");
                this.restList.add(rest);
                restName.add(rest.getName());
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
