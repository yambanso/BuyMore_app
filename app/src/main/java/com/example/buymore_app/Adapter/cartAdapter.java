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
import com.example.buymore_app.backend.favouritesDatabase;
import com.example.buymore_app.backend.itemOrderEntity;
import com.example.buymore_app.backend.orderItemDao;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder>{
        List<Items> item;
        Context context;
        TextView price;

    public cartAdapter(List<Items> item, Context context,TextView price) {
        this.item = item;
        this.context = context;
        this.price = price;
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
        holder.price.setText("Price is : "+itemm.getPrice());
        holder.itemname.setText(itemm.getItemName());
        Picasso.get().load(itemm.getUri()).into(holder.itemImage);

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
                    Items itemm = item.get(getAdapterPosition());
                    favouritesDatabase db = favouritesDatabase.getDb(v.getContext());
                    orderItemDao dao = db.order();
                    int total = 0;
                    for(int i =0; i < item.size(); i++) total += item.get(i).getPrice();
                    total = total - item.get(getAdapterPosition()).getPrice();
                    price.setText("Total : k "+ total);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            itemOrderEntity  et = dao.checkInCart(itemm.getOwnerId()+itemm.getItemName());
                            dao.itemDelete(et);

                                                    }
                    }).start();

                    Toast.makeText(context,"Item : "+itemm.getItemName()+" removed from cart", Toast.LENGTH_SHORT).show();

                    item.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),item.size());
                }
            });

        }
    }
}
