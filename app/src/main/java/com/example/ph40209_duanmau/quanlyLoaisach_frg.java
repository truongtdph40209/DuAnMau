package com.example.ph40209_duanmau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph40209_duanmau.adapter.LoaiSachAdapter;
import com.example.ph40209_duanmau.dao.LoaiSachDAO;
import com.example.ph40209_duanmau.interFace.itemClick;
import com.example.ph40209_duanmau.model.loaisach;

import java.util.ArrayList;

public class quanlyLoaisach_frg extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSachDAO loaiSachDAO;
    ArrayList<loaisach> list;
    LoaiSachAdapter loaiSachAdapter;
    EditText edt_loaisach;
    int maloai;


    public quanlyLoaisach_frg() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quanly_loaisach_frg, container, false);
        recyclerLoaiSach = view.findViewById(R.id.recycler_loaisach);
        edt_loaisach = view.findViewById(R.id.edt_loaisach);
        Button btn_themLoaisach = view.findViewById(R.id.btn_themLoaisach);
        Button btn_suaLoaisach = view.findViewById(R.id.btn_suaLoaisach);




        loadData();

        btn_themLoaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenloai = edt_loaisach.getText().toString();

                if (loaiSachDAO.themLoaiSach(tenloai)){
                    loadData();
                    edt_loaisach.setText("");

                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_suaLoaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edt_loaisach.getText().toString();
                loaisach loaiSach = new loaisach(maloai, tenloai);
                if (loaiSachDAO.thayDoiLoaiSach(loaiSach)){
                    loadData();
                    edt_loaisach.setText("");
                }else {
                    Toast.makeText(getContext(), "Update không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);


        loaiSachDAO = new LoaiSachDAO(getContext());
        list = loaiSachDAO.getDSLoaiSach();

        loaiSachAdapter = new LoaiSachAdapter(getContext(), list, new itemClick() {
            @Override
            public void onClickLoaisach(loaisach loaiSach) {
                edt_loaisach.setText(loaiSach.getTenloai());
                maloai = loaiSach.getId();
            }
        });
        recyclerLoaiSach.setAdapter(loaiSachAdapter);
    }
}