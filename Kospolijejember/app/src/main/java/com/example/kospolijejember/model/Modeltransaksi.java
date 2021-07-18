package com.example.kospolijejember.model;

public class Modeltransaksi {

    String id,id_kos,Namakos,id_pemilik,Namapemilik,id_user,Namauser,id_kamar,Namakamar,Buktipembayaran,Tglmulai,Lamasewa,Jumlahkamar,Totalharga,Tgl_transaksi,Status;

    public Modeltransaksi(){}

    public Modeltransaksi(String id, String id_kos, String namakos, String id_pemilik, String namapemilik, String id_user, String namauser, String id_kamar, String namakamar, String buktipembayaran, String tglmulai, String lamasewa, String jumlahkamar, String totalharga, String tgl_transaksi, String status) {
        this.id = id;
        this.id_kos = id_kos;
        Namakos = namakos;
        this.id_pemilik = id_pemilik;
        Namapemilik = namapemilik;
        this.id_user = id_user;
        Namauser = namauser;
        this.id_kamar = id_kamar;
        Namakamar = namakamar;
        Buktipembayaran = buktipembayaran;
        Tglmulai = tglmulai;
        Lamasewa = lamasewa;
        Jumlahkamar = jumlahkamar;
        Totalharga = totalharga;
        Tgl_transaksi = tgl_transaksi;
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_kos() {
        return id_kos;
    }

    public void setId_kos(String id_kos) {
        this.id_kos = id_kos;
    }

    public String getNamakos() {
        return Namakos;
    }

    public void setNamakos(String namakos) {
        Namakos = namakos;
    }

    public String getId_pemilik() {
        return id_pemilik;
    }

    public void setId_pemilik(String id_pemilik) {
        this.id_pemilik = id_pemilik;
    }

    public String getNamapemilik() {
        return Namapemilik;
    }

    public void setNamapemilik(String namapemilik) {
        Namapemilik = namapemilik;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNamauser() {
        return Namauser;
    }

    public void setNamauser(String namauser) {
        Namauser = namauser;
    }

    public String getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(String id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getNamakamar() {
        return Namakamar;
    }

    public void setNamakamar(String namakamar) {
        Namakamar = namakamar;
    }

    public String getBuktipembayaran() {
        return Buktipembayaran;
    }

    public void setBuktipembayaran(String buktipembayaran) {
        Buktipembayaran = buktipembayaran;
    }

    public String getTglmulai() {
        return Tglmulai;
    }

    public void setTglmulai(String tglmulai) {
        Tglmulai = tglmulai;
    }

    public String getLamasewa() {
        return Lamasewa;
    }

    public void setLamasewa(String lamasewa) {
        Lamasewa = lamasewa;
    }

    public String getJumlahkamar() {
        return Jumlahkamar;
    }

    public void setJumlahkamar(String jumlahkamar) {
        Jumlahkamar = jumlahkamar;
    }

    public String getTotalharga() {
        return Totalharga;
    }

    public void setTotalharga(String totalharga) {
        Totalharga = totalharga;
    }

    public String getTgl_transaksi() {
        return Tgl_transaksi;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        Tgl_transaksi = tgl_transaksi;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
