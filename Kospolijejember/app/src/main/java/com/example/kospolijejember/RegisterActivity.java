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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Spinner sp_kelamin;
    private String[] list = {"Pria","Wanita"};
    TextView tv_login;
    TextView tv_jk;
    String nama,telp,username,password,alamat;
    EditText et_nama, et_telp, et_username, et_password, et_alamat;
    Button btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sp_kelamin = findViewById(R.id.sp_kelamin);
        tv_login = findViewById(R.id.tv_login);
        tv_jk = findViewById(R.id.tv_jk);
        et_nama = findViewById(R.id.et_nama);
        et_telp = findViewById(R.id.et_telp);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_alamat = findViewById(R.id.et_alamat);
        btn_simpan = findViewById(R.id.btn_simpan);




        ArrayAdapter adapter_kelamin = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter_kelamin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_kelamin.setAdapter(adapter_kelamin);

        sp_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_jk.setText(list[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Snama = et_nama.getText().toString();
                String Stelp = et_telp.getText().toString();
                String Susername = et_username.getText().toString();
                String Spassword = et_password.getText().toString();
                String Salamat = et_alamat.getText().toString();

                if (Snama.equals("")){
                    et_nama.setError("Anda belum menginputkan nama");
                    et_nama.requestFocus();
                }if (Stelp.equals("")){
                    et_telp.setError("Anda belum menginputkan nomor telp");
                    et_telp.requestFocus();
                }if (Susername.equals("")){
                    et_username.setError("Anda belum menginputkan username");
                    et_username.requestFocus();
                }if (Spassword.equals("")){
                    et_password.setError("Anda belum menginputkan password");
                    et_password.requestFocus();
                }if (Salamat.equals("")){
                    et_alamat.setError("Anda belum menginputkan alamat");
                    et_alamat.requestFocus();
                }else{
                    simpan_data();
                }

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void simpan_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(RegisterActivity.this, "Berhasil Membuat Akun", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(RegisterActivity.this, "Gagal Membuat Akun", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(RegisterActivity.this, "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("nama",et_nama.getText().toString());
                params.put("telp",et_telp.getText().toString());
                params.put("username",et_username.getText().toString());
                params.put("password",et_password.getText().toString());
                params.put("alamat",et_alamat.getText().toString());
                params.put("jenis_kelamin",tv_jk.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }

}