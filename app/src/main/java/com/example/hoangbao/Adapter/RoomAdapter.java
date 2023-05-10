package com.example.hoangbao.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangbao.Activity.RoomPageActivity;
import com.example.hoangbao.Interface.ItemClickListener;
import com.example.hoangbao.Model.Room;
import com.example.hoangbao.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
     Context context;
    List<Room> arrayList ;

    public RoomAdapter(Context context, List<Room> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Room room = arrayList.get(position);

        holder.edTitle.setText(room.getTenkhachsan());
        holder.edPrice.setText(String.valueOf(room.getGiaphong())+" VNĐ");
        //set the image
        Picasso.with(this.context).load(room.getImage()).fit().into(holder.imageView);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent(context, RoomPageActivity.class);
                    intent.putExtra("chitiet", room);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView edTitle, edPrice;
        private ImageView imageView;
        private CardView cardView;
        private ItemClickListener itemClickListener;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edTitle = itemView.findViewById(R.id.roomTitle);
            edPrice = itemView.findViewById(R.id.roomPrice);
            cardView = itemView.findViewById(R.id.roomCardView);
            imageView = itemView.findViewById(R.id.roomImage);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
