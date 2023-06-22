package com.mtha.contactsmanagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DanhBaAdapter extends ArrayAdapter<DanhBa> {
    Context context;
    List<DanhBa> lsData;
    public DanhBaAdapter(@NonNull Context context, List<DanhBa> lsDba) {
        super(context,0 ,lsDba);
        this.context = context;
        lsData = lsDba;
    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Nullable
    @Override
    public DanhBa getItem(int position) {
        return lsData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =convertView;
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_danhba,
                    parent,false);
        }
        DanhBa danhBa = lsData.get(position);
        //lay doi tuong cac view tren layout
        TextView tvTen = view.findViewById(R.id.tvTen);
        TextView tvSDT = view.findViewById(R.id.tvDienThoai);

        ImageButton btnSua = view.findViewById(R.id.btnSua);
        ImageButton btnXoa = view.findViewById(R.id.btnXoa);
        ImageButton btnCall = view.findViewById(R.id.btnCall);
        ImageButton btnSMS = view.findViewById(R.id.btnSMS);
        //data binding
        tvTen.setText(danhBa.getTen());
        tvSDT.setText(danhBa.getSoDT());
        //xu ly su kien tren tung nut lenh
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //day doi tuong danhba sang activity cap nhat
                Intent data  = new Intent(context,InsUpdActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("danhba", danhBa);
                data.putExtras(bundle);
                context.startActivity(data);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("phone:" + danhBa.getSoDT()));
                context.startActivity(intentCall);
            }
        });
        return view;
    }
}
