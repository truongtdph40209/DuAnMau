package com.example.ph40209_duanmau.model;

public class loaisach {
    private int id;
    private String tenloai;

    public loaisach(int id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public loaisach() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
