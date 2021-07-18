package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kospolijejember.adapter.AdapterListKamar;
import com.example.kospolijejember.model.Modelkamar;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListKamar extends AppCompatActivity {

    String id;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Modelkamar> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kamar);

        id = getIntent().getStringExtra("id");

        mRecyclerView = findViewById(R.id.listrcycler);
        mItems = new ArrayList<>();

        tampilkamar();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterListKamar(this,mItems);
        mRecyclerView.setAdapter(mAdapter);
    }
    private void tampilkamar(){

        StringRequest selectkos = new StringRequest(Request.Method.POST, ServerAPI.URL_read_listkamar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley", "response" +response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONArray array = new JSONArray(response);
                                JSONObject data = array.getJSONObject(i);

                                Modelkamar mk = new Modelkamar();
                                mk.setId(data.getString("id"));
                                mk.setNamakamar(data.getString("Namakamar"));
                                mk.setId_kos(data.getString("id_kos"));
                                mk.setNamakos(data.getString("Namakos"));
                                mk.setId_pemilik(data.getString("id_pemilik"));
                                mk.setNamapemilik(data.getString("Namapemilik"));
                                mk.setFoto(data.getString("Foto"));
                                mk.setFasilitaskamar(data.getString("Fasilitaskamar"));
                                mk.setJumlahkamar(data.getString("Jumlahkamar"));
                                mk.setHargakamar(data.getString("Hargakamar"));
                                mk.setAktif(data.getString("Aktif"));
                                mItems.add(mk);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error : " +error.getMessage());
                Toast.makeText(ListKamar.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(selectkos);
    }
}