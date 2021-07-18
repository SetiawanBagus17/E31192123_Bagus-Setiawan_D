package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class EditProfile extends AppCompatActivity {

    ImageView imageProfile;
    EditText et_nama,et_namauser,et_alamat,et_notelp;
    TextView tv_kelamin;
    Spinner sp_kelamin;
    private String[] list = {"-Pilih-","Laki-laki","Perempuan"};
    Button button;
    String id,nama, alamat, namauser, kelamin, notelp;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageProfile = findViewById(R.id.imageProfile);
        et_nama = findViewById(R.id.et_nama);
        et_namauser = findViewById(R.id.et_namauser);
        et_alamat = findViewById(R.id.et_alamat);
        et_notelp = findViewById(R.id.et_notelp);
        tv_kelamin = findViewById(R.id.tv_kelamin);
        sp_kelamin = findViewById(R.id.sp_kelamin);
        ArrayAdapter adapter_kelamin= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter_kelamin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_kelamin.setAdapter(adapter_kelamin);
        button = findViewById(R.id.btn_edit_profile);

        sessionManager = new SessionManager(EditProfile.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID_USER);

        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        namauser = getIntent().getStringExtra("namauser");
        kelamin = getIntent().getStringExtra("kelamin");
        notelp = getIntent().getStringExtra("notelp");

        et_nama.setText(nama);
        et_alamat.setText(alamat);
        et_notelp.setText(notelp);
        tv_kelamin.setText(kelamin);
        et_namauser.setText(namauser);

        sp_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_kelamin.setText(list[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_kelamin.setText("-Pilih-");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Snama = et_nama.getText().toString();
                String Salamat = et_alamat.getText().toString();
                String Snotelp = et_notelp.getText().toString();
                String Skelamin = tv_kelamin.getText().toString();
                String Snamauser = et_namauser.getText().toString();

                if (Snama.equals("")){
                    et_nama.setError("Anda belum menginputkan nama");
                    et_nama.requestFocus();
                }if (Snotelp.equals("")){
                    et_notelp.setError("Anda belum menginputkan nomor telp");
                    et_notelp.requestFocus();
                }if (Snamauser.equals("")){
                    et_namauser.setError("Anda belum menginputkan username");
                    et_namauser.requestFocus();
                }if (Skelamin.equals("")){
                    tv_kelamin.setError("Anda belum memilih jenis kelamin");
                    tv_kelamin.requestFocus();
                }if (Salamat.equals("")){
                    et_alamat.setError("Anda belum menginputkan alamat");
                    et_alamat.requestFocus();
                }else{
                    edit_data();
                }
            }
        });
    }
    private void edit_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_edit_profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(EditProfile.this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfile.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(EditProfile.this, "Gagal Mengubah Akun", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(EditProfile.this, "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("nama",et_nama.getText().toString());
                params.put("notelp",et_notelp.getText().toString());
                params.put("namauser",et_namauser.getText().toString());
                params.put("alamat",et_alamat.getText().toString());
                params.put("kelamin",tv_kelamin.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
}