package com.example.ph40209_duanmau;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.example.ph40209_duanmau.adapter.PhieuMuonAdapter;
import com.example.ph40209_duanmau.dao.PhieuMuonDAO;
import com.example.ph40209_duanmau.dao.SachDAO;
import com.example.ph40209_duanmau.dao.ThanhVienDAO;
import com.example.ph40209_duanmau.model.phieumuon;
import com.example.ph40209_duanmau.model.sach;
import com.example.ph40209_duanmau.model.thanhvien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class quanlyPhieumuon_frg extends Fragment {


    RecyclerView recycler_qlPhieumuon;
    FloatingActionButton float_add;
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<phieumuon> list;
    public quanlyPhieumuon_frg() {
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
        View view = inflater.inflate(R.layout.fragment_quanly_phieumuon_frg, container, false);
        recycler_qlPhieumuon = view.findViewById(R.id.recyclerView_QLphieumuon);
        float_add = view.findViewById(R.id.float_add);

        loadData();
        float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogThem();
            }
        });

        return view;
    }

    private void showDiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_them_phieumuon, null);



        Spinner spn_thanhvien = view.findViewById(R.id.spn_thanhvien);
        Spinner spn_sach = view.findViewById(R.id.spn_sach);
        getDataThanhVien(spn_thanhvien);
        getDataSach(spn_sach);
        EditText edt_tienthue = view.findViewById(R.id.edt_tienthue);
        builder.setView(view);




        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



                HashMap<String, Object> hsTV = (HashMap<String, Object>) spn_thanhvien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                HashMap<String, Object> hsSach = (HashMap<String, Object>) spn_sach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tienThue = Integer.parseInt(edt_tienthue.getText().toString());

                themPhieuMuon(matv,masach,tienThue);

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    private void getDataThanhVien(Spinner spn_thanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<thanhvien> list = thanhVienDAO.getDSThanhVien();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for(thanhvien tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"hoten"}, new int[]{android.R.id.text1});
        spn_thanhVien.setAdapter(simpleAdapter);

    }
    private void getDataSach(Spinner spn_sach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<sach> list = sachDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for(sach sc : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", sc.getMasach());
            hs.put("tensach", sc.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHM, android.R.layout.simple_list_item_1, new String[]{"tensach"}, new int[]{android.R.id.text1});
        spn_sach.setAdapter(simpleAdapter);

    }

    private void themPhieuMuon(int matv , int masach, int tienThue){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");

        Date currenTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currenTime);

        phieumuon phieumuon = new phieumuon(matv, matt, masach, ngay, 0, tienThue);

        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieumuon);
        if (kiemtra){
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }

    }
    private void loadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();

        PhieuMuonAdapter adapter = new PhieuMuonAdapter(getContext(), list, phieuMuonDAO);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_qlPhieumuon.setLayoutManager(linearLayoutManager);
        recycler_qlPhieumuon.setAdapter(adapter);
    }
}