package com.example.hoangbao.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoangbao.Adapter.RoomAdapter;
import com.example.hoangbao.Model.Room;
import com.example.hoangbao.Model.RoomModel;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomPageActivity extends AppCompatActivity {
//    private RecyclerView ListDataView;
    private RoomAdapter roomAdapter;


    TextView vTitle, vDesc, vPrice, vStartDate, vNights;
    ImageView imageView;
    List<RoomModel> roomModelArrayList;

    Room room;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    ApiBookingHotel apiBookingHotel;
    LinearLayoutManager linearLayoutManager;
    private int year, month, day;
    private int Cyear, Cmonth, Cday;
    private int difference;
    Button pBook;
    List<Room> mRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_page);
        apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);
        initUI();
        initData();
        getRoom();
        initControl();
    }

    private void initControl() {
        vStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
                Toast.makeText(getApplicationContext(), "Enter Start Date", Toast.LENGTH_SHORT).show();
            }
        });
        pBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookRoom();
            }
        });
    }

    private void bookRoom() {
        int maKH = Utils.user_current.getMaKH();
        String startDate = vStartDate.getText().toString().trim();
        String night = vNights.getText().toString().trim();
        String sophong = String.valueOf(room.getSophong());
        compositeDisposable.add(apiBookingHotel.bookRoom(maKH, startDate, night, sophong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                Toast.makeText(getApplicationContext(), "Đăng kí Thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                ));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            Calendar calendar = Calendar.getInstance();
            int yearNow = calendar.get(Calendar.YEAR);
            int monthNow = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(this,
                    myDateListener, yearNow, monthNow, dayOfMonth);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    int currentDate = ((Cyear*12*365)+(Cmonth*30)+Cday);
                    int pickDate = ((arg1*12*365)+(arg2*30)+arg3);
                    difference = pickDate - currentDate;
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        vStartDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    private void initUI() {
        vTitle = findViewById(R.id.proomTitle);
        vDesc = findViewById(R.id.pDesc);
        vPrice = findViewById(R.id.proomPrice);
        imageView = findViewById(R.id.pRoomImage);
        vStartDate = findViewById(R.id.pstartDate);
        vNights = findViewById(R.id.pNights);
        recyclerView = findViewById(R.id.SimilarListView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        pBook = findViewById(R.id.pBook);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        room = (Room) getIntent().getSerializableExtra("chitiet");
        vTitle.setText(room.getTenkhachsan());
        vDesc.setText(room.getDiachi());
        Picasso.with(this).load(room.getImage()).fit().into(imageView);
        vPrice.setText(String.valueOf(room.getGiaphong())+" VNĐ");
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