package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;
import com.google.android.gms.maps.MapView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detailkos_activity extends AppCompatActivity {

    MapView maps;
    TextView txt_idkos,txt_namakos,txt_namakec,txt_harga,txt_namapemilik,txt_deskripsi,txt_jmlkamar,latitude,longtitude,txt_telp;
    String id,telp;
    Button btn_sewa, btn_wa;
    ImageView foto1,foto2,foto3,foto4;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkos);

        foto1 =findViewById(R.id.foto1);
        foto2 =findViewById(R.id.foto2);
        foto3 =findViewById(R.id.foto3);
        foto4 =findViewById(R.id.foto4);
        btn_sewa =findViewById(R.id.btn_sewa);
        btn_wa = findViewById(R.id.btn_wa);

        txt_idkos = findViewById(R.id.txt_idkos);
        txt_namakos = findViewById(R.id.txt_namakos);
        txt_namakec = findViewById(R.id.txt_namakec);
        txt_harga = findViewById(R.id.txt_harga);
        txt_namapemilik = findViewById(R.id.txt_namapemilik);
        txt_telp = findViewById(R.id.txt_telp);
        txt_deskripsi = findViewById(R.id.txt_deskripsi);
        latitude=findViewById(R.id.latitude);
        longtitude=findViewById(R.id.longtitude);
        txt_jmlkamar = findViewById(R.id.txt_jumlkamar);
        imageView = findViewById(R.id.maps);

        id = getIntent().getStringExtra("id");
        txt_idkos.setText(id);
        telp = getIntent().getStringExtra("no_telp");
        txt_telp.setText(telp);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Detailkos_activity.this,MapsActivity.class);
                intent.putExtra("latitude",latitude.getText().toString());
                intent.putExtra("longtitude",longtitude.getText().toString());
                intent.putExtra("kost_name",txt_namakos.getText().toString());
                startActivity(intent);
            }
        });

        btn_sewa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Detailkos_activity.this, ListKamar.class);
                        intent.putExtra("id",txt_idkos.getText().toString());
                        startActivity(intent);

            }
        });

        btn_wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = txt_telp.getText().toString().trim();
                Uri webpage = Uri.parse("https://api.whatsapp.com/send?phone=62"+phoneNo);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
//                if (intent.resolveActivity(Detailkos_activity.this.getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//Alert Comment
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

                            String photos1 = ServerAPI.URL_IMAGEKOS + data.getString("foto1");
                            String photos2 = ServerAPI.URL_IMAGEKOS + data.getString("foto2");
                            String photos3 = ServerAPI.URL_IMAGEKOS + data.getString("foto3");
                            String photos4 = ServerAPI.URL_IMAGEKOS + data.getString("foto4");

                            txt_namakos.setText(data.getString("Namakos"));
                            txt_namakec.setText(data.getString("Kecamatan"));
                            txt_namapemilik.setText(data.getString("Namapemilik"));
                            txt_deskripsi.setText(data.getString("Deskripsi"));
                            txt_telp.setText(data.getString("No_telp"));
                            latitude.setText(data.getString("Latitude"));
                            longtitude.setText(data.getString("Longtitude"));

                            Glide.with(Detailkos_activity.this).load(photos1).thumbnail(0.5f).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).into(foto1);
                            Glide.with(Detailkos_activity.this).load(photos2).thumbnail(0.5f).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).into(foto2);
                            Glide.with(Detailkos_activity.this).load(photos3).thumbnail(0.5f).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).into(foto3);
                            Glide.with(Detailkos_activity.this).load(photos4).thumbnail(0.5f).diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL).into(foto4);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley, ", "error  : " + error.getMessage());
                Toast.makeText(Detailkos_activity.this, "Gagal Memuat" + id, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(selectDetail);
    }

}