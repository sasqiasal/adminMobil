package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link akunprofile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class akunprofile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView satu, dua, tiga, empat;

    public akunprofile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment akunprofile.
     */
    // TODO: Rename and change types and number of parameters
    public static akunprofile newInstance(String param1, String param2) {
        akunprofile fragment = new akunprofile();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_akunprofile, container, false);
        // Inflate the layout for this fragment

        satu = view.findViewById(R.id.btndataakun);
        dua = view.findViewById(R.id.btntentangmitra);
        tiga = view.findViewById(R.id.btnpusatbantuan);
        empat = view.findViewById(R.id.btnsyaratdnaketentuan);

        satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), dataprofileActivity.class);
                startActivity(Intent);

            }
        });

        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), ProfileMitraActivity.class);
                startActivity(Intent);
            }
        });

        tiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), pusatbantuanActivity.class);
                startActivity(Intent);

            }
        });

        empat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), syaratketentuanActivity.class);
                startActivity(Intent);

            }
        });

        return view;
    }
}