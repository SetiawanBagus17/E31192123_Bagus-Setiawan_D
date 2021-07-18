package com.example.kospolijejember.dialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kospolijejember.BerandaActivity;
import com.example.kospolijejember.R;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CameraActivity extends AppCompatDialogFragment {

    String id,id_kos,id_pemilik,id_penyewa,namakos,namapemilik,namapenyewa,namakamar,jumlahkamar,hargakos,tgl_kos,lamasewa;
    ImageView imageView;
    Button btn_kamera, btn_simpan;
    public static Bitmap bitmap_bukti;
    public static final int CAMERA_REQUEST_CODE  = 1;
    TextView text;
    String foto_bukti="kosong";

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_camera, null);

        text = view.findViewById(R.id.text);
        imageView = view.findViewById(R.id.imageView);
        btn_kamera = view.findViewById(R.id.btn_kamera);
        btn_simpan = view.findViewById(R.id.btn_simpan);

        id = getActivity().getIntent().getStringExtra("id");
        id_kos = getActivity().getIntent().getStringExtra("id_kos");
        id_pemilik = getActivity().getIntent().getStringExtra("id_pemilik");
        id_penyewa = getActivity().getIntent().getStringExtra("id_penyewa");
        namakamar = getActivity().getIntent().getStringExtra("namakamar");
        namakos = getActivity().getIntent().getStringExtra("namakos");
        namapemilik = getActivity().getIntent().getStringExtra("namapemilik");
        namapenyewa = getActivity().getIntent().getStringExtra("namapenyewa");
        tgl_kos = getActivity().getIntent().getStringExtra("tgl_kos");
        lamasewa = getActivity().getIntent().getStringExtra("lamasewa");
        hargakos = getActivity().getIntent().getStringExtra("hargakos");
        jumlahkamar = getActivity().getIntent().getStringExtra("jumlahkamar");
        text.setText(jumlahkamar);

        btn_kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_data();
            }
        });

        builder.setView(view);
        return builder.create();
    }
    private void BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);
        byte[]arr=baos.toByteArray();
        foto_bukti = Base64.encodeToString(arr,Base64.DEFAULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode){
                case(CAMERA_REQUEST_CODE) :
                    if(resultCode == Activity.RESULT_OK){
                        bitmap_bukti = (Bitmap) data.getExtras().get("data");
                        BitMapToString(bitmap_bukti);
                        imageView.setImageBitmap(bitmap_bukti);
                    }
                    break;
            }

        }

    private void simpan_data(){
        StringRequest insertData = new StringRequest(Request.Method.POST, ServerAPI.URL_transaksi_langsung, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("volley", "response insert to db : " + response.toString());
                try {
                    JSONObject data = new JSONObject(response);
                    String status_respon;
                    status_respon = data.getString("status");

                    if (status_respon.equals("berhasil")) {
                        Toast.makeText(getContext(), "Berhasil Menyimpan Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), BerandaActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Log.d("volley", "error insert tb : "+response.toString());
                        Toast.makeText(getContext(), "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley", "error insert tb : "+error.getMessage());
                Toast.makeText(getContext(), "Kesalahan Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id",id);
                params.put("id_kos",id_kos);
                params.put("id_pemilik",id_pemilik);
                params.put("id_penyewa",id_penyewa);
                params.put("namakamar",namakamar);
                params.put("namakos",namakos);
                params.put("namapemilik",namapemilik);
                params.put("namapenyewa",namapenyewa);
                params.put("bukti",foto_bukti);
                params.put("tgl_kos",tgl_kos);
                params.put("lamasewa",lamasewa);
                params.put("jumlahkamar",jumlahkamar);
                params.put("hargakos",hargakos);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(insertData);
    }
    }
