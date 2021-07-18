package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kospolijejember.dialog.CameraActivity;
import com.example.kospolijejember.dialog.GaleryActivity;
import com.example.kospolijejember.session.SessionManager;

import java.util.HashMap;

public class FormBukti_activity extends AppCompatActivity {

    String id,id_kos,id_pemilik,id_penyewa,namakos,namapemilik,namapenyewa,namakamar,jumlahkamar,hargakos,tgl_kos,lamasewa;
    TextView txt_jumlahkamar,txt_harga,txt_namakos,txt_namakamar;
    SessionManager sessionManager;
    TextView imagename;
    ImageView imageView;
    Button buttoncamera,buttongaleri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bukti);
        txt_harga = findViewById(R.id.txt_harga);
        txt_jumlahkamar = findViewById(R.id.txt_jumlahkamar);
        txt_namakos = findViewById(R.id.txt_namakos);
        txt_namakamar = findViewById(R.id.txt_namakamar);
        imagename = findViewById(R.id.imagename);
        imageView = findViewById(R.id.imageview);
        buttoncamera = findViewById(R.id.buttoncamera);
        buttongaleri = findViewById(R.id.buttongalery);

        sessionManager = new SessionManager(FormBukti_activity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_penyewa = user.get(SessionManager.ID_USER);
        namapenyewa = user.get(SessionManager.NAMA_USER);

        jumlahkamar = getIntent().getStringExtra("jumlahkamar");
        txt_jumlahkamar.setText(jumlahkamar);
        hargakos = getIntent().getStringExtra("hargakos");
        txt_harga.setText(hargakos);
        id = getIntent().getStringExtra("id");
        id_kos = getIntent().getStringExtra("id_kos");
        id_pemilik = getIntent().getStringExtra("id_pemilik");
        namakamar = getIntent().getStringExtra("namakamar");
        txt_namakamar.setText(namakamar);
        namakos = getIntent().getStringExtra("namakos");
        txt_namakos.setText(namakos);
        namapemilik = getIntent().getStringExtra("namapemilik");
        tgl_kos = getIntent().getStringExtra("tgl_kos");
        lamasewa = getIntent().getStringExtra("lamasewa");

        buttoncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraActivity cameraActivity = new CameraActivity();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("id_kos", id_kos);
                bundle.putString("id_pemilik", id_pemilik);
                bundle.putString("id_penyewa", id_penyewa);
                bundle.putString("namakamar", namakamar);
                bundle.putString("namakos", namakos);
                bundle.putString("namapemilik", namapemilik);
                bundle.putString("namapenyewa", namapenyewa);
                bundle.putString("tgl_kos", tgl_kos);
                bundle.putString("lamasewa", lamasewa);
                bundle.putString("jumlahkamar", jumlahkamar);
                bundle.putString("hargakos", hargakos);
                cameraActivity.show(getSupportFragmentManager(), "example dialog");

            }
        });

        buttongaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GaleryActivity galeryActivity = new GaleryActivity();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("id_kos", id_kos);
                bundle.putString("id_pemilik", id_pemilik);
                bundle.putString("id_penyewa", id_penyewa);
                bundle.putString("namakamar", namakamar);
                bundle.putString("namakos", namakos);
                bundle.putString("namapemilik", namapemilik);
                bundle.putString("namapenyewa", namapenyewa);
                bundle.putString("tgl_kos", tgl_kos);
                bundle.putString("lamasewa", lamasewa);
                bundle.putString("jumlahkamar", jumlahkamar);
                bundle.putString("hargakos", hargakos);
                galeryActivity.show(getSupportFragmentManager(), "example dialog");

            }
        });
    }
}