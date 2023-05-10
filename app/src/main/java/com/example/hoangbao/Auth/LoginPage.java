package com.example.hoangbao.Auth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoangbao.Activity.HomePageActivity;
import com.example.hoangbao.Activity.MainActivity;
import com.example.hoangbao.R;
import com.example.hoangbao.retrofit.ApiBookingHotel;
import com.example.hoangbao.retrofit.RetrofitClient;
import com.example.hoangbao.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPage extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText email,password;
    ApiBookingHotel apiBookingHotel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int set;
    boolean isLogin= false;
    MaterialTextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        initUI();
      initControl();
    }

    private void initControl() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString().trim();
                String str_pass = password.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    String error = "Bạn chưa nhập Email";
                    TBL(error);
                }else if(!isValidEmail(str_email)){
                    String error = "Bạn chưa nhập Email";
                    TBL(error);
                }else if(TextUtils.isEmpty(str_pass)){
                    String error = "Bạn chưa nhập Pass";
                    TBL(error);
                }else{
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);

                    dangNhap(str_email, str_pass);
                }
            }
        });
    }


    private boolean isValidEmail(String email) {
        // Sử dụng regex để kiểm tra định dạng email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void initUI() {
        Paper.init(this);
        apiBookingHotel = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBookingHotel.class);

        btnLogin = findViewById(R.id.login_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.createAccount);



        //read data
        if (Paper.book().read("email") != null && Paper.book().read("pass") != null){
            email.setText(Paper.book().read("email"));
            password.setText(Paper.book().read("pass"));
            if(Paper.book().read("isLogin")!= null){
                boolean flag = Paper.book().read("isLogin");
                if(flag){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dangNhap(Paper.book().read("email"),Paper.book().read("pass"));
                        }
                    },1000);
                }
            }
        }
    }

    private void dangNhap(String email, String pass) {
        compositeDisposable.add(apiBookingHotel.dangNhap(email, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                set = getIntent().getIntExtra("Set", 1);
                                if (set == 1){
                                    isLogin = false;
                                }else {
                                    isLogin = true;
                                }
//                                isLogin = true;
                                Paper.book().write("isLogin", isLogin);
                                Utils.user_current = userModel.getResult().get(0);
                                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Incorrect username or password. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
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

}