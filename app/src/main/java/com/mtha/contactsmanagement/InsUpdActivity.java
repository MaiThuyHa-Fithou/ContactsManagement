package com.mtha.contactsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class InsUpdActivity extends AppCompatActivity {
    EditText etTen, etSDT;
    Button btnLuu, btnDong;
    String action="";
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_upd);
        getViews();
        //lay ra doi tuong intent duoc truyen sang tu adapter
        Intent data =getIntent();
        action = data.getStringExtra("ins_upd");
        //hien thi du lieu len view
        if(action.equals("update")){
            DanhBa db =(DanhBa) data.getExtras()
                    .getSerializable("danhba");
            etTen.setText(db.getTen());
            etSDT.setText(db.getSoDT());
            id = db.getId();
        }
        //kiem tra action la insert hay update
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action.equals("update")){
                    //cap nhat du lieu moi va truyen ve
                    Intent updIntent= new Intent();
                    updIntent.putExtra("action", action);
                    DanhBa danhBa = new DanhBa();
                    danhBa.setId(id);
                    danhBa.setTen(etTen.getText().toString());
                    danhBa.setSoDT(etSDT.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updDanhBa", danhBa);
                    updIntent.putExtras(bundle);
                    //set ket qua tra ve
                    setResult(RESULT_OK,updIntent);
                    finish();
                }
            }
        });
    }

    private void getViews(){
        etTen = findViewById(R.id.etTen);
        etSDT = findViewById(R.id.etDienThoai);
        btnLuu = findViewById(R.id.btnLuu);
        btnDong = findViewById(R.id.btnDong);
    }
}