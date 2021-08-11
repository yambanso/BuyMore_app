package com.example.buymore_app.home_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Items;
import com.example.buymore_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link laptops#newInstance} factory method to
 * create an instance of this fragment.
 */
public class laptops extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public laptops() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment laptops.
     */
    // TODO: Rename and change types and number of parameters
    public static laptops newInstance(String param1, String param2) {
        laptops fragment = new laptops();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_items_recyclerview, container, false);
        recyclerView = view .findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Items[] fashionItems = new Items[]{
                new Items(0,  36, R.drawable.acer_nitro_5, "Acer Nitro 5", "Laptop", "k700,000"),
                new Items(1,  7, R.drawable.dell_xps_13, "Dell Xps 13", "Laptop", "k550,000"),
                new Items(2,  16, R.drawable.asus_vivobook, "Asus Vivo Book", "Laptop", "k400,000"),
                new Items(3,  240, R.drawable.hp_g6, "Hp G6", "Laptop", "k370,000"),
                new Items(4,  100, R.drawable.macbook_pro, "Macbook Pro", "Laptop", "k2,000 000"),
                new Items(5,  78, R.drawable.razer_blade, "Razer Blade pro", "Laptop", "k1,700 000"),
        };
        recyclerView.setAdapter(new ItemsAdapter(fashionItems, getContext()));
        return view;}
}