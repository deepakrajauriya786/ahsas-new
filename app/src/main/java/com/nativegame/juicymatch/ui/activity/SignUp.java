package com.nativegame.juicymatch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nativegame.juicymatch.R;

import java.util.Random;

public class SignUp extends AppCompatActivity {

    TextView login;
    ImageView selectsity;
    TextInputEditText city, referalEdt;
    TextView txtlogin;
    TextInputEditText mobile, password;
    TextInputEditText name;
    RecyclerView recyclerView;
    KProgressHUD hud;
    String n, c, m, p, e, r;
    String MobilePattern = "[0-9]{10}";
    String otprandom;
    //FirebaseAut auth;
    //String email1 = email.getText().toString().trim();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        login = findViewById(R.id.login);
        name = findViewById(R.id.namefist);
        city = findViewById(R.id.selectcity);
        mobile = findViewById(R.id.phone);
        selectsity = findViewById(R.id.select_gender);
        password = findViewById(R.id.password1);
        txtlogin = findViewById(R.id.txt_login);
        referalEdt = findViewById(R.id.referalEdt);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                n = name.getText().toString();
                c = city.getText().toString();
                m = mobile.getText().toString();
                p = password.getText().toString();
                r = referalEdt.getText().toString();

                if (n.isEmpty()) {
                    name.setError("Enter the name");
                    name.requestFocus();
                    return;
                }


                else if (!m.matches(MobilePattern)) {
                    // Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    mobile.setError("Please enter valid 10 digit phone number");
                    return;

                } else if (p.length() < 6) {
                    password.setError("pls enter the password");
                    password.requestFocus();
                    return;
                } else {

                    random();
                    otpSend(n,m,otprandom);

                }

            }
        });


    }
    public void random(){
        Random r=new Random();
        int otp   =(int) (Math.random()*9000)+1000;
//        int otp = r.nextInt(10000); // no. of zeros depends on the OTP digit
        otprandom = String.valueOf(otp);
    }

    private void otpSend(String name,String mobile,String otprandom) {
//        Log.d("hgvgvjh", "onClick: 11");
//        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().otpSend(name,mobile,otprandom);
//        call.enqueue(new Callback<List<LoginModels>>() {
//            @Override
//            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {

//                if (response.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "OTP Send your number.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OtpSend.class);
                    intent.putExtra("name", n);
                    intent.putExtra("mobile", m);
                    intent.putExtra("otp", otprandom);
                    intent.putExtra("c", c);
                    intent.putExtra("p", p);
                    intent.putExtra("e", e);
                    intent.putExtra("r", r);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                    finish();

//                }else {
//                    Toast.makeText(getApplicationContext(), "Failed Send OTP!.", Toast.LENGTH_SHORT).show();
//                }



//            }
//
//            @Override
//            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
//
//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }



}