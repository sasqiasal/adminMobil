package com.example.adminmobile;

public class Mobil {
    private  String merekmobil, harga, jmlkursi;
    private  String gambar;

    public Mobil() {
    }

    public Mobil(String merekmobil, String harga, String jmlkursi, String gambar) {
        this.merekmobil = merekmobil;
        this.harga = harga;
        this.jmlkursi = jmlkursi;
        this.gambar = gambar;
    }

    public String getMerekmobil() {
        return merekmobil;
    }

    public void setMerekmobil(String merekmobil) {
        this.merekmobil = merekmobil;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJmlkursi(){
        return jmlkursi;
    }
    public void setJmlkursi(String jmlkursi){
        this.jmlkursi = jmlkursi;
    }
    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
