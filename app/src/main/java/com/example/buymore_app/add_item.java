package com.example.buymore_app;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buymore_app.home_fragments.homefrag;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

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
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Item");
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

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
    ProgressBar prg;
    String id;
    Spinner category;
    TextInputEditText name,quantity,price,descriprition;
    Button cancel,submit;

    Uri filepath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View  view = inflater.inflate(R.layout.fragment_add_item, container, false);
        imageup = view.findViewById(R.id.itemPicture);
        category = view.findViewById(R.id.category);
        name = view.findViewById(R.id.itemName);
        prg = view.findViewById(R.id.progressBar2);
        quantity = view.findViewById(R.id.quantity);
        price = view.findViewById(R.id.price);
        descriprition = view.findViewById(R.id.description);
        cancel = view.findViewById(R.id.cancel);
        submit = view.findViewById(R.id.submit);
        prg.setVisibility(View.INVISIBLE);
        //populating the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.category_array,android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

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
                prg.setVisibility(View.VISIBLE);
                String itemName,categ,descrip,Price, qt;
                int qty;
                itemName = name.getText().toString();
                categ = category.getSelectedItem().toString();
                descrip = descriprition.getText().toString();
                Price = price.getText().toString();
                qt = quantity.getText().toString();


                if(itemName.isEmpty()){
                    prg.setVisibility(View.INVISIBLE);
                    name.setError("Item name is missing");
                    name.requestFocus();
                }else if(descrip.isEmpty()){
                    prg.setVisibility(View.INVISIBLE);
                    descriprition.setError("Item description is missing");
                    descriprition.requestFocus();
                }else if(Price.isEmpty()){
                    prg.setVisibility(View.INVISIBLE);
                    price.setError("Item price is missing");
                    price.requestFocus();
                }else if(qt.isEmpty()){
                    prg.setVisibility(View.INVISIBLE);
                    quantity.setError("item Quantity is missing");
                    quantity.requestFocus();
                } else if( filepath == null){
                    prg.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"Please select Item image", Toast.LENGTH_LONG).show();
                }else{
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    id = user.getUid();

                    uploadItem(filepath,id, Integer.parseInt(qt),itemName, categ, Integer.parseInt(Price),descrip);     }

            }
        });

        return view;
    }

    private void uploadItem(Uri uri, String ownerID,int qty, String name, String catego,int price, String descript) {

            StorageReference fileref =  storageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
            fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Items item = new Items(ownerID,uri.toString(),qty,name, catego, price,descript);
                                String itemID  = dbRef.push().getKey();
                                dbRef.child(itemID).setValue(item);

                                Toast.makeText(getContext(),"Item upload succesiful "+ itemID,Toast.LENGTH_LONG).show();
                                prg.setVisibility(View.INVISIBLE);
                                                            }
                        });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                        Toast.makeText(getContext(),"Item is being uploaded",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    prg.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"Failled to upload item",Toast.LENGTH_LONG).show();
                }
            });


    }
    private String getFileExtension(Uri uri){
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mm = MimeTypeMap.getSingleton();
        return  mm.getExtensionFromMimeType(cr.getType(uri));
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