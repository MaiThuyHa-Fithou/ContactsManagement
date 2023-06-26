package com.mtha.contactsmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DanhBaDB extends SQLiteOpenHelper {
    public static final String DB_NAME="contact.db";
    public static final int DB_VERSION=1;
    //dinh nghia ten bang, ten cac cot
    public static final String TB_NAME="tbl_contact";
    public static final String ID="id";
    public static final String TEN="ten";
    public static final String SODT="soDT";
    Context context;
    public DanhBaDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //viet cau lenh tao bang
        String sql ="CREATE TABLE " + TB_NAME + "( " + ID+" INTEGER PRIMARY KEY, " +
                TEN +" TEXT, " + SODT +" TEXT )";
        //goi phuong thuc execSQL de thuc thi
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa bang neu bang da ton tai trong db
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        //goi lai ham onCreate de nang cap phien ban va tao lai bang
        onCreate(db);
    }

    public void initData(){
        //kiem tra xem database da co du lieu chua
        int count = getDbCount();
        if(count==0){
            //tao du lieu mau
            DanhBa db1 = new DanhBa("Ha","098775432");
            DanhBa db2 = new DanhBa("Tuan", "098654321");
            //insert du lieu vao database
            if(insDanhBa(db1)==-1||insDanhBa(db2)==-1)
                Toast.makeText(this.context,"Insert failed! ",
                        Toast.LENGTH_LONG).show();
        }
    }

    public long insDanhBa(DanhBa db){
        //tao doi tuong ContentValues de chua gia tri can insert
        ContentValues values = new ContentValues();
        values.put(TEN,db.getTen());
        values.put(SODT, db.getSoDT());
        //thuc hien insert du lieu
        return this.getWritableDatabase().
                insert(TB_NAME,null,values);
    }

    public int udpDanhBa(DanhBa db, int id){
        ContentValues values = new ContentValues();
        values.put(TEN, db.getTen());
        values.put(SODT,db.getSoDT());
        String whereArg[] ={id+""};
        return this.getWritableDatabase().update(TB_NAME,values,
                ID+"=?", whereArg);
    }

    public int delDanhBa(int id){
        String whereArg[]={id+""};
        return this.getWritableDatabase().delete(TB_NAME,
                ID+"=?", whereArg);
    }

    public int delDanhBa(String ten){
        String whereArg[]={ten};
        return this.getWritableDatabase().delete(TB_NAME,
                TEN+"=?", whereArg);
    }
    public List<DanhBa> getAllDanhBa(){
        List<DanhBa> kq = new ArrayList<DanhBa>();
        Cursor cursor = this.getReadableDatabase().
                rawQuery("SELECT * FROM " + TB_NAME, null);
        //duyet qua danh sach ban ghi co trong con tro
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                //thuc hien insert du lieu vao list kq
                do{
                    DanhBa db = new DanhBa();
                    db.setId(cursor.getInt(0));
                    db.setTen(cursor.getString(1));
                    db.setSoDT(cursor.getString(2));
                    kq.add(db);
                }while(cursor.moveToNext());

            }
        }
        cursor.close();//dong con tro sau khi ket thuc truy van
        return kq;
    }

    public int getDbCount(){
        Cursor cursor = this.getReadableDatabase().
                rawQuery("SELECT * FROM "+ TB_NAME,null);
        return cursor.getCount();
    }
}
