package com.example.kospolijejember.adapter;

import com.google.gson.annotations.SerializedName;

public class ResponseListKost{

	@SerializedName("Status")
	private String status;

	@SerializedName("Namakos")
	private String namakos;

	@SerializedName("foto4")
	private String foto4;

	@SerializedName("Kecamatan")
	private String kecamatan;

	@SerializedName("Deskripsi")
	private String deskripsi;

	@SerializedName("Namapemilik")
	private String namapemilik;

	@SerializedName("Latitude")
	private String latitude;

	@SerializedName("id")
	private String id;

	@SerializedName("foto1")
	private String foto1;

	@SerializedName("Longtitude")
	private String longtitude;

	@SerializedName("foto3")
	private String foto3;

	@SerializedName("foto2")
	private String foto2;

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNamakos(String namakos) {
		this.namakos = namakos;
	}

	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}

	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public void setNamapemilik(String namapemilik) {
		this.namapemilik = namapemilik;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getStatus(){
		return status;
	}

	public String getNamakos(){
		return namakos;
	}

	public String getFoto4(){
		return foto4;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public String getNamapemilik(){
		return namapemilik;
	}

	public String getLatitude(){
		return latitude;
	}

	public String getId(){
		return id;
	}

	public String getFoto1(){
		return foto1;
	}

	public String getLongtitude(){
		return longtitude;
	}

	public String getFoto3(){
		return foto3;
	}

	public String getFoto2(){
		return foto2;
	}
}