package com.example.ck03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
    EditText fix_ID, fix_Name, fix_SoLuong, fix_Gia;
    Button btnSua;

    private MyService mMyService;
    private Boolean isConnect = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mMyService = binder.getService();
            isConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = new Intent(MainActivity3.this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);



        fix_ID = findViewById(R.id.fix_ID);
        fix_Name = findViewById(R.id.fix_Name);
        fix_SoLuong = findViewById(R.id.fix_soluong);
        fix_Gia = findViewById(R.id.fix_Tuoi);
        btnSua = findViewById(R.id.btnSua);

        SanPham sp = (SanPham) getIntent().getSerializableExtra("SinhVien");



        if(sp != null) {
            fix_ID.setText(sp.getId());
            fix_Name.setText(sp.getName());
            fix_Gia.setText(sp.getPrice() + "");
            fix_SoLuong.setText(sp.getSoluong() + "");
        }


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = fix_ID.getText().toString();
                String name = fix_Name.getText().toString();
                Double gia = Double.parseDouble(fix_Gia.getText().toString());
                Double soluong = Double.parseDouble(fix_SoLuong.getText().toString());


                mMyService.suaSanPham(id, new SanPham(id, name, gia, soluong));

                Intent intent  = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
            }
        });






    }
}