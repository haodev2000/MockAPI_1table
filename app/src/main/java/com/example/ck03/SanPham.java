package com.example.ck03;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String id;
    private String name;
    private Double price;
    private Double soluong;

    public SanPham(String id, String name, Double price, Double soluong) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soluong = soluong;
    }

    public SanPham(String name, Double price, Double soluong) {
        this.name = name;
        this.price = price;
        this.soluong = soluong;
    }

    public SanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSoluong() {
        return soluong;
    }

    public void setSoluong(Double soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", soluong=" + soluong +
                '}';
    }
}
