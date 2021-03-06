package com.dionrasmadi.lelangapp.listDetailBarang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataBarang;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.BarangViewHolder>{

    private Context ctx;
    private List<DataBarang> barang;

    public AdapterBarang(Context ctx, List<DataBarang> barang) {
        this.ctx = ctx;
        this.barang = barang;
    }

    @NonNull
    @Override
    public AdapterBarang.BarangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_barang, null);

        return new AdapterBarang.BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.BarangViewHolder holder, int position) {
        final DataBarang dataBarang = barang.get(position);
        holder.tvNamaBarang.setText(dataBarang.getNama_barang());
        holder.tvNamaPenjual.setText(dataBarang.getNama_penjual());
        holder.tvAlamat.setText(dataBarang.getAlamat_penjual());
        holder.tvHarga.setText(dataBarang.getHarga_tawar());
        Glide.with(ctx)
                .load(dataBarang.getGambar())
                .into(holder.ivBarang);
    }

    @Override
    public int getItemCount() {
        return barang.size();
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBarang;
        TextView tvNamaPenjual, tvNamaBarang, tvAlamat, tvHarga;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);

            ivBarang = itemView.findViewById(R.id.iv_barang);
            tvNamaPenjual = itemView.findViewById(R.id.tv_nama_penjual);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang);
            tvAlamat = itemView.findViewById(R.id.tv_alamat_penjual);
            tvHarga = itemView.findViewById(R.id.tv_harga_barang);

        }
    }
}
