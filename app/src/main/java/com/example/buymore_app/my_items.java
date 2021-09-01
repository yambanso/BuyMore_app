package com.example.buymore_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.buymore_app.R.drawable.acer_nitro_5;
import static com.example.buymore_app.R.drawable.buren_83016m;
import static com.example.buymore_app.R.drawable.dell_xps_13;
import static com.example.buymore_app.R.drawable.huawei_mate_20;
import static com.example.buymore_app.R.drawable.lg_v60;
import static com.example.buymore_app.R.drawable.nike_air_270;
import static com.example.buymore_app.R.drawable.polo_golf_shirt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link my_items#newInstance} factory method to
 * create an instance of this fragment.
 */
public class my_items extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public my_items() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment my_items.
     */
    // TODO: Rename and change types and number of parameters
    public static my_items newInstance(String param1, String param2) {
        my_items fragment = new my_items();
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
    RecyclerView recyclerView;
    String id;
    DatabaseReference db;
    private ValueEventListener valueEventListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_items, container, false);

        recyclerView =view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Items> ItemsList = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("Item");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();

        ItemsAdapter  adapter = new ItemsAdapter(ItemsList, getContext());
        recyclerView.setAdapter(adapter);

        valueEventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Items item = dataSnapshot.getValue(Items.class);
                    if(item.getOwnerId().equals(id)) ItemsList.add(item);
                }
                Collections.reverse(ItemsList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        db.removeEventListener(valueEventListener);
    }
}