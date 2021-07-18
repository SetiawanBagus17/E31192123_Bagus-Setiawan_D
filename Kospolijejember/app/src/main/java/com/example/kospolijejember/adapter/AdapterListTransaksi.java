package com.example.kospolijejember.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kospolijejember.BerandaActivity;
import com.example.kospolijejember.FormBuktiPending_activity;
import com.example.kospolijejember.R;
import com.example.kospolijejember.model.Modeltransaksi;
import com.example.kospolijejember.server.AppController;
import com.example.kospolijejember.server.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterListTransaksi extends RecyclerView.Adapter<AdapterListTransaksi.HolderData> {
    private List<Modeltransaksi> mItems;
    private Context context;

    public AdapterListTransaksi(Context context, List<Modeltransaksi> items ){
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_checkout, parent, false);
        HolderData HolderData = new HolderData(layout);
        return HolderData;

    }

    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Modeltransaksi mk = mItems.get(position);

        holder.id_transaksi.setText(mk.getId());
        holder.nama_penyewa.setText(mk.getNamauser());
        holder.nama_kos.setText(mk.getNamakos());
        holder.pemilik_kos.setText(mk.getNamapemilik());
        holder.lama_kos.setText(mk.getLamasewa());
        holder.total_harga.setText(mk.getTotalharga());
        holder.tgl_kos.setText(mk.getTglmulai());
        holder.jumlah_kamar.setText(mk.getJumlahkamar());
        holder.nama_kamar.setText(mk.getNamakamar());

    }


    @Override
    public int getItemCount(){
        return mItems.size();
    }

    //    public int getItemCount() { return mItems.size();}

    class HolderData extends RecyclerView.ViewHolder {

        TextView id_transaksi, nama_penyewa, nama_kos,nama_kamar, pemilik_kos, lama_kos, total_harga,tgl_kos, jumlah_kamar;
        Button btn_delete;

        public HolderData(final View view) {
            super(view);
            id_transaksi = view.findViewById(R.id.id_transaksi);
            nama_penyewa = view.findViewById(R.id.nama_penyewa);
            nama_kos = view.findViewById(R.id.nama_kos);
            pemilik_kos = view.findViewById(R.id.pemilik_kos);
            lama_kos = view.findViewById(R.id.lama_kos);
            total_harga = view.findViewById(R.id.total_harga);
            tgl_kos = view.findViewById(R.id.tgl_kos);
            jumlah_kamar = view.findViewById(R.id.jumlah_kamar);
            nama_kamar = view.findViewById(R.id.nama_kamar);
            btn_delete = view.findViewById(R.id.btn_delete);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteData();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), FormBuktiPending_activity.class);
                    intent.putExtra("id",id_transaksi.getText().toString());
                    intent.putExtra("hargakos",total_harga.getText().toString());
                    intent.putExtra("namakos",nama_kos.getText().toString());
                    intent.putExtra("namakamar",nama_kamar.getText().toString());
                    intent.putExtra("namapemilik",pemilik_kos.getText().toString());
                    intent.putExtra("namapenyewa",nama_penyewa.getText().toString());
                    intent.putExtra("jumlahkamar",jumlah_kamar.getText().toString());
                    intent.putExtra("tgl_kos",tgl_kos.getText().toString());
                    intent.putExtra("lamasewa",lama_kos.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }

        private void deleteData(){

            StringRequest delData = new StringRequest(Request.Method.POST, ServerAPI.URL_delete_transaksi,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("volley","response delete to db hewan : "+response.toString());
                            try {
                                JSONObject data = new JSONObject(response);
                                String status_respon;
                                status_respon = data.getString("status");

                                if(status_respon.equals("berhasil")){
                                    Toast.makeText(context, "Sukses Perubahan Data", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, BerandaActivity.class);
                                    context.startActivity(intent);

                                }else{
                                    Toast.makeText(context, "Kesalahan Server", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("volley", "error delete tb hewan : "+error.getMessage());
                            Toast.makeText(context, "Terjadi Masalah", Toast.LENGTH_SHORT).show();

                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id",id_transaksi.getText().toString());
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(delData);
        }
    }
}
