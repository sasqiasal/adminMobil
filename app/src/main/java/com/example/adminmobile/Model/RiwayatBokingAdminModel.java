package com.example.adminmobile.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class RiwayatBokingAdminModel {
    String IDMobil;
    String NamaPenyewa;
    String NoHp;
    String Penjemputan;
    String Total;
    String Tujuan;
    @DocumentId
    String DocumentID;
    String JamBerangkat;
    int JumlahHari;
    Timestamp TanggalKembali, TanggalPinjam;
//    Date TanggalKembali, TanggalPinjam;

    public RiwayatBokingAdminModel() {
        this.JamBerangkat = "";
        this.IDMobil = "";
        this.JumlahHari = 0;
        this.NamaPenyewa = "";
        this.NoHp = "";
        this.Penjemputan = "";
        this.Total = "";
        this.Tujuan = "";
        this.TanggalKembali = null;
        this.TanggalPinjam = null;
    }
    public String getJam() {return JamBerangkat;
    }

    public void setJam(String jam) {this.JamBerangkat = jam;
    }

    public String getIDMobil() {
        return IDMobil;
    }

    public void setIDMobil(String IDMobil) {
        this.IDMobil = IDMobil;
    }

    public int getJumlahHari() {
        return JumlahHari;
    }

    public void setJumlahHari(int jumlahHari) {
        JumlahHari = jumlahHari;
    }

    public String getNamaPenyewa() {
        return NamaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        NamaPenyewa = namaPenyewa;
    }

    public String getNoHp() {
        return NoHp;
    }

    public void setNoHp(String noHp) {
        NoHp = noHp;
    }

    public String getPenjemputan() {
        return Penjemputan;
    }

    public void setPenjemputan(String penjemputan) {
        Penjemputan = penjemputan;
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

    public String getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(String documentID) {
        this.DocumentID = documentID;
    }
    public Timestamp getTanggalPinjam() {return TanggalPinjam;}

    public void setTanggalPinjam(Timestamp tanggalPinjam) {
        TanggalPinjam = tanggalPinjam;
    }

    private boolean isMimin;

    // Konstruktor dan metode lainnya...

    // Getter untuk field "isMimin"
    public boolean isMimin() {
        return isMimin;
    }

    // Setter untuk field "isMimin"
    public void setMimin(boolean isMimin) {
        this.isMimin = isMimin;
    }


}
