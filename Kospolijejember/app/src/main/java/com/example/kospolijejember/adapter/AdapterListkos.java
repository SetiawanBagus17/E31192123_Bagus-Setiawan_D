package com.example.kospolijejember.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kospolijejember.Detailkos_activity;
import com.example.kospolijejember.R;
import com.example.kospolijejember.model.Modelkos;
import com.example.kospolijejember.server.ServerAPI;

import java.util.List;

public class AdapterListkos extends RecyclerView.Adapter<AdapterListkos.HolderData> {
    private List<Modelkos> mItems;
    private Context context;

    public AdapterListkos(Context context, List<Modelkos> items){
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kos,parent, false);
        HolderData HolderData = new HolderData(layout);
        return HolderData;
    }

    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        Modelkos mk = mItems.get(position);

        String img= ServerAPI.URL_IMAGEKOS + mk.getFoto1();
        holder.id_kos.setText(mk.getId());
        holder.tv_namakos.setText(mk.getNamakos());
        holder.tv_telp.setText(mk.getNo_telp());
        holder.tv_kecamatan.setText(mk.getKecamatan());
        Glide.with(context).load(img).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.fotokos);
    }


    @Override
    public int getItemCount(){
        return mItems.size();
    }

    //    public int getItemCount() { return mItems.size();}

    class HolderData extends RecyclerView.ViewHolder {

        ImageView fotokos;
        TextView tv_namakos, tv_kecamatan, id_kos, tv_telp;

        public HolderData(final View view) {
            super(view);
            tv_namakos = view.findViewById(R.id.tv_namakos);
            tv_kecamatan = view.findViewById(R.id.tv_kecamatan);
            id_kos = view.findViewById(R.id.id_kos);
            tv_telp = view.findViewById(R.id.tv_telp);
            fotokos = view.findViewById(R.id.fotokos);

            view.setOnClickListener(v -> {
                Intent intent = new Intent(view.getContext(), Detailkos_activity.class);
                intent.putExtra("id",id_kos.getText().toString());
                intent.putExtra("no_telp",tv_telp.getText().toString());
                view.getContext().startActivity(intent);
            });
        }

    }
}
