package com.example.adminmobile.Model;

import com.google.firebase.firestore.DocumentId;

public class Mobil {
    private String Nama,Harga,Kursi, Gambar;
    public String getDocumentId() {
        return documentId;
    }

    @DocumentId
    private String documentId;



    public Mobil() {
        this.documentId = null;
        this.Nama = "";
        this.Harga="";
        this.Kursi="";
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
    public String getKursi() {
        return Kursi;
    }
    public void setKursi(String Kursi) {
        this.Kursi = Kursi;
    }
    public String getGambar() {
        return Gambar;
    }
    public void setGambar(String Gambar) {
        this.Gambar = Gambar;
    }

}





