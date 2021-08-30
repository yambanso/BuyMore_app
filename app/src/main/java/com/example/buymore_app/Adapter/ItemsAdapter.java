package com.example.buymore_app.Adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buymore_app.Items;
import com.example.buymore_app.R;
import com.example.buymore_app.screen_fragment.item_Details;

import org.jetbrains.annotations.NotNull;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.itemsViewHolder> {



    Items[] ItemsList;
    Context context;

    public ItemsAdapter(Items [] ItemsList, Context context){
        this.ItemsList = ItemsList;
        this.context = context;

    }

    @NonNull
    @NotNull
    @Override
    public itemsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list,parent,false);
        return new itemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull itemsViewHolder holder, int position) {
        Items item = ItemsList[position];
        holder.itemName.setText(item.getItemName());
        holder.price.setText("k "+item.getPrice());
        holder.image.setImageResource(item.getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating a bundle object to transfer data to item details fragment
                Bundle builder = new Bundle();
                String itemToJson = Utility.getGsonParser().toJson(item); // converting item object to String
                builder.putString("Item",itemToJson); //passing item string to our bundle object
                item_Details itemdetails =new item_Details(); // creating new Item Details object
                itemdetails.setArguments(builder); // passing our bundle to item details fragment

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.navHostFragment, itemdetails)
                            .addToBackStack(null)
                            .commit();



            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemsList.length;
    }


    public static class itemsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView itemName;
        TextView price;

        public itemsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.textItemName);
            price = itemView.findViewById(R.id.textItemPrice);
        }
    }
}
