package com.example.buymore_app.screen_fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.buymore_app.backend.favouriteItem;
import com.example.buymore_app.backend.favouritesDatabase;
import com.example.buymore_app.backend.itemDao;
import com.example.buymore_app.backend.itemOrderEntity;
import com.example.buymore_app.backend.orderItemDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    private String contact;

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
    Items itemm;

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
        itemm = Utility.getGsonParser().fromJson(item, Items.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Picasso.get().load(itemm.getUri()).into(itemImage);
        itemName.setText(itemm.getItemName());
        price.setText("Price : "+itemm.getPrice());
        category.setText("Categoy : "+ itemm.getCategory());
        quantity.setText("In Stock : "+ itemm.getQuantity());
        details.setText(itemm.getDescription());

        //action listeners for buttons
        add_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouritesDatabase db = favouritesDatabase.getDb(getContext());
                orderItemDao dao = db.order();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        itemOrderEntity et = dao.checkInCart(itemm.getOwnerId()+itemm.getItemName());
                        if(et == null){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uID = user.getUid();


                            itemOrderEntity it = new itemOrderEntity();

                            it.setItemID(itemm.getOwnerId()+itemm.getItemName());
                            it.setOrderOwner(uID);
                            it.setItemPrice(itemm.getPrice());
                            it.setItemName(itemm.getItemName());
                            it.setItemOwner(itemm.getOwnerId());

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.child("Users").child(uID).child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                                    if(task.isSuccessful()){
                                        String ownerContact;
                                        ownerContact = (String) task.getResult().getValue();
                                        it.setOrderOwnerContact(ownerContact);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                dao.  orderItemInsert(it);
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getContext(),"Item : "+itemm.getItemName()+"added to cart sucessifully",Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        }).start();

                                    }else{
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getContext(),"Failled to add item to cart",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }else{
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    dao.itemDelete(et);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(),"Item : "+et.getItemName()+"removed from cart",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }).start();
                        }
                    }
                }).start();
            }
        });

        enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Users").child(itemm.getOwnerId()).child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            contact = (String) task.getResult().getValue();
                            contact = contact.replaceFirst("0","265");

                            String message = "I would like to enquire about item : " +itemm.getItemName()+" listed on the buy more app for sale";
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT,message);
                            intent.putExtra("jid", contact + "@s.whatsapp.net");
                            intent.setPackage("com.whatsapp");
                            if(intent.resolveActivity(getActivity().getPackageManager()) == null){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(),"Failled to send enquiry message", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                });

                            }
                            startActivity(intent);

                        }else{    getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"Failled to send enquiry message", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });

                        }
                    }
                });

            }
        });

        addFavourites.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                favouritesDatabase db = favouritesDatabase.getDb(getContext());
                itemDao dao = db.Item();

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        favouriteItem it = dao.checkItem(itemm.getOwnerId() + itemm.getItemName());
                        if (it == null) {

                            favouriteItem itt = new favouriteItem();
                             itt.setItemID(itemm.getOwnerId() + itemm.getItemName());

                            String ownerid = user.getUid();
                            itt.setOwnerId(ownerid);

                            String jsonObject = Utility.getGsonParser().toJson(itemm);
                            itt.setJonObject(jsonObject);
                            dao.itemInsert(itt);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),"Item : "+itemm.getItemName()+" added to favourites",Toast.LENGTH_LONG).show();
                                }
                            });
                            } else {
                            dao.ItemDelete(it);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getContext(),"Item : "+itemm.getItemName()+" has been removed from favourites",Toast.LENGTH_LONG).show();
                                    }
                                });
                        }
                    }
                }).start();
            }
        });

        return view;
    }
}