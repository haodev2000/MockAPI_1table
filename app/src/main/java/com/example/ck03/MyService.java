package com.example.ck03;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {
    private MyBinder mBinder  = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }


    public void themSanPham(SanPham sv){
        ApiService.api.addSanPham(sv).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                Toast.makeText(getApplicationContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Thêm lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void xoaSanPham(String id){
        ApiService.api.xoaSinhVien(id).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Xoá lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void suaSanPham(String id, SanPham sp){
        ApiService.api.suaSanPham(id, sp).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sửa lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
