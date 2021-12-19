package com.example.ck03;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService api = new Retrofit.Builder()
            .baseUrl("https://61bd4778d8542f0017824ba2.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("sanpham")
    Call<List<SanPham>> getListSP();

    @POST("sanpham")
    Call<SanPham> addSanPham(@Body SanPham sv);

    @DELETE("sanpham/{id}")
    Call<SanPham> xoaSinhVien(@Path("id") String id);

    @PUT("sanpham/{id}")
    Call<SanPham> suaSanPham(@Path("id") String id, @Body SanPham sanPham);


}
