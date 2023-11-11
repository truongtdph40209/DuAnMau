package com.example.ph40209_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ph40209_duanmau.dao.ThuThuDAO;

public class dang_nhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edt_User = findViewById(R.id.edt_userName);
        EditText edt_Pass = findViewById(R.id.edt_passWord);
        Button btn_Login = findViewById(R.id.btn_login);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_User.getText().toString();
                String pass = edt_Pass.getText().toString();

                if (thuThuDAO.checkdn(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(dang_nhap.this, MainActivity.class));

                }else {
                    Toast.makeText(dang_nhap.this, "Tk or mk sai", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}