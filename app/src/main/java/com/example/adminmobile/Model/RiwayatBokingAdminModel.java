package com.example.adminmobile.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class RiwayatBokingAdminModel {

    public RiwayatBokingAdminModel() {
        this.documentID = null;
        this.Tujuan = "";
        this.IDMobil = "";
        this.NoHp = "";
        this.Namapemesan = "";
        this.Penjemputan = "";
        this.TanggalKembali = null;
        this.TanggalPinjam = null;
        this.Total = "";
        this.JumlahHari = 0;
    }

    public String getIDMobil() {
        return IDMobil;
    }

    public void setIDMobil(String IDMobil) {
        this.IDMobil = IDMobil;
    }

    public Object getNoHp() {
        return NoHp;
    }

    public void setNoHp(String Nohp) {
        NoHp = Nohp;
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

    public Timestamp getTanggalKembali() {
        return TanggalKembali;
    }

    public void setTanggalKembali(Timestamp tanggalKembali) {
        TanggalKembali = tanggalKembali;
    }

    public Timestamp getTanggalPinjam() {
        return TanggalPinjam;
    }

    public void setTanggalPinjam(Timestamp tanggalPinjam) {
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

    String IDMobil;
    String Namapemesan;
    String Penjemputan;
    String NoHp;
    private Timestamp TanggalKembali;
    private Timestamp TanggalPinjam;
    String Total;
    String Tujuan;
    int JumlahHari;

    public String getDocumentID() {
        return documentID;
    }

    @DocumentId
    private String documentID;
}
