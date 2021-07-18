package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;
import com.example.kospolijejember.session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class checkout_activity extends AppCompatActivity {

    TextView txt_lamakos,txt_namakamar,txt_namakos,txt_namapemilik,txt_harga,txt_namapenyewa,txt_mulaikos,txt_jumlkamar;
    String id,id_kos,id_pemilik,id_penyewa,namakamar,namakos,namapemilik,namapenyewa,jumlahkamar,tgl_kos,harga,lamasewa;
    SessionManager sessionManager;
    Button btn_bayarsekarang,btn_bayarnanti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        txt_lamakos = findViewById(R.id.txt_lamakos);
        txt_namakamar = findViewById(R.id.txt_namakamar);
        txt_namakos = findViewById(R.id.txt_namakos);
        txt_namapemilik = findViewById(R.id.txt_namapemilik);
        txt_harga = findViewById(R.id.txt_harga);
        txt_mulaikos = findViewById(R.id.txt_mulaikos);
        txt_jumlkamar = findViewById(R.id.txt_jumlkamar);
        txt_namapenyewa = findViewById(R.id.txt_namapenyewa);
        btn_bayarnanti = findViewById(R.id.btn_bayarnanti);
        btn_bayarsekarang = findViewById(R.id.btn_bayarsekarang);

        sessionManager = new SessionManager(checkout_activity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();

        id = getIntent().getStringExtra("id");
        id_kos = getIntent().getStringExtra("id_kos");
        id_pemilik = getIntent().getStringExtra("id_pemilik");
        id_penyewa = getIntent().getStringExtra("id_penyewa");
        namakamar = getIntent().getStringExtra("namakamar");
        txt_namakamar.setText(namakamar);
        namakos= getIntent().getStringExtra("namakos");
        txt_namakos.setText(namakos);
        namapemilik= getIntent().getStringExtra("namapemilik");
        txt_namapemilik.setText(namapemilik);
        namapenyewa= getIntent().getStringExtra("namapenyewa");
        txt_namapenyewa.setText(namapenyewa);
        jumlahkamar= getIntent().getStringExtra("jumlahkamar");
        txt_jumlkamar.setText(jumlahkamar);
        tgl_kos= getIntent().getStringExtra("tgl_kos");
        txt_mulaikos.setText(tgl_kos);
        harga= getIntent().getStringExtra("hargakos");
        txt_harga.setText(harga);
        lamasewa= getIntent().getStringExtra("lamasewa");
        txt_lamakos.setText(lamasewa);

        btn_bayarsekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahhalaman = new Intent(checkout_activity.this,FormBukti_activity.class);
                pindahhalaman.putExtra("id",id);
                pindahhalaman.putExtra("id_kos",id_kos);
                pindahhalaman.putExtra("id_pemilik",id_pemilik);
                pindahhalaman.putExtra("id_penyewa",id_penyewa);
                pindahhalaman.putExtra("hargakos",harga);
                pindahhalaman.putExtra("namapemilik",namapemilik);
                pindahhalaman.putExtra("namapenyewa", namapenyewa);
                pindahhalaman.putExtra("jumlahkamar",jumlahkamar);
                pindahhalaman.putExtra("tgl_kos",tgl_kos);
                pindahhalaman.putExtra("lamasewa",lamasewa);
                pindahhalaman.putExtra("namakos",namakos);
                pindahhalaman.putExtra("namakamar",namakamar);
                startActivity(pindahhalaman);
            }
        });

        btn_bayarnanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_data();
            }
        });
        }
    private void simpan_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_transaksi_pending, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(checkout_activity.this, "Berhasil Menyimpan Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(checkout_activity.this, BerandaActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(checkout_activity.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(checkout_activity.this, "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("id_kos",id_kos);
                params.put("id_pemilik",id_pemilik);
                params.put("id_penyewa",id_penyewa);
                params.put("hargakos",harga);
                params.put("namapemilik",namapemilik);
                params.put("namapenyewa",namapenyewa);
                params.put("jumlahkamar",jumlahkamar);
                params.put("tgl_kos",tgl_kos);
                params.put("lamasewa",lamasewa);
                params.put("namakos",namakos);
                params.put("namakamar",namakamar);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
}