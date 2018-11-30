package com.dionrasmadi.lelangapp.listJual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dionrasmadi.lelangapp.R;
import com.dionrasmadi.lelangapp.dataClass.DataUser;
import com.dionrasmadi.lelangapp.listDetailBarang.DetailPenjualActivity;

import java.util.List;

public class AdapterPenjual extends RecyclerView.Adapter<AdapterPenjual.PenjualViewHolder>{
    private Context ctx;
    private List<DataUser> users;

    public AdapterPenjual(Context ctx, List<DataUser> users) {
        this.ctx = ctx;
        this.users = users;
    }

    @NonNull
    @Override
    public PenjualViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_penjual, null);

        return new PenjualViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenjualViewHolder holder, int position) {
        final DataUser dataUser = users.get(position);
        holder.tvNama.setText(dataUser.getNama());
        holder.tvAlamat.setText(dataUser.getAlamat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, DetailPenjualActivity.class );
                Bundle extras = new Bundle();
                extras.putString("username", dataUser.getUsername());
                extras.putString("nama", dataUser.getNama());
                extras.putString("tlf", dataUser.getTlf());
                extras.putString("alamat", dataUser.getAlamat());
                i.putExtras(extras);
                ctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class PenjualViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat;
        public PenjualViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
        }
    }


}
