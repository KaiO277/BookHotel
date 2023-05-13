package com.example.hoangbao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangbao.Interface.ItemClickListener;
import com.example.hoangbao.Model.ShowRoom;
import com.example.hoangbao.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowRoomAdapter extends RecyclerView.Adapter<ShowRoomAdapter.MyViewHolder> {

    Context context;
    List<ShowRoom> array;

    public ShowRoomAdapter(Context context, List<ShowRoom> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_room, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShowRoom showRoom = array.get(position);
        Picasso.with(this.context).load(showRoom.getImage()).fit().into(holder.rcardImage);
        holder.rcardStartDate.setText(showRoom.getStartDate());
        holder.rcardTitle.setText(showRoom.getTenkhachsan());
        holder.rcardStatus.setText("Not approve");
        holder.rcardDays.setText(String.valueOf(showRoom.getNight()));
        holder.rcardTotalPrice.setText(String.valueOf(showRoom.getTotalPaymen()));
        holder.rcardPrice.setText(String.valueOf(showRoom.getGiaphong()));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView rcardImage;
        TextView rcardStatus, rcardTitle,rcardStartDate, rcardDays, rcardPrice, rcardTotalPrice;
        Button rcardCancel;


        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rcardImage = itemView.findViewById(R.id.rcardImage);
            rcardStatus = itemView.findViewById(R.id.rcardStatus);
            rcardTitle = itemView.findViewById(R.id.rcardTitle);
            rcardStartDate = itemView.findViewById(R.id.rcardStartDate);
            rcardDays = itemView.findViewById(R.id.rcardDays);
            rcardPrice = itemView.findViewById(R.id.rcardPrice);
            rcardTotalPrice = itemView.findViewById(R.id.rcardTotalPrice);
            rcardCancel = itemView.findViewById(R.id.rcardCancel);
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
