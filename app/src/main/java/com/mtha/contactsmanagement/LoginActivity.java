package com.mtha.contactsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText txtUser, txtPwd;
    CheckBox chkRemember;
    //khai bao ten file
    public static final String MY_LOGIN="myLogin";
    SharedPreferences myPrefer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
        readFile();
    }

    private void getViews(){
        txtUser = findViewById(R.id.txtUser);
        txtPwd = findViewById(R.id.txtPwd);
        chkRemember = findViewById(R.id.chkRemember);
    }

    private void saveFile(){
        //khoi tao doi tuong sharedpreferences
        myPrefer = getSharedPreferences(MY_LOGIN, Activity.MODE_PRIVATE);
        //thuc hien lay ra doi tuong Editor de put du lieu can ghi
        SharedPreferences.Editor editor = myPrefer.edit();
        editor.putBoolean("remember", chkRemember.isChecked());
        editor.putString("user", txtUser.getText().toString());
        editor.putString("pwd", txtPwd.getText().toString());
        editor.commit();
    }
    private void readFile(){
        //khoi tao doi tuong sharedpreferences de doc file
        myPrefer = getSharedPreferences(MY_LOGIN, Activity.MODE_PRIVATE);
        //thuc hien doc du lieu tu file
        boolean isCheck = myPrefer.getBoolean("remember", false);
        String name = myPrefer.getString("user",null);
        String pwd = myPrefer.getString("pwd", null);
        //hien thi len view
        chkRemember.setChecked(isCheck);
        txtUser.setText(name);
        txtPwd.setText(pwd);
    }

    public void onLoginClick(View view) {
        if(chkRemember.isChecked()){
            //thuc hien luu file
            saveFile();
        }
    }
}