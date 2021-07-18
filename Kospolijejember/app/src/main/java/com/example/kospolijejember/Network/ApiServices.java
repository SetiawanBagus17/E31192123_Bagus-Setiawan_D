package com.example.kospolijejember.Network;
import com.example.kospolijejember.adapter.ResponseGetKost;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("getArroundKost")
    Call<List<ResponseGetKost>> get_kost(
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("radius") int radius
    );
    @FormUrlEncoded
    @POST("getKostByDistricts")
    Call<List<ResponseGetKost>> get_by_districts(
            @Field("kecamatan") String kecamatan
    );
}
