package com.example.ph40209_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph40209_duanmau.R;
import com.example.ph40209_duanmau.dao.PhieuMuonDAO;
import com.example.ph40209_duanmau.model.phieumuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<phieumuon> list;
    private PhieuMuonDAO phieuMuonDAO;

    public PhieuMuonAdapter(Context context, ArrayList<phieumuon> list, PhieuMuonDAO phieuMuonDAO) {
        this.context = context;
        this.list = list;
        this.phieuMuonDAO = phieuMuonDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieumuon, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_mapm.setText("Mã PM: " +list.get(position).getMapm());
        holder.txt_matv.setText("Mã TV: " +list.get(position).getMatv());
        holder.txt_tentv.setText("Tên TV: " +list.get(position).getTentv());
        holder.txt_matt.setText("Mã TT: " +list.get(position).getMatt());
        holder.txt_tentt.setText("Tên TT: " +list.get(position).getTentt());
        holder.txt_masach.setText("Mã Sách: " +list.get(position).getMasach());
        holder.txt_tensach.setText("Tên Sách: " +list.get(position).getTensach());
        holder.txt_ngay.setText("Ngày: " +list.get(position).getNgay());
        String trangThai;
        if (list.get(position).getTrasach() == 1){
            trangThai = "Đã trả sách";
            holder.btn_trasach.setVisibility(View.GONE);
        }else {
            trangThai = "Chưa trả sách";
            holder.btn_trasach.setVisibility(View.VISIBLE);

        }
        holder.txt_trangThai.setText("Trạng Thái: " +trangThai);


        holder.txt_tienThue.setText("Tiền Thuê: "+ list.get(position).getTienthue());


        holder.btn_trasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if (kiemtra){
                    list.clear();
                    list = phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_mapm, txt_matv, txt_tentv, txt_matt, txt_tentt, txt_masach, txt_tensach, txt_ngay, txt_trangThai, txt_tienThue;
        Button btn_trasach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mapm = itemView.findViewById(R.id.txt_maPM);
            txt_matv = itemView.findViewById(R.id.txt_maTV);
            txt_tentv = itemView.findViewById(R.id.txt_tenTV);
            txt_matt = itemView.findViewById(R.id.txt_maTT);
            txt_tentt = itemView.findViewById(R.id.txt_tenTT);
            txt_masach = itemView.findViewById(R.id.txt_maSach);
            txt_tensach = itemView.findViewById(R.id.txt_tenSach);
            txt_ngay = itemView.findViewById(R.id.txt_ngay);
            txt_trangThai = itemView.findViewById(R.id.txt_trangThai);
            txt_tienThue = itemView.findViewById(R.id.txt_tienThue);
            btn_trasach = itemView.findViewById(R.id.btn_traSach);

        }
    }
}
