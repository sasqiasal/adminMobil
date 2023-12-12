package com.example.adminmobile.Adapter;

import static com.example.adminmobile.DetailRiwayat.formatFirestoreTimestamp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.DetailRiwayat;
import com.example.adminmobile.Model.riwayatmodel;
import com.example.adminmobile.R;
import com.example.adminmobile.viewholder.riwayatviewholder;

import java.util.List;

public class AdapterRiwayat extends RecyclerView.Adapter<riwayatviewholder>{
    List<riwayatmodel> dataRes;
    private Context context;

    public AdapterRiwayat(List<riwayatmodel> dataRes, Context context) {
        this.dataRes = dataRes;
        this.context = context;
    }

    @NonNull
    @Override
    public riwayatviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new riwayatviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_riwayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull riwayatviewholder holder, int position) {
        holder.namamobil.setText(dataRes.get(position).getIDMobil());
        holder.tuju.setText(dataRes.get(position).getTujuan());
        holder.pinjam.setText(formatFirestoreTimestamp(dataRes.get(position).getTanggalPinjam()));
        String hp, tujuan, total, namapenyewa, penjemputan, namamobil;
        String pinjam, kembali;
        int hari;
        hp = dataRes.get(position).getNoHp();
        tujuan = dataRes.get(position).getTujuan();
        namamobil = dataRes.get(position).getTujuan();
        total = dataRes.get(position).getTotal();
        hari = dataRes.get(position).getJumlahHari();
        pinjam = formatFirestoreTimestamp(dataRes.get(position).getTanggalPinjam());
        kembali = formatFirestoreTimestamp(dataRes.get(position).getTanggalKembali());
        namapenyewa = dataRes.get(position).getNamaPenyewa();
        penjemputan = dataRes.get(position).getPenjemputan();

        holder.intinyainti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailRiwayat.class);
                intent.putExtra("NoHp", hp);
                intent.putExtra("tujuan", tujuan);
                intent.putExtra("total", total);
                intent.putExtra("hari", hari);
                intent.putExtra("namapenyewa", namapenyewa);
                intent.putExtra("penjemputan", penjemputan);
                intent.putExtra("tanggalpinjam", pinjam);
                intent.putExtra("tanggalkembali", kembali);
                intent.putExtra("mobil", namamobil);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return dataRes.size();
    }
}
