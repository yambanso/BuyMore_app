package com.example.buymore_app.home_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Items;
import com.example.buymore_app.MainActivity;
import com.example.buymore_app.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.buymore_app.R.drawable.acer_nitro_5;
import static com.example.buymore_app.R.drawable.buren_83016m;
import static com.example.buymore_app.R.drawable.dell_xps_13;
import static com.example.buymore_app.R.drawable.huawei_mate_20;
import static com.example.buymore_app.R.drawable.lg_v60;
import static com.example.buymore_app.R.drawable.nike_air_270;
import static com.example.buymore_app.R.drawable.polo_golf_shirt;

public class homefrag extends Fragment {
    RecyclerView recyclerView;
    public homefrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homefrag, container, false);
        recyclerView =view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Items[] ItemsList = new Items[]{
                new Items (0,  10, huawei_mate_20, "Huawei mate 20 pro", "phones", "k400,000"),
                new Items(1,  100, nike_air_270, "Nike air Max 270 Black", "Fashion", "k40,000"),
                new Items(2,  36, acer_nitro_5, "Acer Nitro 5", "Laptop", "k700,000"),
                new Items(3,  7, dell_xps_13, "Dell Xps 13", "Laptop", "k550,000"),
                new Items(4,  100, buren_83016m, "Buren 83016M", "Fashion", "k100,000"),
                new Items(5,  87, lg_v60, "LG V60 thinkQ", "Phones", "k480,000"),
                new Items(6,  100, polo_golf_shirt, "Polo Mens Golf Shirt", "Fashion", "k50,000"),
        };

        recyclerView.setAdapter(new ItemsAdapter(ItemsList, getContext()));
        return view;
    }
}