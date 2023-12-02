package com.example.adminmobile.Model;

public class Mobil {
    private String Nama,Harga, Gambar;


    public Mobil() {
        this.Nama = "";
        this.Harga="";
        this.Gambar="";
    }

    public String getNama() {
        return Nama;
    }
    public void setNama(String Nama) {
        this.Nama = Nama;
    }


    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String Gambar) {
        this.Gambar = Gambar;
    }

}





