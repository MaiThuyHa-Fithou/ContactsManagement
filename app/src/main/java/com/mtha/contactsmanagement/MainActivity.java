package com.mtha.contactsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhBa;
    List<DanhBa> lsData = new ArrayList<DanhBa>();
    DanhBaAdapter adapter;
    DanhBaDB  danhBaDb = new DanhBaDB(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDanhBa = findViewById(R.id.lvDanhBa);
        //tao doi tuong DanhBaDB thuc hien initdata

        danhBaDb.initData();
        //select du lieu do vao lsData de hien len listview
        lsData = danhBaDb.getAllDanhBa();
        //khoi tao adapter load view va data
        adapter = new DanhBaAdapter(MainActivity.this,lsData);
        //set adapter cho listview
        lvDanhBa.setAdapter(adapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //refresh adapter
        lsData = danhBaDb.getAllDanhBa();
        adapter.notifyDataSetChanged();
    }
}