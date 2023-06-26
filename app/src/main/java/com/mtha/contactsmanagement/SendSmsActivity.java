package com.mtha.contactsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.*;

public class SendSmsActivity extends AppCompatActivity {
    EditText etGui, etNhan, etContent;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        getViews();
        Intent data = getIntent();
        String ten = data.getStringExtra("ten");
        String sdt = data.getStringExtra("sdt");
        etGui.setText(ten +"\n"+ sdt);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userReceiver = etNhan.getText().toString();
                String smsContent = etContent.getText().toString();
                //khai bao doi tuong intent de thuc hien viec gui sms
                Intent smsIntent = new Intent(getApplicationContext(),
                        SendSmsActivity.class);
                PendingIntent pend= PendingIntent.getActivity(getApplicationContext(),
                        0,smsIntent,0);
                //khai bao doi tuong smsmanager de thuc hien viec send
                SmsManager sms = SmsManager.getDefault();
                //thuc hien gui tin nhan
                sms.sendTextMessage(userReceiver,null,smsContent,
                        pend,null);
                //hien thi thong bao viec gui tin nhan hoan tat
                Toast.makeText(getApplicationContext(),
                        "message send success!!",
                Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getViews(){
        etGui = findViewById(R.id.etGui);
        etNhan = findViewById(R.id.etNhan);
        etContent = findViewById(R.id.etContent);
        btnSend = findViewById(R.id.btnSend);
    }
}