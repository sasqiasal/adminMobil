package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.Adapter.AdapterRiwayatBokingAdmin;
import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RiwayatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RiwayatBokingAdmin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public RiwayatBokingAdmin() {
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
    public static RiwayatBokingAdmin newInstance(String param1, String param2) {
        RiwayatBokingAdmin fragment = new RiwayatBokingAdmin();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notifikasi, container, false);

        recyclerView = view.findViewById(R.id.viewriwayat);
        String firebaseUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseFirestore.getInstance().collection("Boking_Admin").whereEqualTo("UID",firebaseUID);

        FirestoreRecyclerOptions<RiwayatBokingAdminModel> option = new FirestoreRecyclerOptions.Builder<RiwayatBokingAdminModel>()
                .setQuery(query, RiwayatBokingAdminModel.class)
                .build();

        AdapterRiwayatBokingAdmin AdapterRiwayat = new AdapterRiwayatBokingAdmin(option, getContext());
        recyclerView.setAdapter(AdapterRiwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(container != null ? container.getContext() : null, LinearLayoutManager.VERTICAL, false));;
       AdapterRiwayat.startListening();

        return view;


    }
}