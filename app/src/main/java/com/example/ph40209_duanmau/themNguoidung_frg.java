package com.example.ph40209_duanmau;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph40209_duanmau.dao.ThuThuDAO;
import com.example.ph40209_duanmau.model.thuthu;

import java.util.ArrayList;


public class themNguoidung_frg extends Fragment {

    private ThuThuDAO thuThuDAO;
    private ArrayList<thuthu> thuThuList;


    public themNguoidung_frg() {
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
        View view = inflater.inflate(R.layout.fragment_them_nguoidung_frg, container, false);


        EditText edt_matt = view.findViewById(R.id.edt_matt);
        EditText edt_hoten = view.findViewById(R.id.edt_hoten);
        EditText edt_matkhau = view.findViewById(R.id.edt_matkhau);
        Button btn_add_nguoidung = view.findViewById(R.id.btn_add_nguoidung);
        thuThuDAO = new ThuThuDAO(getContext());

        btn_add_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("AAA","error");
                String matt = edt_matt.getText().toString();
                String hoten = edt_hoten.getText().toString();
                String matkhau = edt_matkhau.getText().toString();
                boolean check = false;
                try {
                    check = thuThuDAO.addThuThu(matt, hoten, matkhau);
                }catch (Exception e){
                    Log.e("AAA",e+"");

                }

                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}