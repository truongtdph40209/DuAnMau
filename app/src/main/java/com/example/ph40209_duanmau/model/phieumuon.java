package com.example.ph40209_duanmau.model;

public class phieumuon {
    private int mapm;
    private int matv;
    private String tentv;
    private String matt;
    private String tentt;
    private int masach;
    private String tensach;
    private String ngay;
    private int trasach;
    private int tienthue;

    //pm.mapm, pm.matv, tv.hoten, pm.matt, tt.hoten, pm.masach, sc.tensach, pm.ngay, pm.trasach, pm.tienthue

    public phieumuon() {
    }

    public phieumuon(int mapm, int matv, String tentv, String matt, String tentt, int masach, String tensach, String ngay, int trasach, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.tentv = tentv;
        this.matt = matt;
        this.tentt = tentt;
        this.masach = masach;
        this.tensach = tensach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public phieumuon(int matv, String matt, int masach, String ngay, int trasach, int tienthue) {

        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }
}
