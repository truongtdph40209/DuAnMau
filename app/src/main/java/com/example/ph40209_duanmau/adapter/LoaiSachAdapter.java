package com.example.ph40209_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph40209_duanmau.R;
import com.example.ph40209_duanmau.dao.LoaiSachDAO;
import com.example.ph40209_duanmau.interFace.itemClick;
import com.example.ph40209_duanmau.model.loaisach;
import com.example.ph40209_duanmau.model.phieumuon;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<loaisach> list;
    private itemClick itemclick;

    public LoaiSachAdapter(Context context, ArrayList<loaisach> list, itemClick itemclick) {
        this.context = context;
        this.list = list;
        this.itemclick = itemclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_tenLoai.setText("Tên sách: " + list.get(position).getTenloai());
        holder.txt_maLoai.setText("Mã Loai: "+list.get(position).getId());
        holder.txt_delete_loaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaisach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:{
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();
                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case -1:{
                        Toast.makeText(context, "Không thể xóa loại sách này", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 0 :{
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                    default :{
                        break;
                    }
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaisach loaiSach = list.get(holder.getAdapterPosition());
                itemclick.onClickLoaisach(loaiSach);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    TextView txt_maLoai, txt_tenLoai, txt_delete_loaisach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_maLoai =  itemView.findViewById(R.id.txt_maloai);
            txt_tenLoai =  itemView.findViewById(R.id.txt_tenloai);
            txt_delete_loaisach =  itemView.findViewById(R.id.txt_delete_loaisach);





        }
    }

}
