package com.example.buymore_app.screen_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Adapter.cartAdapter;
import com.example.buymore_app.Items;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addCartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addCartFragment newInstance(String param1, String param2) {
        addCartFragment fragment = new addCartFragment();
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
        RecyclerView recyclerView;
        TextView price;

        View view = inflater.inflate(R.layout.fragment_add_cart, container, false);
        price = view.findViewById(R.id.totalPrice);
        recyclerView = view.findViewById(R.id.cartItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String desc = getResources().getString(R.string.description);
        List<Items> ItemsList = new ArrayList<Items>();
                ItemsList.add(new Items(0+"",  10, huawei_mate_20, "Huawei mate 20 pro", "phones", 400000,desc));
                ItemsList.add(new Items(1+"",  100, nike_air_270, "Nike air Max 270 Black", "Fashion", 40000, desc));
                ItemsList.add(new Items(2+"",  87, lg_v60, "LG V60 thinkQ", "Phones", 480000, desc));
                ItemsList.add(new Items(3+"",  100, polo_golf_shirt, "Polo Mens Golf Shirt", "Fashion", 50000,desc));

                int total = 0;
                for(int i = 0;i <ItemsList.size();i++){
                    total += ItemsList.get(i).getPrice();
                }
        price.setText("Total : k "+ total);
        recyclerView.setAdapter(new cartAdapter(ItemsList, getContext()));
        return view;
    }
}