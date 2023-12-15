package com.example.adminmobile.Riwayatcustumuer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.Adapter.AdapterRiwayat;
import com.example.adminmobile.Model.riwayatmodel;
import com.example.adminmobile.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiwayatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiwayatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public RiwayatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RiwayatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RiwayatFragment newInstance(String param1, String param2) {
        RiwayatFragment fragment = new RiwayatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.riwayat, container, false);

        recyclerView = view.findViewById(R.id.viewriwayat);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Booking")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<riwayatmodel> dataList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Convert each document snapshot to your model class
                        riwayatmodel data = document.toObject(riwayatmodel.class);
                        // Add the converted data to your list
                        dataList.add(data);
                    }
                    // Set up RecyclerView after populating the dataList
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    AdapterRiwayat adapter = new AdapterRiwayat(dataList, getContext());
                    recyclerView.setAdapter(adapter);
                })
                .addOnFailureListener(e -> {
                    Log.e("getResdata", "error Fetching data " + e);
                    Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                });
        return view;
    }
}