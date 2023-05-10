package com.example.hoangbao.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.hoangbao.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class RegisterPage extends AppCompatActivity {
    TextInputEditText edtName, edtEmail, edtPass;
    MaterialTextView btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        initUI();
    }

    private void initUI() {
        edtName = findViewById(R.id.text_name);
        edtEmail = findViewById(R.id.text_name);
        edtPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.login_button);
    }
}