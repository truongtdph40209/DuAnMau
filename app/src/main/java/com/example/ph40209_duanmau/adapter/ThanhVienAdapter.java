package com.example.ph40209_duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph40209_duanmau.R;
import com.example.ph40209_duanmau.dao.PhieuMuonDAO;
import com.example.ph40209_duanmau.dao.ThanhVienDAO;
import com.example.ph40209_duanmau.model.phieumuon;
import com.example.ph40209_duanmau.model.thanhvien;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<thanhvien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<thanhvien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_matv.setText("Mã TV: " + list.get(position).getMatv());
        holder.txt_hoten.setText("Họ tên: " + list.get(position).getHoten());
        holder.txt_namsinh.setText("Năm sinh: " + list.get(position).getNamsinh());

        holder.txt_update_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });

        holder.txt_delete_thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = thanhVienDAO.delete_tt_ThanhVien(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:{
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    }
                    case 0:{
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case -1:{
                        Toast.makeText(context, "Thành viên có phiếu mượn, không được xóa", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_matv, txt_hoten, txt_namsinh, txt_update_thanhvien, txt_delete_thanhvien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_matv = itemView.findViewById(R.id.matv_recyler_thanhVien);
            txt_hoten = itemView.findViewById(R.id.hoten_recyler_thanhVien);
            txt_namsinh = itemView.findViewById(R.id.namsinh_recyler_thanhvien);
            txt_update_thanhvien = itemView.findViewById(R.id.txt_update_thanhvien);
            txt_delete_thanhvien = itemView.findViewById(R.id.txt_delete_thanhvien);



        }
    }

    public void dialogUpdate(thanhvien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_update_thanhvien, null);
        builder.setView(view);

        TextView txt_matv_ud = view.findViewById(R.id.txt_matv_update);
        EditText edt_hoten_ud = view.findViewById(R.id.edt_hoten_update);
        EditText edt_namsinh_ud = view.findViewById(R.id.edt_namsinh_update);


        txt_matv_ud.setText("Mã TV: "+ thanhVien.getMatv());
        edt_hoten_ud.setText(thanhVien.getHoten());
        edt_namsinh_ud.setText(thanhVien.getNamsinh());

        builder.setNegativeButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edt_hoten_ud.getText().toString();
                String namsinh = edt_namsinh_ud.getText().toString();
                int id = thanhVien.getMatv();

                boolean check = thanhVienDAO.update_TT_ThanhVien(id, hoten, namsinh);
                if (check){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }

    public void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
