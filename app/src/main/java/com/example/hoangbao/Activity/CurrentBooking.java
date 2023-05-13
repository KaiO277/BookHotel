package com.example.hoangbao.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangbao.R;

public class CurrentBooking extends AppCompatActivity {
    private RecyclerView ListDataView;
//    private CurrentBookingAdapter Adapter;
    ImageView menu, profile;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        title = findViewById(R.id.pageTitle);

        menu = findViewById(R.id.onMenu);
        profile= findViewById(R.id.onProfile);
        title.setText("Current Booking List");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CurrentBooking.this, UserMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}