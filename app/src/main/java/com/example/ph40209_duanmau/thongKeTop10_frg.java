package com.example.ph40209_duanmau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ph40209_duanmau.adapter.Top10Adapter;
import com.example.ph40209_duanmau.dao.PhieuMuonDAO;
import com.example.ph40209_duanmau.dao.ThongKeTop10DAO;
import com.example.ph40209_duanmau.model.phieumuon;
import com.example.ph40209_duanmau.model.sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class thongKeTop10_frg extends Fragment {

    RecyclerView recycler_ThongKeTop10;

    ThongKeTop10DAO thongKeTop10DAO;
    ArrayList<sach> list;

    public thongKeTop10_frg() {
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
        View view = inflater.inflate(R.layout.fragment_thong_ke_top10_frg, container, false);
        recycler_ThongKeTop10 = view.findViewById(R.id.recycler_top10);

        thongKeTop10DAO = new ThongKeTop10DAO(getContext());
        list = thongKeTop10DAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_ThongKeTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter top10Adapter = new Top10Adapter(getContext(),list);
        recycler_ThongKeTop10.setAdapter(top10Adapter);


        return view;
    }
}