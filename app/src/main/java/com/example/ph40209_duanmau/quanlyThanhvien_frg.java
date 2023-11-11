package com.example.ph40209_duanmau;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph40209_duanmau.adapter.ThanhVienAdapter;
import com.example.ph40209_duanmau.dao.ThanhVienDAO;
import com.example.ph40209_duanmau.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class quanlyThanhvien_frg extends Fragment {
    ThanhVienDAO thanhVienDAO;
    ArrayList<thanhvien> list;
    ThanhVienAdapter thanhVienAdapter;
    RecyclerView recyclerThanhvien;

    public quanlyThanhvien_frg() {
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
        View view = inflater.inflate(R.layout.fragment_quanly_thanhvien_frg, container, false);
        FloatingActionButton floatadd_thanhVien = view.findViewById(R.id.float_add_thanhVien);
        recyclerThanhvien = view.findViewById(R.id.recyclerView_QLThanhVien);

        loadData();


        floatadd_thanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogThem();
            }
        });




        return view;
    }
    public void showdialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_them_thanhvien, null);
        builder.setView(view);

        EditText edt_hoten = view.findViewById(R.id.edt_hoten);
        EditText edt_namsinh = view.findViewById(R.id.edt_namsinh);



        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edt_hoten.getText().toString();
                String namsinh = edt_namsinh.getText().toString();

                boolean check = thanhVienDAO.themThanhVien(hoten, namsinh);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();

                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }


            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void loadData(){
        thanhVienDAO = new ThanhVienDAO(getContext());
        list = thanhVienDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhvien.setLayoutManager(linearLayoutManager);

        thanhVienAdapter = new ThanhVienAdapter(getContext(), list, thanhVienDAO);
        recyclerThanhvien.setAdapter(thanhVienAdapter);
    }
}