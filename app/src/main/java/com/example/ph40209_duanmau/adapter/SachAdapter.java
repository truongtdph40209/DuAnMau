package com.example.ph40209_duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph40209_duanmau.R;
import com.example.ph40209_duanmau.dao.SachDAO;
import com.example.ph40209_duanmau.model.loaisach;
import com.example.ph40209_duanmau.model.sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<sach> list;
    private ArrayList<HashMap<String, Object>> listHM;
    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<sach> list, ArrayList<HashMap<String, Object>> listHM, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_masach.setText("Mã sách: " + list.get(position).getMasach());
        holder.txt_tensach.setText("Tên sách: " + list.get(position).getTensach());
        holder.txt_giathue.setText("Giá thuê: " + list.get(position).getGiathue() + " VND");
        holder.txt_maloai.setText("Mã loại: " + list.get(position).getMaloai());
        holder.txt_tenloai.setText("Tên loại: " + list.get(position).getTenloai());

        holder.txt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogSUA(list.get(holder.getAdapterPosition()));
            }
        });

        holder.txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sachDAO.deleteSach(list.get(holder.getAdapterPosition()).getMasach());
                switch (check){
                    case 1:{
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;

                    }
                    case 0:{
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case -1:{
                        Toast.makeText(context, "Không được phép xóa, đã có sách ở phiếu mượn", Toast.LENGTH_SHORT).show();
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

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_masach, txt_tensach, txt_giathue, txt_maloai, txt_tenloai, txt_delete, txt_update;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_masach = itemView.findViewById(R.id.masach_recyler_sach);
            txt_tensach = itemView.findViewById(R.id.tensach_recyler_sach);
            txt_giathue = itemView.findViewById(R.id.giathue_recyler_sach);
            txt_maloai = itemView.findViewById(R.id.maloai_recyler_sach);
            txt_tenloai = itemView.findViewById(R.id.tenloai_recyler_sach);
            txt_delete = itemView.findViewById(R.id.txt_delete_sach);
            txt_update = itemView.findViewById(R.id.txt_update_sach);


        }
    }

    private void showdialogSUA(sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_sach, null);
        builder.setView(view);

        EditText edt_tenSach = view.findViewById(R.id.edt_tensach_update);
        EditText edt_tien = view.findViewById(R.id.edt_tien_update);
        TextView txt_masach = view.findViewById(R.id.txt_masach_update);
        Spinner spn_loaisach = view.findViewById(R.id.spn_loaisach_update);

        txt_masach.setText("Mã sách: " + sach.getMasach());
        edt_tenSach.setText(sach.getTensach());
        edt_tien.setText(String.valueOf(sach.getGiathue()));

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spn_loaisach.setAdapter(simpleAdapter);


        int index = 0;
        int postion = -1;
        for (HashMap<String, Object> item : listHM){
            if ((int)item.get("maloai") ==  sach.getMaloai()){
                postion = index;

            }
            index ++;
        }
        spn_loaisach.setSelection(postion);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach = edt_tenSach.getText().toString();
                int tien = Integer.parseInt(edt_tien.getText().toString());

                HashMap<String ,Object> hs = (HashMap<String, Object>) spn_loaisach.getSelectedItem();
                int maloai = (int)hs.get("maloai");

                boolean check = sachDAO.capnhatSach(sach.getMasach(),tensach, tien, maloai);
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
    private void loadData(){
        list.clear();
        list = sachDAO.getDSDauSach();
        notifyDataSetChanged();
    }
}
