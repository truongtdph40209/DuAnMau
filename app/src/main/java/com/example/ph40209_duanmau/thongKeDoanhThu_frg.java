package com.example.ph40209_duanmau;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ph40209_duanmau.dao.ThongKeTop10DAO;

import java.util.Calendar;


public class thongKeDoanhThu_frg extends Fragment {



    public thongKeDoanhThu_frg() {
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
        View view = inflater.inflate(R.layout.fragment_thong_ke_doanh_thu_frg, container, false);
        EditText edt_ngayBatdau = view.findViewById(R.id.edt_ngayBatdau);
        EditText edt_ngayKetthuc = view.findViewById(R.id.edt_ngayKetthuc);
        Button btn_thongKe = view.findViewById(R.id.btn_thongKe);
        TextView txt_ketquaThongke = view.findViewById(R.id.txt_ketQuaThongKe);


        Calendar calendar = Calendar.getInstance();

        edt_ngayBatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int i, int i1, int i2) {//i = nam, i1 = thang, i2 = ngay
                                String ngay = "";
                                String thang = "";
                                if (i2 < 10){
                                    ngay = "0" + i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }

                                if ((i1 + 1) < 10){
                                    thang = "0" + (i1 + 1);
                                }else {
                                    thang = String.valueOf(i1 + 1);
                                }

                                edt_ngayBatdau.setText(i + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();

            }
        });

        edt_ngayKetthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                                String ngay = "";
                                String thang = "";
                                if (i2 < 10){
                                    ngay = "0" + i2;
                                }else {
                                    ngay = String.valueOf(i2);
                                }

                                if ((i1 + 1) < 10){
                                    thang = "0" + (i1 + 1);
                                }else {
                                    thang = String.valueOf(i1 + 1);
                                }

                                edt_ngayKetthuc.setText(i + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)

                );
                datePickerDialog.show();
            }
        });

        btn_thongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeTop10DAO thongKeTop10DAO = new ThongKeTop10DAO(getContext());
                String ngaybatdau = edt_ngayBatdau.getText().toString();
                String ngayketthuc = edt_ngayKetthuc.getText().toString();

                int doanhthu = thongKeTop10DAO.getDoanhThu(ngaybatdau, ngayketthuc);

                txt_ketquaThongke.setText("Doanh thu: " + doanhthu + "VND");

            }
        });




        return view;
    }
}