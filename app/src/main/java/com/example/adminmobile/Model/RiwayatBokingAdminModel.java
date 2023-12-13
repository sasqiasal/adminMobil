package com.example.adminmobile.Model;

import com.google.firebase.firestore.DocumentId;

public class RiwayatBokingAdminModel {

    public RiwayatBokingAdminModel(){
        this.documentID = null;
        this.IDMobil ="";
    this.Namapemesan ="";
    this.Penjemputan = "";
    this.TanggalKembali="";
    this.TanggalPinjam="";
    this.Total="";
    this.Tujuan="";
    this.JumlahHari =0;
    }

    public String getIDMobil() {
        return IDMobil;
    }

    public void setIDMobil(String IDMobil) {
        this.IDMobil = IDMobil;
    }

    public String getNamapemesan() {
        return Namapemesan;
    }

    public void setNamapemesan(String namapemesan) {
        Namapemesan = namapemesan;
    }

    public String getPenjemputan() {
        return Penjemputan;
    }

    public void setPenjemputan(String penjemputan) {
        Penjemputan = penjemputan;
    }

    public String getTanggalKembali() {
        return TanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        TanggalKembali = tanggalKembali;
    }

    public String getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        TanggalPinjam = tanggalPinjam;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getTujuan() {
        return Tujuan;
    }

    public void setTujuan(String tujuan) {
        Tujuan = tujuan;
    }

    public int getJumlahHari() {
        return JumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        JumlahHari = jumlahHari;
    }

    String IDMobil,Namapemesan,Penjemputan,TanggalKembali,TanggalPinjam,Total,Tujuan;
    int JumlahHari;

    public String getDocumentID() {
        return documentID;
    }

    @DocumentId
    String documentID;
}

