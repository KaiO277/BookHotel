package com.example.hoangbao.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangbao.Adapter.ShowRoomAdapter;
import com.example.hoangbao.Model.ShowRoom;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RequestedBooking extends AppCompatActivity {

    ImageView menu, profile;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBookingHotel apiBookingHotel;
    LinearLayoutManager linearLayoutManager;
    ShowRoomAdapter roomAdapter;
    TextView title;
    RecyclerView AllListView;
    List<ShowRoom> showRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide (); //This Line hides the action bar
        setContentView(R.layout.activity_view_all);
        apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);
        title = findViewById(R.id.pageTitle);

        menu = findViewById(R.id.onMenu);
        profile= findViewById(R.id.onProfile);
        title.setText("Requested Booking List");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestedBooking.this, UserMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        initControl();

    }

    private void initControl() {
        AllListView = findViewById(R.id.AllListView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        AllListView.setLayoutManager(linearLayoutManager);
        AllListView.setHasFixedSize(true);
        int maKH = Utils.user_current.getMaKH();
        compositeDisposable.add(apiBookingHotel.showRoom(maKH, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        showRoomModel -> {
                            if(showRoomModel.isSuccess()){
                                showRooms = showRoomModel.getResult();
                                roomAdapter = new ShowRoomAdapter(getApplicationContext(), showRooms);
                                AllListView.setAdapter(roomAdapter);
                            }
                        }
                ));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RequestedBooking.this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
