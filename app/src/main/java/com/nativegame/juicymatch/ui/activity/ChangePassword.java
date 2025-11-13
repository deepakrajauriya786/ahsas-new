package com.nativegame.juicymatch.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.nativegame.juicymatch.databinding.ActivityChangePasswordBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    KProgressHUD hud;
    String otp="",mobile;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User(this);


        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pindata = binding.otp.getText().toString();
                if (pindata.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Please Enter Verify Otp Number", Toast.LENGTH_SHORT).show();
                } else {

                    if (otp.equals(pindata)) {

                     loginPassword();


                    } else {
                        Toast.makeText(ChangePassword.this, "Wrong Otp Number", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend();
            }
        });




    }
    public void random() {
        Random r = new Random();
        int otpf = (int) (Math.random() * 9000) + 1000;
//        int otp = r.nextInt(10000); // no. of zeros depends on the OTP digit
        otp = String.valueOf(otpf);
    }
    private void resend() {

        random();

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().otpSend(user.getUsername(),binding.mobile.getText().toString(),otp);
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {

                if (response.isSuccessful()){
                    Toast.makeText(ChangePassword.this, "Sent Otp Success", Toast.LENGTH_SHORT).show();
                    countdata();
                   mobile= binding.mobile.getText().toString();
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

    private void loginPassword() {

        hud = KProgressHUD.create(ChangePassword.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("Please wait").setMaxProgress(100).show();

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().update_password(user.getUserid(), binding.password.getText().toString(),mobile);
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();
                hud.dismiss();

                if (data.get(0).getMessage() != null) {
                    if (data.get(0).getMessage().equals("1")) {

                        Toast.makeText(getApplicationContext(), "Password Update Successfully", Toast.LENGTH_SHORT).show();
                        finish();


                    }
                    if (data.get(0).getMessage().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Failed !.", Toast.LENGTH_SHORT).show();
                    }

                    if (data.get(0).getMessage().equals("2")) {
                        Toast.makeText(getApplicationContext(), "Mobile Number Wrong !", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Update Failed !", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
                hud.dismiss();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}