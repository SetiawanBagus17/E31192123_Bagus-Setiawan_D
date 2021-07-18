package com.example.kospolijejember.model;

public class Modelkos {
    String  id,Namakos,id_pemilik,Namapemilik,No_telp,Latitude,Longtitude,foto1,foto2,foto3,foto4,Deskripsi,Kecamatan,Status,Aktif;

    public Modelkos(){}

    public Modelkos(String id, String namakos, String id_pemilik, String namapemilik, String no_telp, String latitude, String longtitude, String foto1, String foto2, String foto3, String foto4, String deskripsi, String kecamatan, String status, String aktif) {
        this.id = id;
        Namakos = namakos;
        this.id_pemilik = id_pemilik;
        Namapemilik = namapemilik;
        No_telp = no_telp;
        Latitude = latitude;
        Longtitude = longtitude;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.foto4 = foto4;
        Deskripsi = deskripsi;
        Kecamatan = kecamatan;
        Status = status;
        Aktif = aktif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNo_telp() {
        return No_telp;
    }

    public void setNo_telp(String no_telp) {
        No_telp = no_telp;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getFoto4() {
        return foto4;
    }

    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAktif() {
        return Aktif;
    }

    public void setAktif(String aktif) {
        Aktif = aktif;
    }
}
