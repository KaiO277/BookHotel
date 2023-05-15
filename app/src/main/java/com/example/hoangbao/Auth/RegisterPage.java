package com.example.hoangbao.Auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hoangbao.Model.UserModel;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPage extends AppCompatActivity {
    TextInputEditText edtName, edtEmail, edtPass;
    MaterialTextView btnLogin;
    MaterialButton btnSignUp;
    ApiBookingHotel apiBookingHotel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        initUI();
        initControl();
    }

    private void initUI() {
        edtName = findViewById(R.id.text_name);
        edtEmail = findViewById(R.id.text_email);
        edtPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        btnSignUp = findViewById(R.id.login_button);
    }

    private void initControl() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = edtName.getText().toString();
                Log.d("error", str_name);
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (TextUtils.isEmpty(str_name)){
                    String error = "You haven't entered your name.";
                    TBL(error);
                }else if (TextUtils.isEmpty(email)){
                    String error = "You haven't entered your email.";
                    TBL(error);
                }else if (!isValidEmail(email)) {
                    // Nếu email không hợp lệ, thông báo lỗi.
                    String error =  "You did not enter an email.";
                    TBL(error);
                }else if (TextUtils.isEmpty(pass)){
                    String error = "You haven't entered your password.";
                    TBL(error);
                }else{
                    apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);
                    compositeDisposable.add(apiBookingHotel.dangKi(str_name, email, pass)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    UserModel->{
                                        if (UserModel.isSuccess()){
                                            Toast.makeText(RegisterPage.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                                            startActivity(intent);
                                            finish();
                                        }else {
                                            Toast.makeText(getApplicationContext(), UserModel.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            ));
                }
            }
        });
    }



    private void TBL(String error){
        String title = "Thông báo";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(error)
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isValidEmail(String email) {
        // Sử dụng regex để kiểm tra định dạng email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
}