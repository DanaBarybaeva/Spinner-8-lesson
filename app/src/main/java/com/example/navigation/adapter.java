package com.example.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kotlin.time.FormatToDecimalsKt;

public class adapter extends RecyclerView.Adapter<adapter.MyTViewHolder> {
    private Context context;
    private List<food> foodList;

    @NonNull
    @Override
    public MyTViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        return new MyTViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTViewHolder holder, int position) {
        food item = foodList.get(position);
        holder.photo.setImageResource(item.getPhoto());
        holder.photo.setImageResource(item.getPhoto());
        holder.title.setText(item.getTitle());
        holder.desc.setText(item.getDesc());
        holder.price.setText(""+item.getPrice());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MyTViewHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView title,desc,price;

        public MyTViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.photo1);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            price = itemView.findViewById(R.id.price);

        }
    }

    public adapter(Context context, List<food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

}
