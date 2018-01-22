package com.iman.inc.zakatcount.model;

/**
 * Created by z on 22/01/18.
 */

public class Mdl_ZM {

    int id;
    String jmlHarta;
    String hrgEmas;
    String tZakat;
    String namaPemilik;
    //kenapa kita gk buat variabel dengan tipe int seperti di Model zakat fitra?
    //karena data yg akan kita simpan , bertipe string karena mengandung " Rp. " , karena variabel int tidak bisa menyimpan huruf


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJmlHarta() {
        return jmlHarta;
    }

    public void setJmlHarta(String jmlHarta) {
        this.jmlHarta = jmlHarta;
    }

    public String getHrgEmas() {
        return hrgEmas;
    }

    public void setHrgEmas(String hrgEmas) {
        this.hrgEmas = hrgEmas;
    }

    public String gettZakat() {
        return tZakat;
    }

    public void settZakat(String tZakat) {
        this.tZakat = tZakat;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }
}
