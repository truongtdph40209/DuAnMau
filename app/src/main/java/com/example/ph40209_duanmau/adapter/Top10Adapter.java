package com.example.ph40209_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph40209_duanmau.R;
import com.example.ph40209_duanmau.dao.PhieuMuonDAO;
import com.example.ph40209_duanmau.model.phieumuon;
import com.example.ph40209_duanmau.model.sach;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{
    private Context context;
    private ArrayList<sach> list;

    public Top10Adapter(Context context, ArrayList<sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_masach.setText("Mã sách: " + list.get(position).getMasach());
        holder.txt_tensach.setText("Tên sách: " + list.get(position).getTensach());
        holder.txt_soluongmuon.setText("Số lượng mượn: " +list.get(position).getSoluongdamuon());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_masach, txt_tensach, txt_soluongmuon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_masach = itemView.findViewById(R.id.masach_recylerTop10);
            txt_tensach = itemView.findViewById(R.id.tensach_recylerTop10);
            txt_soluongmuon = itemView.findViewById(R.id.soluongmuon_recylerTop10);

        }
    }
}
