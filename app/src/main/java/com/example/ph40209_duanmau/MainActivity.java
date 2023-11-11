package com.example.ph40209_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph40209_duanmau.dao.SachDAO;
import com.example.ph40209_duanmau.dao.ThuThuDAO;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản lí phiếu mượn");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frmNav, new quanlyPhieumuon_frg()).commit();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.thoat){
                    Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                    finish();
                }else if (item.getItemId()==R.id.ql_phieumuon) {
                    quanlyPhieumuon_frg ql_phieumuon = new quanlyPhieumuon_frg();
                    replaceFragment(ql_phieumuon);
                    Toast.makeText(MainActivity.this, "Quản lý phiếu mượn", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId()==R.id.ql_loaisach) {
                    quanlyLoaisach_frg ql_loaisach = new quanlyLoaisach_frg();
                    replaceFragment(ql_loaisach);
                    Toast.makeText(MainActivity.this, "Quản lý loại sách", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId()==R.id.ql_sach) {
                    quanlySach_frg ql_sach = new quanlySach_frg();
                    replaceFragment(ql_sach);
                    Toast.makeText(MainActivity.this, "Quản lý sách", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId()==R.id.ql_thanhvien) {
                    quanlyThanhvien_frg ql_thanhvien = new quanlyThanhvien_frg();
                    replaceFragment(ql_thanhvien);
                    Toast.makeText(MainActivity.this, "Quản lý thành viên", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId()==R.id.doimk) {
                    showDoiMK();
                    Toast.makeText(MainActivity.this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId()==R.id.top10_sach) {
                    thongKeTop10_frg thongKeTop10_frg = new thongKeTop10_frg();
                    replaceFragment(thongKeTop10_frg);
                    Toast.makeText(MainActivity.this, "Thống kê top 10", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId()==R.id.doanhthu) {
                    thongKeDoanhThu_frg thongKeDoanhThu_frg = new thongKeDoanhThu_frg();
                    replaceFragment(thongKeDoanhThu_frg);
                    Toast.makeText(MainActivity.this, "Thống kê doanh thu", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId()==R.id.add_nguoidung) {
                    themNguoidung_frg themNguoidung_frg = new themNguoidung_frg();
                    replaceFragment(themNguoidung_frg);
                    Toast.makeText(MainActivity.this, "Thêm người dùng", Toast.LENGTH_SHORT).show();
                }
                getSupportActionBar().setTitle(item.getTitle());
                return true;
            }
        });

    }
    public void replaceFragment(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav,frg).commit();
    }

    private void showDoiMK(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_doimatkhau,null);


        EditText edt_mkCu = view.findViewById(R.id.edt_mkCu);
        EditText edt_mkMoi = view.findViewById(R.id.edt_mkMoi);
        EditText edt_nhapLai_mkMoi = view.findViewById(R.id.edt_NhapLai_mkMoi);

        builder.setView(view);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mkCu = edt_mkCu.getText().toString();
                String mkMoi = edt_mkMoi.getText().toString();
                String nhaplai_mkMoi = edt_nhapLai_mkMoi.getText().toString();

                if (mkCu.equals("") || mkMoi.equals("") || nhaplai_mkMoi.equals("")){
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }


                if (mkMoi.equals(nhaplai_mkMoi)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);



                    String matt = sharedPreferences.getString("matt", "");


                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                    boolean check = thuThuDAO.capNhatMatKhau(matt, mkCu, mkMoi);
                    if (check){
                        Toast.makeText(MainActivity.this, "Đổi mk thành công", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        Toast.makeText(MainActivity.this, "Đổi mk thất bại", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Nhập mk không trùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }


}