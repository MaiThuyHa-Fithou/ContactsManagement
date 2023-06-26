package com.mtha.contactsmanagement;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        lsData.clear();
        lsData.addAll(danhBaDb.getAllDanhBa());
        adapter.notifyDataSetChanged();
        lvDanhBa.invalidateViews();
        lvDanhBa.refreshDrawableState();
    }


    public void onInsDanhBaClick(View view) {
        Intent insIntent = new Intent(this, InsUpdActivity.class);
        insIntent.putExtra("ins_upd", "insert");
        insDanhBa.launch(insIntent);
    }
    ActivityResultLauncher<Intent> insDanhBa= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //insert du lieu
                    if(result.getResultCode()==RESULT_OK){
                        Intent data = result.getData();
                        if(data.getStringExtra("action").equals("insert")){
                            //insert du lieu vao database
                            DanhBa db =(DanhBa) data.getExtras().getSerializable("ins");
                            danhBaDb.insDanhBa(db);
                        }
                    }
                }
            });
}