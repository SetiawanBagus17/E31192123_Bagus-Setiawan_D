package com.example.kospolijejember.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kospolijejember.EditProfile;
import com.example.kospolijejember.R;
import com.example.kospolijejember.session.SessionManager;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

 SessionManager sessionManager;
 Button btn_logout,btn_edit_profile;
 TextView txt_nama,txt_alamat,txt_namauser,txt_notelp,txt_kelamin;
 String nama,alamat,namauser,notelp,kelamin;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_nama = view.findViewById(R.id.txt_nama);
        txt_alamat = view.findViewById(R.id.txt_alamat);
        txt_namauser = view.findViewById(R.id.txt_namauser);
        txt_kelamin = view.findViewById(R.id.txt_kelamin);
        txt_notelp = view.findViewById(R.id.txt_notelp);

        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();

        // mendeklarasikan string dari nilai session
        nama = user.get(SessionManager.NAMA_USER);
        alamat = user.get(SessionManager.ALAMAT);
        namauser = user.get(SessionManager.USERNAME);
        kelamin = user.get(SessionManager.JENIS_KELAMIN);
        notelp = user.get(sessionManager.NO_TELP);

        //Mengisi data dari session
        txt_nama.setText(nama);
        txt_alamat.setText(alamat);
        txt_notelp.setText(notelp);
        txt_kelamin.setText(kelamin);
        txt_namauser.setText(namauser);

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_edit_profile = view.findViewById(R.id.btn_edit_profile);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager((view.getContext()));
                sessionManager.logoutUser();
            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditProfile.class);
                intent.putExtra("nama",nama);
                intent.putExtra("alamat",alamat);
                intent.putExtra("namauser",namauser);
                intent.putExtra("kelamin",kelamin);
                intent.putExtra("notelp",notelp);
                startActivity(intent);
            }
        });

        return view;
    }
}