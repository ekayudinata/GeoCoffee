package com.dionrasmadi.lelangapp.listLelang;

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
import com.dionrasmadi.lelangapp.dataClass.DataLelangSaya;

import java.util.List;

public class LelangAdapter extends RecyclerView.Adapter<LelangAdapter.LelangViewHolder>{

    private Context ctx;
    private List<DataLelangSaya> barang;

    public LelangAdapter(Context ctx, List<DataLelangSaya> barang) {
        this.ctx = ctx;
        this.barang = barang;
    }

    @NonNull
    @Override
    public LelangAdapter.LelangViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_lelang_saya, null);

        return new LelangAdapter.LelangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LelangAdapter.LelangViewHolder holder, int position) {
        final DataLelangSaya dataBarang = barang.get(position);
        holder.tvNamaBarang.setText(dataBarang.getNama_barang());
        holder.tvHarga.setText(dataBarang.getHarga_tawar());
        Glide.with(ctx)
                .load(dataBarang.getGambar())
                .into(holder.ivBarang);
    }

    @Override
    public int getItemCount() {
        return barang.size();
    }

    public class LelangViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBarang;
        TextView tvNamaPenjual, tvNamaBarang, tvAlamat, tvHarga;

        public LelangViewHolder(@NonNull View itemView) {
            super(itemView);

            ivBarang = itemView.findViewById(R.id.iv_barang);
            tvNamaPenjual = itemView.findViewById(R.id.tv_nama_penjual);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang);
            tvAlamat = itemView.findViewById(R.id.tv_alamat_penjual);
            tvHarga = itemView.findViewById(R.id.tv_harga_barang);

        }
    }
}
