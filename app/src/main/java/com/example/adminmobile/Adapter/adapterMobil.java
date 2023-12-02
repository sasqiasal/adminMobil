package com.example.adminmobile.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminmobile.R;
import com.example.adminmobile.Model.Mobil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapterMobil extends FirestoreRecyclerAdapter<Mobil, adapterMobil.ViewHolder> {
        Context context;
        public adapterMobil(FirestoreRecyclerOptions<Mobil> options,Context context) {
                super(options);
                this.context = context;
        }

        @Override
        protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Mobil model) {
                Log.d("Bind", "onBindViewHolder: " + model.getNama() +model.getGambar());

                holder.namaMobil.setText(model.getNama());
                holder.harga.setText(model.getHarga());
                Glide.with(context)
                        .load(model.getGambar())
                        .centerCrop()
                        .into(holder.gambar);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Context context = parent.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);
                View mobilView = inflater.inflate(R.layout.listmobil, parent, false);
                ViewHolder viewHolder = new ViewHolder(mobilView);
                return viewHolder;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
                public TextView namaMobil;
                public TextView harga;
                ImageView gambar;

                public ViewHolder(View itemView) {
                        super(itemView);
                        namaMobil = itemView.findViewById(R.id.merek);
                        harga = itemView.findViewById(R.id.detail);
                        gambar = itemView.findViewById(R.id.gmobil);
                }
        }
}
