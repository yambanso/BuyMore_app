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

import static com.example.buymore_app.R.drawable.buren_83016m;
import static com.example.buymore_app.R.drawable.nike_air_270;
import static com.example.buymore_app.R.drawable.polo_golf_shirt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fashion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fashion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public fashion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fashion.
     */
    // TODO: Rename and change types and number of parameters
    public static fashion newInstance(String param1, String param2) {
        fashion fragment = new fashion();
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
                new Items(0,  100, buren_83016m, "Buren 83016M", "Fashion", "k100,000"),
                new Items(1,  120, polo_golf_shirt, "Polo Mens Golf Shirt", "Fashion", "k50,000"),
                new Items(2,  16, nike_air_270, "Nike air Max 270 Black", "Fashion", "k40,000"),
                new Items(3,  2450, R.drawable.new_balance_tshirt, "New Balance T shirt", "Fashion", "k17,000"),
                new Items(4,  100, R.drawable.zara_mens_pants, "Zara mens Pants", "Fashion", "k20,000"),
                new Items(5,  100, R.drawable.new_balance_sweatshirt, "Adidas sweat Shirt", "Fashion", "k20,000"),
        };
        recyclerView.setAdapter(new ItemsAdapter(fashionItems, getContext()));
        return view;
    }
}