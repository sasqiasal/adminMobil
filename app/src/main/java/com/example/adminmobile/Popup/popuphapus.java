package com.example.adminmobile.Popup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.adminmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link popuphapus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class popuphapus extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public popuphapus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment popuphapus.
     */
    // TODO: Rename and change types and number of parameters
    public static popuphapus newInstance(String param1, String param2) {
        popuphapus fragment = new popuphapus();
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
Button btnHapus,btnBatal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popuphapus, container, false);
        btnHapus = view.findViewById(R.id.button);
        btnBatal = view.findViewById(R.id.button2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnHapus.setOnClickListener(v -> {
            db.collection("Data_Mobil").document(mParam1).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getActivity(), "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            });
        });

        btnBatal.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }
}