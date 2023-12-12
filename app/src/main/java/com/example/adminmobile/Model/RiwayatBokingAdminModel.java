package com.example.adminmobile.Model;

import com.google.firebase.firestore.DocumentId;

public class RiwayatBokingAdminModel {
    public String getNamaPemesanan() {
        return Namapemesan;
    }

    public void setNamaPemesanan(String namaPemesanan) {
        Namapemesan = namaPemesanan;
    }

    private String Namapemesan;

    public String getPenjemputan() {
        return penjemputan;
    }

    public void setPenjemputan(String penjemputan) {
        this.penjemputan = penjemputan;
    }

    private String penjemputan;

    public String getDocumentId() {
        return documentId;
    }

    public String getJumlahHari() {
        return JumlahHari;
    }

    public void setJumlahHari(String jumlahHari) {
        JumlahHari = jumlahHari;
    }

    private String JumlahHari;

    public String getTanggalKembali() {
        return TanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        TanggalKembali = tanggalKembali;
    }

    private String TanggalKembali;

    public String getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        TanggalPinjam = tanggalPinjam;
    }

    private String TanggalPinjam;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    private String Total;
    private String Tujuan;

    public String getIDMobil() {
        return IDMobil;
    }

    public void setIDMobil(String IDMobil) {
        this.IDMobil = IDMobil;
    }

    private String IDMobil;

    public String getTujuan() {
        return Tujuan;
    }

    public void setTujuan(String tujuan) {
        Tujuan = tujuan;
    }

    @DocumentId
    private String documentId;

    public RiwayatBokingAdminModel() {
        this.documentId = null;
        this.Namapemesan="";
        this.penjemputan="";
        this.JumlahHari="";
        this.TanggalPinjam="";
        this.TanggalKembali="";
        this.Total="";
        this.Tujuan="";
        this.IDMobil ="";
    }
}

