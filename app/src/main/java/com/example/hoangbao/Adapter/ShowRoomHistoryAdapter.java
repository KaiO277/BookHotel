package com.example.hoangbao.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangbao.Activity.RequestedBooking;
import com.example.hoangbao.Activity.UserMenu;
import com.example.hoangbao.Interface.ItemClickListener;
import com.example.hoangbao.Model.ShowRoom;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShowRoomHistoryAdapter extends RecyclerView.Adapter<ShowRoomHistoryAdapter.MyViewHolder> {

    Context context;
    List<ShowRoom> array;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBookingHotel apiBookingHotel;

    public ShowRoomHistoryAdapter(Context context, List<ShowRoom> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ShowRoomHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_room_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowRoomHistoryAdapter.MyViewHolder holder, int position) {
        ShowRoom showRoom = array.get(position);
        Picasso.with(this.context).load(showRoom.getImage()).fit().into(holder.rcardImage);
        holder.rcardStartDate.setText(showRoom.getStartDate());
        holder.rcardTitle.setText(showRoom.getTenkhachsan());
        holder.rcardStatus.setText("Approve");
        holder.rcardDays.setText(String.valueOf(showRoom.getNight()));
        holder.rcardTotalPrice.setText(String.valueOf(showRoom.getTotalPaymen()));
        holder.rcardPrice.setText(String.valueOf(showRoom.getGiaphong()));
        apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);
        holder.rcardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compositeDisposable.add(apiBookingHotel.deleteBooking(showRoom.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                showRoomModel -> {
                                    if (showRoomModel.isSuccess()){
                                        Toast.makeText(context, "Delete item done", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
//                                        Intent intent = new Intent(context, UserMenu.class);
//                                        context.startActivity(intent);
                                    }else {
                                        Toast.makeText(context, showRoomModel.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                },
                                throwable -> {
                                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                        ));
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView rcardImage;
        TextView rcardStatus, rcardTitle,rcardStartDate, rcardDays, rcardPrice, rcardTotalPrice;
        Button rcardDelete;


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
            rcardDelete = itemView.findViewById(R.id.rcardDelete);
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
