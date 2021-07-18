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
import com.example.kospolijejember.Detailkamar_activity;
import com.example.kospolijejember.R;
import com.example.kospolijejember.model.Modelkamar;
import com.example.kospolijejember.server.ServerAPI;

import java.util.List;

public class AdapterListKamar extends RecyclerView.Adapter<AdapterListKamar.HolderData> {

    private List<Modelkamar> mItems;
    private Context context;

    public AdapterListKamar(Context context, List<Modelkamar> items){
        this.mItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kamar,parent, false);
        HolderData HolderData = new HolderData(layout);
        return HolderData;
    }

    public void onBindViewHolder(@NonNull AdapterListKamar.HolderData holder, int position) {
        Modelkamar mk = mItems.get(position);

        String img= ServerAPI.URL_IMAGEKAMAR + mk.getFoto();
        holder.id_kamar.setText(mk.getId());
        holder.tv_namakamar.setText(mk.getNamakamar());
        holder.tv_jmlkamar.setText(mk.getJumlahkamar());
        holder.tv_hargakamar.setText(mk.getHargakamar());
        Glide.with(context).load(img).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.fotokamar);
    }


    @Override
    public int getItemCount(){
        return mItems.size();
    }

    //    public int getItemCount() { return mItems.size();}

    class HolderData extends RecyclerView.ViewHolder {

        ImageView fotokamar;
        TextView tv_namakamar, tv_jmlkamar,tv_hargakamar, id_kamar;

        public HolderData(final View view) {
            super(view);
            tv_namakamar = view.findViewById(R.id.tv_namakamar);
            tv_jmlkamar = view.findViewById(R.id.tv_jmlkamar);
            id_kamar = view.findViewById(R.id.id_kamar);
            tv_hargakamar = view.findViewById(R.id.tv_hargakamar);
            fotokamar = view.findViewById(R.id.fotokamar);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), Detailkamar_activity.class);
                    intent.putExtra("id",id_kamar.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


}
