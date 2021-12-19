package com.example.ck03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements OnClickListener {
    Button btnADD, btnList;
    EditText editName, editPrice;
    TextView editSoluong;
    RecyclerView rcv;

    ImageView btnTru, btnCong;

    List<SanPham> sanPhams;
    CustomerAdapter adt;


    MyService myService;

    private Boolean isConnect = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
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
        setContentView(R.layout.activity_main2);

        Intent intent = new Intent(MainActivity2.this, MyService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);

        btnADD = findViewById(R.id.btnADD);
        btnList = findViewById(R.id.btnList);
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editSoluong = findViewById(R.id.editSoLuong);

        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);

        rcv = findViewById(R.id.rcv);
        sanPhams = new ArrayList<SanPham>();






        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList();
                Toast.makeText(MainActivity2.this, "Lấy list thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = editName.getText().toString();
                Double gia = Double.parseDouble(editPrice.getText().toString());
                Double soluogn = Double.parseDouble(editSoluong.getText().toString());

                myService.themSanPham(new SanPham(ten, gia, soluogn));
                getList();
            }
        });



        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer so = Integer.parseInt(editSoluong.getText().toString());
                if(so > 0){
                    so--;
                    editSoluong.setText(so+"");
                }
            }
        });

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer so = Integer.parseInt(editSoluong.getText().toString());
                so++;
                editSoluong.setText(so+"");
            }
        });













    }

    private void getList() {
        ApiService.api.getListSP().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.body() != null) {
                    sanPhams= response.body();
//                  tv.setText(sanPhamList.size()+"");
                    adt = new CustomerAdapter(sanPhams, MainActivity2.this);
                    rcv.setHasFixedSize(true);
                    rcv.setAdapter(adt);
                    rcv.setLayoutManager(new LinearLayoutManager(MainActivity2.this, LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });



    }


    //-----------------------------------------------------------------------------
    @Override
    public void clickItem(SanPham sv) {

    }

    @Override
    public void clickItemXoa(SanPham sv) {
        myService.xoaSanPham(sv.getId());
        getList();
    }

    @Override
    public void clickItemSua(SanPham sv) {
        Intent intent  = new Intent(MainActivity2.this,MainActivity3.class);
        intent.putExtra("SinhVien", sv);
        startActivity(intent);
    }
}