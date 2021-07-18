package com.example.kospolijejember.model;

public class Modelkamar {

    String id, Namakamar, id_kos, Namakos, id_pemilik, Namapemilik, Foto, Fasilitaskamar, Jumlahkamar, Hargakamar, Aktif;

    public Modelkamar(){}

    public Modelkamar(String id, String namakamar, String id_kos, String namakos, String id_pemilik, String namapemilik, String foto, String fasilitaskamar, String jumlahkamar, String hargakamar, String aktif) {
        this.id = id;
        Namakamar = namakamar;
        this.id_kos = id_kos;
        Namakos = namakos;
        this.id_pemilik = id_pemilik;
        Namapemilik = namapemilik;
        Foto = foto;
        Fasilitaskamar = fasilitaskamar;
        Jumlahkamar = jumlahkamar;
        Hargakamar = hargakamar;
        Aktif = aktif;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamakamar() {
        return Namakamar;
    }

    public void setNamakamar(String namakamar) {
        Namakamar = namakamar;
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

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getFasilitaskamar() {
        return Fasilitaskamar;
    }

    public void setFasilitaskamar(String fasilitaskamar) {
        Fasilitaskamar = fasilitaskamar;
    }

    public String getJumlahkamar() {
        return Jumlahkamar;
    }

    public void setJumlahkamar(String jumlahkamar) {
        Jumlahkamar = jumlahkamar;
    }

    public String getHargakamar() {
        return Hargakamar;
    }

    public void setHargakamar(String hargakamar) {
        Hargakamar = hargakamar;
    }

    public String getAktif() {
        return Aktif;
    }

    public void setAktif(String aktif) {
        Aktif = aktif;
    }
}
