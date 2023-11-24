package com.example.adminmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapterMobil extends RecyclerView.Adapter<adapterMobil.ViewHolder> {
        private List<String> data;
        public TextView iconHapus;
        public TextView iconEdit;
        private Context context;
        private ArrayList<Mobil> dataList; // Replace YourDataModel with the actual data model you are using

        public adapterMobil(ArrayList<Mobil> dataList, Context context) {
                this.context = context;
                this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmobil, parent, false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Mobil model = dataList.get(position);
                Picasso.get().load(model.getGambar()).placeholder(R.drawable.addimage).into(holder.itemgambar);
                holder.textView.setText(model.getMerekmobil());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent(context, ListMobil.class);
                                intent.putExtra("Mobil", model.getGambar());
                                intent.putExtra("Merk", model.getMerekmobil());
                                intent.putExtra("Harga", model.getHarga());
                                intent.putExtra("JmlKursi", model.getJmlkursi());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                        }
                });

                iconHapus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                popuphapus dialogFragment = new popuphapus();
                                dialogFragment.show(dialogFragment.getChildFragmentManager(), "iconHapus");
                        }
                });
                iconEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                iconEdit.getContext().startActivity(new Intent(context, editDataMobil.class));
                        }
                });
        }

        @Override
        public int getItemCount() {
                return 0;
        }




        public adapterMobil(List<String> data) {
                this.data = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {



                TextView textView; // Replace with the actual views you have in your item layout
                ImageView itemgambar;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        iconHapus = itemView.findViewById(R.id.buttonhapus);
                        textView = itemView.findViewById(R.id.merek);
                        itemgambar = itemView.findViewById(R.id.addimage);
                }
        }
}
