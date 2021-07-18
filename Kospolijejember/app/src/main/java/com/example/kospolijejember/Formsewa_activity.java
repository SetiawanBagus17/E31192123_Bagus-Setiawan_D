package com.example.kospolijejember;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kospolijejember.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class Formsewa_activity extends AppCompatActivity {

    private Spinner sp_lamakos;
    private String[] list = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    String id,namakamar,id_kos,namakos,id_pemilik,namapemilik,harga,id_penyewa,namapenyewa,hasil,jmlkamar;
    Calendar cl;
    DatePickerDialog dpl;
    TextView max,value_hasil,txt_calendar,txt_id,txt_namakos,txt_namapemilik,txt_harga,txt_namapenyewa,tv_bulan,tv_hasil,txt_namakamar,txt_idkos,txt_idpemilik,txt_idpenyewa;
    ImageView img_calendar;
    SessionManager sessionManager;
    EditText edt_jumlahkamar;
    Button btn_pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formsewa);
        sp_lamakos = findViewById(R.id.sp_lamakos);
        txt_id = findViewById(R.id.txt_id);
        txt_namakos = findViewById(R.id.txt_namakos);
        txt_namapemilik = findViewById(R.id.txt_namapemilik);
        txt_namakamar = findViewById(R.id.txt_namakamar);
        txt_harga = findViewById(R.id.txt_harga);
        txt_calendar = findViewById(R.id.txt_calendar);
        value_hasil = findViewById(R.id.value_hasil);
        edt_jumlahkamar = findViewById(R.id.txt_jumlkamar);
        img_calendar = findViewById(R.id.img_calendar);
        txt_namapenyewa = findViewById(R.id.txt_namapenyewa);
        btn_pesan = findViewById(R.id.btn_pesan);
        tv_bulan = findViewById(R.id.tv_bulan);
        tv_hasil = findViewById(R.id.tv_hasil);
        txt_idkos = findViewById(R.id.txt_idkos);
        txt_idpemilik = findViewById(R.id.txt_idpemilik);
        txt_idpenyewa = findViewById(R.id.txt_idpenyewa);
        max = findViewById(R.id.max);

        sessionManager = new SessionManager(Formsewa_activity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();

        id_penyewa = user.get(SessionManager.ID_USER);
        txt_idpenyewa.setText(id_penyewa);

        namapenyewa = user.get(SessionManager.NAMA_USER);
        txt_namapenyewa.setText(namapenyewa);

        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cl = Calendar.getInstance();
                int day = cl.get(Calendar.DAY_OF_MONTH);
                int month = cl.get(Calendar.MONTH);
                int year = cl.get(Calendar.YEAR);

                dpl = new  DatePickerDialog(Formsewa_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int value = Integer.parseInt(value_hasil.getText().toString());
                        int hasil = month + value;
                        txt_calendar.setText(year + "-" + hasil + "-" + dayOfMonth);
                    }
                }, year,month,day);
                dpl.show();
            }
        });


        id = getIntent().getStringExtra("id");
        txt_id.setText(id);

        namakamar = getIntent().getStringExtra("nama_kamar");
        txt_namakamar.setText(namakamar);

        id_kos = getIntent().getStringExtra("id_kos");
        txt_idkos.setText(id_kos);

        namakos = getIntent().getStringExtra("nama_kos");
        txt_namakos.setText(namakos);

        id_pemilik = getIntent().getStringExtra("id_pemilik");
        txt_idpemilik.setText(id_pemilik);

        namapemilik = getIntent().getStringExtra("nama_pemilik");
        txt_namapemilik.setText(namapemilik);

        jmlkamar = getIntent().getStringExtra("jumlahkamar");
        max.setText(jmlkamar);

        harga = getIntent().getStringExtra("hargakamar");
        txt_harga.setText(harga);

        edt_jumlahkamar.setHint("Masukkan Jumlah Kamar (max "+jmlkamar+" kamar)");
        edt_jumlahkamar.setTextSize(14);

        edt_jumlahkamar.getText().toString().trim();

        ArrayAdapter adapter_lamakos = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter_lamakos.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_lamakos.setAdapter(adapter_lamakos);

        sp_lamakos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_bulan.setText(list[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Formsewa_activity.this,"Lama Sewa Belum Dipilih",Toast.LENGTH_SHORT);
            }
        });

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_jumlahkamar.getText().toString().isEmpty()){
                    edt_jumlahkamar.setError("Belum memilih Jumlah Kamar!");
                    edt_jumlahkamar.requestFocus();
                    return;
                }
                if (txt_calendar.getText().toString().isEmpty()) {
                    txt_calendar.setError("Belum memilih Tanggal!");
                    txt_calendar.requestFocus();
                    return;
                } else {

                    int sharga = Integer.parseInt(txt_harga.getText().toString());
                    int sbulan = Integer.parseInt(tv_bulan.getText().toString());
                    int skamar = Integer.parseInt(edt_jumlahkamar.getText().toString());
                    int shasil = (sharga * sbulan) * skamar;
                    tv_hasil.setText(String.valueOf(shasil));
                    Intent pindahhalaman = new Intent(Formsewa_activity.this, checkout_activity.class);
                    pindahhalaman.putExtra("id", id);
                    pindahhalaman.putExtra("namakamar", namakamar);
                    pindahhalaman.putExtra("id_kos", id_kos);
                    pindahhalaman.putExtra("namakos", txt_namakos.getText().toString());
                    pindahhalaman.putExtra("id_pemilik", id_pemilik);
                    pindahhalaman.putExtra("namapemilik", namapemilik);
                    pindahhalaman.putExtra("id_penyewa", id_penyewa);
                    pindahhalaman.putExtra("namapenyewa", namapenyewa);
                    pindahhalaman.putExtra("jumlahkamar", edt_jumlahkamar.getText().toString());
                    pindahhalaman.putExtra("tgl_kos", txt_calendar.getText().toString());
                    pindahhalaman.putExtra("hargakos", tv_hasil.getText().toString());
                    pindahhalaman.putExtra("lamasewa", tv_bulan.getText().toString());
                    startActivity(pindahhalaman);
                }
            }
        });
    }

}