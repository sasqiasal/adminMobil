package com.example.adminmobile.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminmobile.Model.Mobil;
import com.example.adminmobile.Popup.popuphapus;
import com.example.adminmobile.R;
import com.example.adminmobile.Mobil.TambahMobil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdapterMobil extends FirestoreRecyclerAdapter<Mobil, AdapterMobil.ViewHolder> {
        Context context;
        FragmentManager fragmentManager;
        public AdapterMobil(FirestoreRecyclerOptions<Mobil> options, Context context,FragmentManager fM) {
                super(options);
                this.context = context;
                this.fragmentManager = fM;
        }

        @Override
        protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Mobil model) {
                Log.d("Bind", "onBindViewHolder: " +model.getKursi() + model.getNama() +model.getGambar());

                holder.namaMobil.setText(model.getNama());
                holder.harga.setText(model.getHarga());
                Glide.with(context)
                        .load(model.getGambar())
                        .centerCrop()
                        .into(holder.gambar);
                holder.cvMobil.setOnClickListener(new View.OnClickListener() {
                        @Override
         public void onClick(View v) {
          new AlertDialog.Builder(context)
          .setItems(new String[]{"Update", "Delete"}, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
          switch (which) {
          case 0:
          Intent intent = new Intent(context, TambahMobil.class);
          intent.putExtra("namaMobil",model.getNama());
          intent.putExtra("harga",model.getHarga());
          intent.putExtra("kursi",model.getKursi());
          intent.putExtra("gambar",model.getGambar());
          intent.putExtra("id", model.getDocumentId().toString());
          context.startActivity(intent);
          break;
           case 1:
               popuphapus.newInstance(model.getDocumentId(),"").show(fragmentManager,"Hapus");
            break;
          }
          }
          }).show();
                        }
                });
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
                FirebaseFirestore db;

                CardView cvMobil;

                public ViewHolder(View itemView) {
                        super(itemView);
                        namaMobil = itemView.findViewById(R.id.merek);
                        harga = itemView.findViewById(R.id.detail);
                        gambar = itemView.findViewById(R.id.gmobil);
                        cvMobil = itemView.findViewById(R.id.CVMobil);
                        db = FirebaseFirestore.getInstance();
                }
        }
}
