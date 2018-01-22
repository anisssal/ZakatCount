package com.iman.inc.zakatcount.model;

/**
 * Created by z on 21/01/18.
 */

public class Mdl_ZF {
    private int id;
    public int jumlahKel;
    private int totalZakat;
    private String namaKK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlahKel() {
        return jumlahKel;
    }

    public void setJumlahKel(int jumlahKel) {
        this.jumlahKel = jumlahKel;
    }

    public int getTotalZakat() {
        return totalZakat;
    }

    public void setTotalZakat(int totalZakat) {
        this.totalZakat = totalZakat;
    }

    public String getNamaKK() {
        return namaKK;
    }

    public void setNamaKK(String namaKK) {
        this.namaKK = namaKK;
    }
}
