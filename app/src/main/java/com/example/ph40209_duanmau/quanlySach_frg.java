package com.example.ph40209_duanmau;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ph40209_duanmau.adapter.SachAdapter;
import com.example.ph40209_duanmau.dao.LoaiSachDAO;
import com.example.ph40209_duanmau.dao.SachDAO;
import com.example.ph40209_duanmau.model.loaisach;
import com.example.ph40209_duanmau.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class quanlySach_frg extends Fragment {
    RecyclerView recyclerSach;
    ArrayList<sach> list;
    SachDAO sachDAO;
    FloatingActionButton flt_add_sach;


    public quanlySach_frg() {
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
        View view = inflater.inflate(R.layout.fragment_quanly_sach_frg, container, false);
        flt_add_sach = view.findViewById(R.id.float_add_sach);
        recyclerSach = view.findViewById(R.id.recyclerView_QLsach);
        sachDAO = new SachDAO(getContext());

        loadData();

        flt_add_sach.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.item_them_sach, null);
        builder.setView(view);

        EditText edt_tensach = view.findViewById(R.id.edt_tensach);
        EditText edt_tien = view.findViewById(R.id.edt_tien);
        Spinner spn_loaisach = view.findViewById(R.id.spn_loaisach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaisach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );

        spn_loaisach.setAdapter(simpleAdapter);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edt_tensach.getText().toString();
                int tien = Integer.parseInt(edt_tien.getText().toString());

                HashMap<String ,Object> hs = (HashMap<String, Object>) spn_loaisach.getSelectedItem();
                int maloai = (int)hs.get("maloai");

                boolean check = sachDAO.themSachMoi(tensach, tien, maloai);
                if (check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private ArrayList<HashMap<String, Object>> getDSLoaisach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<loaisach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String , Object>> listHM = new ArrayList<>();

        for(loaisach loai : list){
            HashMap<String , Object> hs = new HashMap<>();
            hs.put("maloai", loai.getId());
            hs.put("tenloai", loai.getTenloai());
            listHM.add(hs);
        }

        return listHM;
    }

    private void loadData(){
        list = sachDAO.getDSDauSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);

        SachAdapter sachAdapter = new SachAdapter(getContext(), list, getDSLoaisach(), sachDAO);
        recyclerSach.setAdapter(sachAdapter);
    }
}