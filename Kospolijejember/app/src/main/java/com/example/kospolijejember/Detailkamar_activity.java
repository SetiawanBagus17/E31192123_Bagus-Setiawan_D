package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detailkamar_activity extends AppCompatActivity {

    //Deklarasi Variable
    String id;
    ImageView foto1;
    TextView tv_idkamar, tv_namakamar, tv_idkos, tv_namakos, tv_idpemilik, tv_namapemilik, tv_jumlahkamar, tv_hargakamar,tv_fasilitas;
    Button btn_sewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkamar);

        tv_idkamar = findViewById(R.id.tv_idkamar);
        tv_idkos = findViewById(R.id.tv_idkos);
        tv_namakos = findViewById(R.id.tv_namakos);
        tv_idpemilik = findViewById(R.id.tv_idpemilik);
        tv_namapemilik = findViewById(R.id.tv_namapemilik);
        tv_namakamar = findViewById(R.id.tv_namakamar);
        tv_jumlahkamar = findViewById(R.id.tv_jumlahkamar);
        tv_hargakamar = findViewById(R.id.tv_hargakamar);
        tv_fasilitas = findViewById(R.id.tv_fasilitas);
        btn_sewa = findViewById(R.id.btn_sewa);
        foto1 = findViewById(R.id.foto1);

        id = getIntent().getStringExtra("id");
        tv_idkamar.setText(id);

        detail_kamar();

        btn_sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detailkamar_activity.this, Formsewa_activity.class);
                intent.putExtra("id",tv_idkamar.getText().toString());
                intent.putExtra("nama_kamar",tv_namakamar.getText().toString());
                intent.putExtra("id_kos",tv_idkos.getText().toString());
                intent.putExtra("nama_kos",tv_namakos.getText().toString());
                intent.putExtra("id_pemilik",tv_idpemilik.getText().toString());
                intent.putExtra("nama_pemilik",tv_namapemilik.getText().toString());
                intent.putExtra("fasilitaskamar",tv_fasilitas.getText().toString());
                intent.putExtra("jumlahkamar",tv_jumlahkamar.getText().toString());
                intent.putExtra("hargakamar",tv_hargakamar.getText().toString());
                startActivity(intent);
            }
        });
    }
    private void detail_kamar(){
        StringRequest selectDetail = new StringRequest(Request.Method.POST, ServerAPI.URL_read_detailkamar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley", "response : " + response.toString());
                        try {
                            JSONObject data = new JSONObject(response);

                            String photos1 = ServerAPI.URL_IMAGEKAMAR + data.getString("Foto");

                            tv_idkos.setText(data.getString("id_kos"));
                            tv_namakos.setText(data.getString("Namakos"));
                            tv_idpemilik.setText(data.getString("id_pemilik"));
                            tv_namapemilik.setText(data.getString("Namapemilik"));
                            tv_namakamar.setText(data.getString("Namakamar"));
                            tv_fasilitas.setText(data.getString("Fasilitaskamar"));
                            tv_jumlahkamar.setText(data.getString("Jumlahkamar"));
                            tv_hargakamar.setText(data.getString("Hargakamar"));


                            Glide.with(Detailkamar_activity.this).load(photos1).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(foto1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley, ", "error  : " + error.getMessage());
                Toast.makeText(Detailkamar_activity.this, "Gagal Memuat" + id, Toast.LENGTH_SHORT).show();
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