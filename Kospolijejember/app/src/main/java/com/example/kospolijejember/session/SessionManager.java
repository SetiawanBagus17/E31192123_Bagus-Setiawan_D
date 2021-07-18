package com.example.kospolijejember.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.kospolijejember.BerandaActivity;
import com.example.kospolijejember.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID_USER = "ID_USER";
    public static final String NAMA_USER = "NAMA_USER";
    public static final String ALAMAT = "AlAMAT_USER";
    public static final String NO_TELP = "NO_TELP";
    public static final String JENIS_KELAMIN ="JENIS_KELAMIN" ;
    public static final String USERNAME ="USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String STATUS_LOGIN = "STATUS LOGIN";

    public SessionManager (Context context) {
        this.context =context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void sessionUser (String id_user,String nama_user,String alamat,String no_telp,String jenis_kelamin,String username,String password,String status_login ){

       editor.putBoolean(LOGIN, true);
       editor.putString(ID_USER, id_user);
       editor.putString(NAMA_USER, nama_user);
       editor.putString(ALAMAT, alamat);
       editor.putString(NO_TELP, no_telp);
       editor.putString(JENIS_KELAMIN, jenis_kelamin);
       editor.putString(USERNAME, username);
       editor.putString(PASSWORD, password);
       editor.putString(STATUS_LOGIN, status_login);
       editor.apply();
    }

    public boolean isLoginUser(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLoginUser(){
        if (!this.isLoginUser()){
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((BerandaActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(ID_USER, sharedPreferences.getString(ID_USER, null));
        user.put(NAMA_USER, sharedPreferences.getString(NAMA_USER, null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        user.put(NO_TELP, sharedPreferences.getString(NO_TELP, null));
        user.put(JENIS_KELAMIN, sharedPreferences.getString(JENIS_KELAMIN, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((LoginActivity) context).finish();
    }
}
