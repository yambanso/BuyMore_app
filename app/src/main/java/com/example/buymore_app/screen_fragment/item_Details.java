package com.example.buymore_app.screen_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymore_app.Adapter.Utility;
import com.example.buymore_app.Items;
import com.example.buymore_app.R;

import org.jetbrains.annotations.TestOnly;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link item_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class item_Details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public item_Details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment item_Details.
     */
    // TODO: Rename and change types and number of parameters
    public static item_Details newInstance(String param1, String param2) {
        item_Details fragment = new item_Details();
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
    ImageView itemImage;
    TextView itemName, price, quantity, category, details;
    Button enq, add_Cart;
    ImageView addFavourites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item__details, container, false);
        itemImage = view.findViewById(R.id.itemPicture);
        itemName = view.findViewById(R.id.itemName);
        price = view.findViewById(R.id.price);
        category = view.findViewById(R.id.category);
        quantity = view.findViewById(R.id.quantity);
        details = view.findViewById(R.id.item_description);
        add_Cart = view.findViewById(R.id.addCart);
        enq = view.findViewById(R.id.enquire);
        addFavourites = view.findViewById(R.id.add_favourites);
        //getting our item from bundle
        Bundle bundle = getArguments();
        String item = bundle.getString("Item");

        //converting the string item back to an item object
        Items itemm = Utility.getGsonParser().fromJson(item, Items.class);


        itemImage.setImageResource(itemm.getImageUrl());
        itemName.setText(itemm.getItemName());
        price.setText("Price : "+itemm.getPrice());
        category.setText("Categoy : "+ itemm.getCategory());
        quantity.setText("In Stock : "+ itemm.getQuantity());
        details.setText("Limit of Liability/Disclaimer of Warranty: The publisher and the author make no representations or warranties with respect to the accuracy or completeness of the contents of this work and specifically disclaim\n" +
                "all warranties, including without limitation warranties of fitness for a particular purpose. No warranty\n" +
                "may be created or extended by sales or promotional materials.");

        //action listeners for buttons
        add_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),itemm.getItemName()+" Added to cart succesifuly", Toast.LENGTH_SHORT).show();
            }
        });

        enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Sending an enquiry", Toast.LENGTH_SHORT).show();
            }
        });
        final Boolean[] fav = {false};
        addFavourites.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(fav[0] == false) {
                    fav[0] = true;
                    addFavourites.setImageResource(R.drawable.ic_favorite);
                    Toast.makeText(getContext(),itemm.getItemName()+" added to favourites", Toast.LENGTH_SHORT).show();
                }else {
                    fav[0] = false;
                    addFavourites.setImageResource(R.drawable.ic_baseline_favorite_red);
                    Toast.makeText(getContext(),itemm.getItemName()+" removed from favourites", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}