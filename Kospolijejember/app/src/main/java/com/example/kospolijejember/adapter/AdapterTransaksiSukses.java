package com.example.kospolijejember.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kospolijejember.Notatransaksi_activity;
import com.example.kospolijejember.R;
import com.example.kospolijejember.model.Modeltransaksi;

import java.util.List;

public class AdapterTransaksiSukses extends RecyclerView.Adapter<AdapterTransaksiSukses.HolderData> {

    private List<Modeltransaksi> mItems;
    private Context context;

    public AdapterTransaksiSukses(Context context, List<Modeltransaksi> items) {
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_transaksi, parent, false);
        HolderData HolderData = new HolderData(layout);
        return HolderData;

    }

    public void onBindViewHolder(@NonNull AdapterTransaksiSukses.HolderData holder, int position) {
        Modeltransaksi mk = mItems.get(position);

        holder.id_transaksi.setText(mk.getId());
        holder.id_kos.setText(mk.getId_kos());
        holder.nama_penyewa.setText(mk.getNamauser());
        holder.nama_kos.setText(mk.getNamakos());
        holder.pemilik_kos.setText(mk.getNamapemilik());
        holder.lama_kos.setText(mk.getLamasewa());
        holder.total_harga.setText(mk.getTotalharga());
        holder.tgl_kos.setText(mk.getTglmulai());
        holder.jumlah_kamar.setText(mk.getJumlahkamar());
        holder.tv_image.setText(mk.getBuktipembayaran());
        holder.nama_kamar.setText(mk.getNamakamar());
        holder.tgl_transaksi.setText(mk.getTgl_transaksi());

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    //    public int getItemCount() { return mItems.size();}

    class HolderData extends RecyclerView.ViewHolder {

        TextView id_kos,id_transaksi, nama_penyewa, nama_kos, pemilik_kos, lama_kos, total_harga, tgl_kos, jumlah_kamar, tv_image, nama_kamar,tgl_transaksi;

        public HolderData(final View view) {
            super(view);
            id_transaksi = view.findViewById(R.id.id_transaksi);
            id_kos = view.findViewById(R.id.id_kos);
            nama_penyewa = view.findViewById(R.id.nama_penyewa);
            nama_kos = view.findViewById(R.id.nama_kos);
            pemilik_kos = view.findViewById(R.id.pemilik_kos);
            lama_kos = view.findViewById(R.id.lama_kos);
            total_harga = view.findViewById(R.id.total_harga);
            tgl_kos = view.findViewById(R.id.tgl_kos);
            jumlah_kamar = view.findViewById(R.id.jumlah_kamar);
            tv_image = view.findViewById(R.id.tv_image);
            nama_kamar = view.findViewById(R.id.nama_kamar);
            tgl_transaksi = view.findViewById(R.id.tgl_transaksi);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), Notatransaksi_activity.class);
                    intent.putExtra("id", id_transaksi.getText().toString());
                    intent.putExtra("id_kos", id_kos.getText().toString());
                    intent.putExtra("hargakos", total_harga.getText().toString());
                    intent.putExtra("namakos", nama_kos.getText().toString());
                    intent.putExtra("namakamar", nama_kamar.getText().toString());
                    intent.putExtra("namapemilik", pemilik_kos.getText().toString());
                    intent.putExtra("namapenyewa", nama_penyewa.getText().toString());
                    intent.putExtra("jumlahkamar", jumlah_kamar.getText().toString());
                    intent.putExtra("tgl_kos", tgl_kos.getText().toString());
                    intent.putExtra("lamasewa", lama_kos.getText().toString());
                    intent.putExtra("bukti", tv_image.getText().toString());
                    intent.putExtra("transaksi", tgl_transaksi.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
