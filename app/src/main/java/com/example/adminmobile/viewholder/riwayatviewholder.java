package com.example.adminmobile.viewholder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.R;

public class riwayatviewholder  extends RecyclerView.ViewHolder {
    public TextView namamobil, tuju,pinjam,inti,db;
    public RelativeLayout intinyainti;
    public riwayatviewholder(@NonNull View itemView) {
        super(itemView);
        namamobil = itemView.findViewById(R.id.namamobil);
        tuju = itemView.findViewById(R.id.tuju);
        pinjam = itemView.findViewById(R.id.pinjam);
        intinyainti = itemView.findViewById(R.id.intinyainti);

    }
}
