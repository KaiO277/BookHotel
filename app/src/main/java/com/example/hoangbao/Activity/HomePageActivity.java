package com.example.hoangbao.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hoangbao.Adapter.RoomAdapter;
import com.example.hoangbao.Model.Room;
import com.example.hoangbao.Model.RoomModel;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<Room> mList;
    ApiBookingHotel apiBookingHotel;
    RoomAdapter roomAdapter;
    List<Room> mRoom;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);
        initUI();
        getRoom();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.ListView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(HomePageActivity.this, UserMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    private void getRoom() {
       compositeDisposable.add(apiBookingHotel.getRoom()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       roomModel -> {
                           if(roomModel.isSuccess()){
                               mRoom = roomModel.getResult();
                               roomAdapter = new RoomAdapter(getApplicationContext(), mRoom);
                               recyclerView.setAdapter(roomAdapter);
                           }
                       }
               ));
    }

}