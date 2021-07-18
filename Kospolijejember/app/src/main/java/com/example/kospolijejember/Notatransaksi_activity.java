package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Notatransaksi_activity extends AppCompatActivity {

    TextView latitude, longtitude,id_transaksi, nama_kos, pemilik_kos, no_telp, nama_penyewa, mulai_sewa, nama_kamar, jumlah_kamar,lama_sewa, harga_total,tv_image, tgl_transaksi;
    String  Sid_transaksi, Sid_kos, Snama_kos, Spemilik_kos, Snama_penyewa, Smulai_sewa, Snama_kamar, Sjumlah_kamar,Slama_sewa, Sharga_total,Stv_image, Stgl_transaksi;
    ImageView maps,img_bukti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notatransaksi);

        id_transaksi = findViewById(R.id.id_transaksi);
        nama_kos = findViewById(R.id.nama_kos);
        pemilik_kos = findViewById(R.id.pemilik_kos);
        no_telp = findViewById(R.id.no_telp);
        lama_sewa = findViewById(R.id.lama_sewa);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        mulai_sewa = findViewById(R.id.mulai_sewa);
        nama_kamar = findViewById(R.id.nama_kamar);
        jumlah_kamar = findViewById(R.id.jumlah_kamar);
        harga_total = findViewById(R.id.harga_total);
        img_bukti = findViewById(R.id.img_bukti);
        maps = findViewById(R.id.maps);
        tgl_transaksi = findViewById(R.id.tgl_transaksi);
        tv_image = findViewById(R.id.tv_image);
        latitude = findViewById(R.id.latitude);
        longtitude = findViewById(R.id.longtitude);


        Sid_transaksi = getIntent().getStringExtra("id");
        id_transaksi.setText(Sid_transaksi);
        Sid_kos = getIntent().getStringExtra("id_kos");
        Snama_kos = getIntent().getStringExtra("namakos");
        nama_kos.setText(Snama_kos);
        Spemilik_kos = getIntent().getStringExtra("namapemilik");
        pemilik_kos.setText(Spemilik_kos);
        Snama_penyewa = getIntent().getStringExtra("namapenyewa");
        nama_penyewa.setText(Snama_penyewa);
        Smulai_sewa = getIntent().getStringExtra("tgl_kos");
        mulai_sewa.setText(Smulai_sewa);
        Snama_kamar = getIntent().getStringExtra("namakamar");
        nama_kamar.setText(Snama_kamar);
        Sjumlah_kamar = getIntent().getStringExtra("jumlahkamar");
        jumlah_kamar.setText(Sjumlah_kamar);
        Slama_sewa = getIntent().getStringExtra("lamasewa");
        lama_sewa.setText(Slama_sewa);
        Sharga_total = getIntent().getStringExtra("hargakos");
        harga_total.setText(Sharga_total);
        Stv_image = getIntent().getStringExtra("bukti");
        tv_image.setText(Stv_image);
        Stgl_transaksi = getIntent().getStringExtra("transaksi");
        tgl_transaksi.setText(Stgl_transaksi);

        String img = ServerAPI.URL_IMAGEBUKTI + Stv_image;
        Glide.with(Notatransaksi_activity.this).load(img).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(img_bukti);

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Notatransaksi_activity.this,MapsActivity.class);
                intent.putExtra("latitude",latitude.getText().toString());
                intent.putExtra("longtitude",longtitude.getText().toString());
                intent.putExtra("kost_name",nama_kos.getText().toString());
                startActivity(intent);
            }
        });

        select_detail_kos();
    }
    private void select_detail_kos() {

        StringRequest selectDetail = new StringRequest(Request.Method.POST, ServerAPI.URL_read_detailkos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley", "response : " + response.toString());
                        try {
                            JSONObject data = new JSONObject(response);

                            no_telp.setText(data.getString("No_telp"));
                            latitude.setText(data.getString("Latitude"));
                            longtitude.setText(data.getString("Longtitude"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley, ", "error  : " + error.getMessage());
                Toast.makeText(Notatransaksi_activity.this, "Gagal Memuat" + Sid_transaksi, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", Sid_kos);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(selectDetail);
    }
}