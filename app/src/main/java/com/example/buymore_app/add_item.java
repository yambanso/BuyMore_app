package com.example.buymore_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static java.lang.Integer.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_item#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_item extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_item() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_item.
     */
    // TODO: Rename and change types and number of parameters
    public static add_item newInstance(String param1, String param2) {
        add_item fragment = new add_item();
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

    ImageView imageup;
    Spinner category;
    TextInputEditText name,quantity,price,descriprition;
    Button cancel,submit;

    Uri filepath;
    Bitmap bitmap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_add_item, container, false);
        imageup = view.findViewById(R.id.itemPicture);
        category = view.findViewById(R.id.category);
        name = view.findViewById(R.id.itemName);
        quantity = view.findViewById(R.id.quantity);
        price = view.findViewById(R.id.price);
        descriprition = view.findViewById(R.id.description);
        cancel = view.findViewById(R.id.cancel);
        submit = view.findViewById(R.id.submit);

        imageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , 2);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName,categ,descrip,Price, qt;
                int qty;
                itemName = name.getText().toString();
                categ = "marlon craft";//category.getSelectedItem().toString();
                descrip = descriprition.getText().toString();
                Price = price.getText().toString();
                qt = quantity.getText().toString();


                if(itemName.isEmpty() && categ.isEmpty() && descrip.isEmpty() && Price.isEmpty() && qt.isEmpty() && filepath == null ){
                    Toast.makeText(getContext(),"Form is incomplete", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        qty = parseInt(qt);
                    }catch (NumberFormatException ex){
                        Toast.makeText(getContext(),"Please make sure that the price is a number not Text", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 2 && resultCode == RESULT_OK && data != null){
                filepath = data.getData();
                imageup.setImageURI(filepath);

            }


    }
}