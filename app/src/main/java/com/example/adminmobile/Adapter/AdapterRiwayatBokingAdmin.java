package com.example.adminmobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.DetailRiwayatBokingAdmin;
import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.example.adminmobile.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
public class AdapterRiwayatBokingAdmin extends FirestoreRecyclerAdapter<RiwayatBokingAdminModel, AdapterRiwayatBokingAdmin.ViewHolder> {
    Context context;
    public AdapterRiwayatBokingAdmin(FirestoreRecyclerOptions<RiwayatBokingAdminModel> options, Context context) {super(options); this.context = context;}



    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RiwayatBokingAdminModel model) {
        Log.d("Bind", "onBindViewHolder: " + "namamobil" +model.getNamaMobil() + "tujuan" +model.getTujuan() +"pinjam" + model.getTanggalPinjam());


        holder.namamobil.setText(model.getNamaMobil());
        holder.tuju.setText(model.getTujuan());
        holder.pinjam.setText(model.getTanggalPinjam());

        holder.inti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailRiwayatBokingAdmin.class);

                intent.putExtra("tujuan",model.getTujuan());
                intent.putExtra("tanggalpinjam",model.getTanggalPinjam());
                intent.putExtra("hari",model.getHari());
                intent.putExtra("total",model.getTotal());
                intent.putExtra("uid", model.getDocumentId().toString());



                context.startActivity(intent);
            }
        });
    }
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mobilView = inflater.inflate(R.layout.listbokingadmin,parent,false);
        ViewHolder viewHolder = new ViewHolder(mobilView);
        return viewHolder;

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namamobil, tuju, pinjam;
        RelativeLayout inti;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class detailRiwayat {
    }
}