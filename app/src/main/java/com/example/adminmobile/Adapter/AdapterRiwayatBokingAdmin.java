package com.example.adminmobile.Adapter;


import static com.example.adminmobile.BokingAdmin.DetailRiwayatBokingAdmin.formatFirestoreTimestamp;

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

import com.example.adminmobile.BokingAdmin.DetailRiwayatBokingAdmin;
import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.example.adminmobile.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterRiwayatBokingAdmin extends FirestoreRecyclerAdapter<RiwayatBokingAdminModel, AdapterRiwayatBokingAdmin.ViewHolder> {
    public AdapterRiwayatBokingAdmin(FirestoreRecyclerOptions<RiwayatBokingAdminModel> options) {
        super(options);
    }



    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RiwayatBokingAdminModel model) {
        Log.d("TestID", "onBindViewHolder: "+ model.getUID());
        holder.namamobil.setText(model.getIDMobil());
        holder.tuju.setText(model.getTujuan());
        holder.pinjam.setText(formatFirestoreTimestamp(model.getTanggalPinjam()));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Data_Mobil").document(model.getIDMobil()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                holder.namamobil.setText(documentSnapshot.getString("Nama"));
            }
        });

        holder.inti.setOnClickListener(view -> {
            Intent intent = new Intent(holder.context, DetailRiwayatBokingAdmin.class);

            intent.putExtra("uid", model.getUID());
            intent.putExtra("tujuan",model.getTujuan());
            intent.putExtra("tanggalpinjam",ViewHolder.formatFirestoreTimestampp(model.getTanggalPinjam().toDate()));
            intent.putExtra("hari",model.getJumlahHari());
            intent.putExtra("total",model.getTotal());




            holder.context.startActivity(intent);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namamobil, tuju, pinjam;
        RelativeLayout inti;
        Context context;
        FirebaseFirestore db;

        public ViewHolder(View itemView) {
            super(itemView);

            namamobil = itemView.findViewById(R.id.namamobilbooking);
            context = itemView.getContext();
            db = FirebaseFirestore.getInstance();
            inti = itemView.findViewById(R.id.container);
            tuju = itemView.findViewById(R.id.tuju);
            pinjam = itemView.findViewById(R.id.pinjam);



        }
        public static String formatFirestoreTimestampp(Date firestoreTimestamp) {
            // Convert Firestore timestamp to Java Date object


            // Format the date to "dd-MMMM-yyyy"
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
            return dateFormat.format(firestoreTimestamp);
        }
    }
}
