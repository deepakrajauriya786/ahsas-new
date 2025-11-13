package com.nativegame.juicymatch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.MessageModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccount extends AppCompatActivity {
    TextInputEditText name, phone, email, city, address, pincode;
    TextView usename, submit;
    User user;
    LinearLayout linearLayout;
    String userid;
    ImageView backarrow;
    KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        backarrow = findViewById(R.id.backarrow);
        name = findViewById(R.id.profilename);
        phone = findViewById(R.id.profilephone);
        email = findViewById(R.id.profileemail);
        city = findViewById(R.id.profilecity);
        linearLayout = findViewById(R.id.linearaa);
        address = findViewById(R.id.profileaddress);
        pincode = findViewById(R.id.profilepincode);
        usename = findViewById(R.id.usename);
        submit = findViewById(R.id.submit);
        user = new User(getApplicationContext());
        userid = user.getUserid();
//        name.setEnabled(false);
        phone.setEnabled(false);
//        email.setEnabled(false);
//        city.setEnabled(false);
//        address.setEnabled(false);
//        pincode.setEnabled(false);


        if (!userid.isEmpty()) {
            myAccount();
        } else {
            startActivity(new Intent(getApplicationContext(), LogIn.class));
        }

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter the name ");
                    name.requestFocus();
                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("Enter the email ");
                    email.requestFocus();
                }
                if (city.getText().toString().isEmpty()) {
                    city.setError("Enter the city ");
                    city.requestFocus();
                }

                if (address.getText().toString().isEmpty()) {
                    address.setError("Enter the address ");
                    address.requestFocus();
                }
                if (pincode.getText().toString().isEmpty()) {
                    pincode.setError("Enter the pincode ");
                    pincode.requestFocus();
                } else {
                    updateAccount();
                }


            }
        });


    }

    private void updateAccount() {
        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().user_profile_update(user.getUserid(),name.getText().toString(),email.getText().toString()
                ,city.getText().toString(),address.getText().toString(),pincode.getText().toString());
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                List<MessageModels> data = response.body();

                if (data.get(0).getMessage().equals("1")) {
                    Toast.makeText(getApplicationContext(), "Profile updated success.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                }

                if (Integer.parseInt(data.get(0).getMessage()) == 0) {

                    Toast.makeText(getApplicationContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<MessageModels>> call, Throwable t) {

//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void myAccount() {
        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().vendorProfile(user.getUserid());
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {

                    usename.setText(data.get(0).getName());
                    name.setText(data.get(0).getName());
                    phone.setText(data.get(0).getMobile());
                    email.setText(data.get(0).getEmail());
                    city.setText(data.get(0).getCity());
                    address.setText(data.get(0).getAddreass());
                    pincode.setText(data.get(0).getPincode());

                }

                if (Integer.parseInt(data.get(0).getMessage()) == 0) {

                    Toast.makeText(getApplicationContext(), "Something went wrong !", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {

//                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}