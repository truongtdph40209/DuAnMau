package com.example.ph40209_duanmau.model;

public class thuthu {
    private String matt;
    private String hoten;
    private String matkhau;

    public thuthu() {
    }

    public thuthu(String matt, String hoten, String matkhau) {
        this.matt = matt;
        this.hoten = hoten;
        this.matkhau = matkhau;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
