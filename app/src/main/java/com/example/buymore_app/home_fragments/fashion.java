package com.example.buymore_app.home_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Items;
import com.example.buymore_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

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
            String desc = getResources().getString(R.string.description);
        ArrayList<Items> fashionItems = new ArrayList<>();
        ItemsAdapter adapter = new ItemsAdapter(fashionItems, getContext());
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Item");
        recyclerView.setAdapter(adapter);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Items item = dataSnapshot.getValue(Items.class);
                    if(item.getCategory().equals( "Fashion"))fashionItems.add(item);
                }
                Collections.reverse(fashionItems);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return view;
    }
}