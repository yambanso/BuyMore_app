package com.example.buymore_app.home_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Items;
import com.example.buymore_app.MainActivity;
import com.example.buymore_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
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

        DatabaseReference db;
        private  ValueEventListener valueEventListener;
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
        String desc = getResources().getString(R.string.description);

        db = FirebaseDatabase.getInstance().getReference("Item");
        ArrayList<Items> list = new ArrayList<>();
        ItemsAdapter adapter = new ItemsAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
         valueEventListener = db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                     Items item = dataSnapshot.getValue(Items.class);
                     list.add(item);
                }
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return view;


    }


}