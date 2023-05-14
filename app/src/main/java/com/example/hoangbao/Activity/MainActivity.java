package com.example.hoangbao.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hoangbao.Auth.LoginPage;
import com.example.hoangbao.Auth.RegisterPage;
import com.example.hoangbao.R;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    MaterialTextView btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
            }
        });
        btnCreate = findViewById(R.id.register);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}