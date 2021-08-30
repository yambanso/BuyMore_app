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

import static com.example.buymore_app.R.drawable.huawei_mate_20;
import static com.example.buymore_app.R.drawable.lg_v60;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link phones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class phones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public phones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment phones.
     */
    // TODO: Rename and change types and number of parameters
    public static phones newInstance(String param1, String param2) {
        phones fragment = new phones();
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
        View view = inflater.inflate(R.layout.fragment_items_recyclerview, container, false);
        recyclerView =view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String desc = getResources().getString(R.string.description);
        Items[] ItemsList = new Items[]{
                new Items (0+"",  10, huawei_mate_20, "Huawei mate 20 pro", "phones", 400000, desc),
                new Items(1+"",  87, lg_v60, "LG V60 thinkQ", "Phones", 480000, desc),
                new Items(2+"",  50, R.drawable.samsung_s21, "Samasung s21", "Phones", 800000,desc),
                new Items(3+"",  13, R.drawable.nokia, "Nokia 8.3 5G", "Phones", 500000, desc),
                new Items(4+"",  87, R.drawable.one_plus_8pro, "One plus pro", "Phones", 350000,desc),
                new Items(5+"",  12, R.drawable.iphone_12, "Iphone 12 normal", "Phones", 1200000,desc),
               };

        recyclerView.setAdapter(new ItemsAdapter(ItemsList, getContext()));
        return view;}
}