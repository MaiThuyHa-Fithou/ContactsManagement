package com.mtha.contactsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.*;

public class SendSmsActivity extends AppCompatActivity {
    EditText etGui, etNhan, etContent;
    Button btnSend;
    SendSmsActivity smsActivity = this;
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
                int smsPermission = ContextCompat.checkSelfPermission(SendSmsActivity.this,Manifest.permission.SEND_SMS);
                if(smsPermission==PackageManager.PERMISSION_GRANTED){
                    sendSms();
                    finish();
                }else{
                    ActivityCompat.requestPermissions(SendSmsActivity.this,new String[]{Manifest.permission.SEND_SMS},0);
                }
            }
        });
    }

    private void sendSms(){
        String userReceiver = etNhan.getText().toString();
        String smsContent = etContent.getText().toString();
        //khai bao doi tuong intent de thuc hien viec gui sms
        PendingIntent piSent = PendingIntent.getBroadcast(this,0,new Intent("SENT_SMS"),0);
        BroadcastReceiver sendSmsConfirm = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(),
                                "SMS sent successfully", Toast.LENGTH_SHORT).show();
                        break;

                    default: //all other codes are error
                        Toast.makeText(getBaseContext(),
                                "Error: SMS was not sent", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        smsActivity.registerReceiver(sendSmsConfirm,new IntentFilter("SENT_SMS"));
        //khai bao doi tuong smsmanager de thuc hien viec send
        SmsManager sms = SmsManager.getDefault();
        //thuc hien gui tin nhan
        sms.sendTextMessage(userReceiver,null,smsContent,
                piSent,null);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 0:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   sendSms();
                   finish();
                }else{
                    Toast.makeText(this,"Permission denied!!", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void getViews(){
        etGui = findViewById(R.id.etGui);
        etNhan = findViewById(R.id.etNhan);
        etContent = findViewById(R.id.etContent);
        btnSend = findViewById(R.id.btnSend);
    }
}