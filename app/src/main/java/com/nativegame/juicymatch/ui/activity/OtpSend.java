package com.nativegame.juicymatch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityOtpSendBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.MessageModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpSend extends AppCompatActivity {

    ActivityOtpSendBinding binding;
    String otp,mobile,name,c,p,age,country,gender,language1,language2,language3,type;
    PinEntryEditText pinEntry;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpSendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User(getApplicationContext());
        otp = getIntent().getStringExtra("otp");
        mobile = getIntent().getStringExtra("mobile");
        name = getIntent().getStringExtra("name");
        c = getIntent().getStringExtra("c");
        p = getIntent().getStringExtra("p");
        age = getIntent().getStringExtra("age");
        country = getIntent().getStringExtra("country");
        type = getIntent().getStringExtra("type");
        gender = getIntent().getStringExtra("gender");
        language1 = getIntent().getStringExtra("language1");
        language2 = getIntent().getStringExtra("language2");
        language3 = getIntent().getStringExtra("language3");


//        Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();

        pinEntry = findViewById(R.id.txt_pin_entry);

        binding.mobilenumber.setText(mobile);
//        pinEntry.setText(otp);

        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pindata = pinEntry.getText().toString();
                if (pindata.isEmpty()) {
                    Toast.makeText(OtpSend.this, "Please Enter Verify Otp Number", Toast.LENGTH_SHORT).show();
                } else {

                    if (otp.equals(pindata)) {

                        if (type.equals("0")){
                            SendToken("1");
                        }
                        if (type.equals("1")){
                            register("1");
                        }

//                        FirebaseMessaging.getInstance().getToken()
//                                .addOnCompleteListener(new OnCompleteListener<String>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<String> task) {
//                                        if (!task.isSuccessful()) {
//                                            if (type.equals("0")){
//                                                SendToken("1");
//                                            }
//                                            if (type.equals("1")){
//                                                register("1");
//                                            }
//                                        }else {
//                                            String token = task.getResult();
//
//                                            if (type.equals("0")){
//                                                SendToken(token);
//                                            }
//                                            if (type.equals("1")){
//                                                register(token);
//                                            }
//                                        }
//                                        // Get new FCM registration token
//
//
//
//                                    }
//                                });




                    } else {
                        Toast.makeText(OtpSend.this, "Wrong Otp Number", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("0")){
                    startActivity(new Intent(getApplicationContext(),LogIn.class));
                    finish();
                }
                if (type.equals("1")){
                    startActivity(new Intent(getApplicationContext(),SignUp.class));
                    finish();
                }

            }
        });

        binding.recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend();
            }
        });

        countdata();

    }

    private void register(String token) {
        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().signIn(name, mobile, c, p,token,age,country,gender,language1,language2,language3);
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                List<MessageModels> data = response.body();

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {
                    user.setUserphone(mobile);
                    user.setUserid(data.get(0).getU_id());
                    user.setSender_id(data.get(0).getSender_id());

                    user.setUsername(name);
                    user.setUsercity(c);
//                    user.setUseremail(e);
//                    user.setAddress(p);
                    user.setToken(token);

//                    Toast.makeText(getApplicationContext(),data.get(0).getU_id() , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();


                }
                if (Integer.parseInt(data.get(0).getMessage()) == 0) {
                    Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();
                }

                if (Integer.parseInt(data.get(0).getMessage()) == 2) {

                    Toast.makeText(getApplicationContext(), "Number Already Registered.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LogIn.class));
                    finish();
                }


            }

            @Override
            public void onFailure(Call<List<MessageModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void SendToken(String token) {

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().otpToken(user.getUserid(),token);
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();
                if (Integer.parseInt(data.get(0).getMessage()) == 1) {
                    user.setUserphone(mobile);
                    startActivity(new Intent(OtpSend.this, MainActivity.class));
                    Toast.makeText(getApplicationContext(),"Login Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (Integer.parseInt(data.get(0).getMessage()) == 0) {
                    Toast.makeText(getApplicationContext(), "Number Not Registered.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LogIn.class));
                    finish();
                }


            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void resend() {

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().otpSend(name,mobile,otp);
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {

                if (response.isSuccessful()){
                    Toast.makeText(OtpSend.this, "Recent Otp Success", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "Failed Send OTP!.", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void countdata() {
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                binding.second.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                binding.second.setText("Completed");
            }
        }.start();

    }

}