package com.example.kospolijejember;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    EditText et_username,et_password;
    Button btn_login;
    TextView tv_regis;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_regis = findViewById(R.id.tv_regis);
        getPermission();
        tv_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        sessionManager = new SessionManager(this);

        et_username= findViewById(R.id.et_username);
        et_password= findViewById(R.id.et_password);

        btn_login= findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Susername = et_username.getText().toString();
                String Spassword = et_password.getText().toString();

                if (Susername.equals("")){
                    et_username.setError("Anda belum menginputkan username");
                    et_username.requestFocus();
                }
                if (Spassword.equals("")){
                    et_password.setError("Anda belum menginputkan password");
                    et_password.requestFocus();
                } else
                    kirim_data();
            }
        });

    }

    void kirim_data(){

        StringRequest selectUser = new StringRequest(Request.Method.POST, ServerAPI.URL_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("volley","response : " + response);

                        try {
                            JSONObject data = new JSONObject(response);
                            String status_login = data.getString("status_login");

                            if (status_login.equals("berhasil")){

                                String id_user = data.getString("id");
                                String nama_user = data.getString("Nama");
                                String alamat = data.getString("Alamat");
                                String jenis_kelamin =data.getString("Jenis_kelamin");
                                String no_telp = data.getString("No_telp");
                                String username = data.getString("Username");
                                String password = data.getString("Password");

                                sessionManager.sessionUser(id_user, nama_user, alamat, no_telp, jenis_kelamin, username, password, status_login);
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),BerandaActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (status_login.equals("gagal")){
                                Toast.makeText(LoginActivity.this, "Username Atau Password salah", Toast.LENGTH_SHORT).show();
                            }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }
                },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                        Log.d ("volley", "Error : " + error.getMessage());
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }) {
                        @Override
                        protected Map<String, String> getParams(){
                            Map<String, String> params = new HashMap<>();
                            params.put("Username",et_username.getText().toString());
                            params.put("Password", et_password.getText().toString());
                            return params;
                        }
                };
        AppController.getInstance().addToRequestQueue(selectUser);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @AfterPermissionGranted(123)
    private void getPermission(){
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)){
            Log.d("LOGIN", "getPermission: oke");
        }else {
            EasyPermissions.requestPermissions(this, "Lokasi dibutuhkan untuk pengalaman lebih baik",
                    123, perms);
        }
    }
}
