package com.example.buymore_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buymore_app.Items;
import com.example.buymore_app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder>{
        List<Items> item;
        Context context;

    public cartAdapter(List<Items> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new cartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull cartViewHolder holder, int position) {
        Items itemm = item.get(position);
        holder.price.setText(itemm.getPrice());
        holder.itemname.setText(itemm.getItemName());
        holder.itemImage.setImageResource(itemm.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage,removeItem;
        TextView itemname, price;

        public cartViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            removeItem = itemView.findViewById(R.id.remove_in_cart);
            itemname = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);

            removeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Item : "+item.get(getAdapterPosition()).getItemName()+" removed from cart", Toast.LENGTH_SHORT).show();
                    item.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),item.size());

                }
            });

        }
    }
}
