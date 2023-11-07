package com.example.adminmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterMobil extends RecyclerView.Adapter<adapterMobil.ViewHolder> {

        private List<String> data;
public TextView iconHapus;
public TextView iconEdit;
private Context context;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmobil,parent,false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

        class ViewHolder extends RecyclerView.ViewHolder{
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);

                      iconHapus=   itemView.findViewById(R.id.buttonhapus);
                }
        }

        public adapterMobil(List<String> data) {
                this.data = data;
        }
}
