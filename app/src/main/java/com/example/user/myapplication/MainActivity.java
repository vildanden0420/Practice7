package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int _REST_REQUEST = 1;
    ListView listView;
    EditText editText;
    Button delete;

    //ArrayList<String> restName = new ArrayList<>();
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

        }else{
            if(delete.getText().toString().equals("선택")){
                delete.setText("삭제");
                adapter.setCheakable(true);
                adapter.notifyDataSetChanged();
            }else{
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
//                dlg.setTitle("삭제확인")
//                        .setMessage("정말 삭제하시겠습니까?")
//                        .setIcon(R.drawable.potato)
//                        .setPositiveButton("취소", null)
//                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                for(int i=0; i < restList.size(); i++){
//                                    if(restList.get(i).getChecked()){
//                                        restList.remove(i--);
//                                    }
//
//                                }
//                            }
//                        }).show();
                Boolean delcheck=false;
                for(int i = 0; i<restList.size();i++){
                    if(restList.get(i).getChecked()){
                        restList.remove(i--);
                        delcheck=true;
                    }
                }

                if(delcheck)
                    Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

                delete.setText("선택");
                adapter.setCheakable(false);
                adapter.notifyDataSetChanged();
            }
        }

    }

    public void init(){
        listView = (ListView)findViewById(R.id.listview);
        editText = (EditText)findViewById(R.id.editText);
        delete = (Button)findViewById(R.id.b4);

        adapter = new RestAdapter(restList,this);
        listView.setAdapter(adapter);

        EditText editTextFilter = (EditText)findViewById(R.id.editText);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText = s.toString();
                ((RestAdapter)listView.getAdapter()).getFilter().filter(filterText);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("restaurant", restList.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REST_REQUEST) {
            if (resultCode == RESULT_OK) {
                restaurant rest = data.getParcelableExtra("restaurant");
                this.restList.add(rest);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
