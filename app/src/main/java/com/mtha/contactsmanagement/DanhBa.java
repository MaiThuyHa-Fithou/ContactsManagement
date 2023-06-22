package com.mtha.contactsmanagement;

import java.io.Serializable;

public class DanhBa implements Serializable {
    int id;
    String ten;
    String soDT;


    public DanhBa(String ten, String soDT) {
        this.ten = ten;
        this.soDT = soDT;
    }

    public DanhBa(){}
    public DanhBa(int id, String ten, String soDT) {
        this.ten = ten;
        this.soDT = soDT;
        this.id= id;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
