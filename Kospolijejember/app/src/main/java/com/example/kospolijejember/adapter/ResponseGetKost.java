package com.example.kospolijejember.adapter;

import com.google.gson.annotations.SerializedName;

public class ResponseGetKost{

	@SerializedName("Status")
	private String status;

	@SerializedName("foto4")
	private String foto4;

	@SerializedName("distance")
	private String distance;

	@SerializedName("Deskripsi")
	private String deskripsi;

	@SerializedName("Namapemilik")
	private String namapemilik;

	@SerializedName("Latitude")
	private String latitude;

	@SerializedName("Namakos")
	private String namakos;

	@SerializedName("Kecamatan")
	private String kecamatan;

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

	public String getStatus(){
		return status;
	}

	public String getFoto4(){
		return foto4;
	}

	public String getDistance(){
		return distance;
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

	public String getNamakos(){
		return namakos;
	}

	public String getKecamatan(){
		return kecamatan;
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