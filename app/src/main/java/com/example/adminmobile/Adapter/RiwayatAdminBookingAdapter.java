package com.example.adminmobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.example.adminmobile.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RiwayatAdminBookingAdapter  extends FirestoreRecyclerAdapter<RiwayatBokingAdminModel,RiwayatAdminBookingAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RiwayatAdminBookingAdapter(@NonNull FirestoreRecyclerOptions<RiwayatBokingAdminModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RiwayatBokingAdminModel model) {
        holder.namamobil.setText(model.getIDMobil());
        holder.tuju.setText(model.getTujuan());


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listbokingadmin,parent,false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namamobil, tuju, pinjam;
        RelativeLayout inti;
        Context context;
        FirebaseFirestore db;
        public ViewHolder(@NonNull View itemView) {
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
