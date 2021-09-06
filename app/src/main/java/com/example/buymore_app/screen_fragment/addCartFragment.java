package com.example.buymore_app.screen_fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Adapter.cartAdapter;
import com.example.buymore_app.Items;
import com.example.buymore_app.R;
import com.example.buymore_app.backend.favouritesDatabase;
import com.example.buymore_app.backend.itemOrderEntity;
import com.example.buymore_app.backend.nortification;
import com.example.buymore_app.backend.order;
import com.example.buymore_app.backend.orderItemDao;
import com.example.buymore_app.home_fragments.homefrag;
import com.example.buymore_app.home_fragments.nortifications;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

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
    private int total;
    private String contact;


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
        Button chekout;

        View view = inflater.inflate(R.layout.fragment_add_cart, container, false);
        price = view.findViewById(R.id.totalPrice);
        chekout = view.findViewById(R.id.checkout);
        recyclerView = view.findViewById(R.id.cartItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String desc = getResources().getString(R.string.description);
        List<Items> ItemsList = new ArrayList<Items>();

        List<itemOrderEntity>list = new ArrayList<>();


        cartAdapter adapter = new cartAdapter(ItemsList, getContext(),price);

        favouritesDatabase db = favouritesDatabase.getDb(getContext());
        orderItemDao dao = db.order();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uId = user.getUid();

        //getting data from room
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<itemOrderEntity>listt = dao.getOrderItems(uId);
                for(int i = 0 ; i < listt.size();i++){
                    itemOrderEntity et = listt.get(i);
                    list.add(et);
                }
            }
        }).start();

        DatabaseReference ref;

        ref= FirebaseDatabase.getInstance().getReference("Item");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Items item = dataSnapshot.getValue(Items.class);
                        for(int i = 0 ;i < list.size();i++){
                            itemOrderEntity t = list.get(i);
                            if(t.getItemName().equals(item.getItemName()) && t.getItemOwner().equals(item.getOwnerId())){
                                ItemsList.add(item);
                                total += item.getPrice();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        price.setText("Total : k "+ total);
                                        Toast.makeText(getContext(),"Retreiving Items Please wait Paitently",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                        adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        chekout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                reference.child("Users").child(uId).child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){

                            contact = (String) task.getResult().getValue();



                        }
                    }
                });
                order ord = new order(uId, contact,ItemsList);
                FirebaseDatabase.getInstance().getReference("Orders").setValue(ord).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        for(int i = 0; i < ItemsList.size();i++){
                            Items it = ItemsList.get(i);
                            nortification not = new nortification(it.getOwnerId(),it.getItemName(), contact,it.getPrice());
                            FirebaseDatabase.getInstance().getReference("Nortifications").setValue(not).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    favouritesDatabase db = favouritesDatabase.getDb(getContext());
                                    orderItemDao dao = db.order();
                                    List<itemOrderEntity> del = new ArrayList<>();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            for(int i = 0; i < ItemsList.size();i++){
                                                itemOrderEntity et = dao.checkInCart(ItemsList.get(i).getOwnerId()+ItemsList.get(i).getItemName());
                                               del.add(et);

                                            }
                                            int i = 0;
                                            while(i < del.size()){
                                                itemOrderEntity it = del.get(i);
                                                if(it != null){
                                                    dao.itemDelete(del.get(i));
                                                }
                                                i++;
                                            }
                                        }
                                    }).start();

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(),"Order sent Succesifuly",Toast.LENGTH_SHORT).show();
                                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                                            activity.getSupportFragmentManager()
                                                    .beginTransaction()
                                                    .replace(R.id.navHostFragment, new homefrag())
                                                    .addToBackStack(null)
                                                    .commit();

                                        }
                                    });
                                }
                            });

                        }
                        }
                });
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}