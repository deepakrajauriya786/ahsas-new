package com.nativegame.juicymatch.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nativegame.juicymatch.databinding.ActivityHelpUsBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.SliderModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpUs extends AppCompatActivity {

    ActivityHelpUsBinding binding;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User(getApplicationContext());


        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:9131436589"));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(HelpUs.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }


            }
        });

        binding.helpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.namefist.getText().toString().isEmpty()) {
                    binding.namefist.setError("Enter the Name");
                    binding.namefist.requestFocus();
                    return;
                } else if (binding.phone.getText().toString().isEmpty()) {
                    binding.phone.setError("Enter the Mobile Number");
                    binding.phone.requestFocus();
                    return;
                }else if (binding.email1.getText().toString().isEmpty()) {
                    binding.email1.setError("Enter the Email");
                    binding.email1.requestFocus();
                    return;
                }
                else if (binding.description1212.getText().toString().isEmpty()) {
                    binding.description1212.setError("Enter the Detail");
                    binding.description1212.requestFocus();
                    return;
                }
                else {
                    register();
                }


            }
        });



    }



    private void register() {

        Call<List<SliderModels>> call = apicontroller.getapi().helpUs(user.getUserid(),binding.namefist.getText().toString(),
                binding.phone.getText().toString(),binding.email1.getText().toString(),binding.description1212.getText().toString());
        call.enqueue(new Callback<List<SliderModels>>() {
            @Override
            public void onResponse(Call<List<SliderModels>> call, Response<List<SliderModels>> response) {
                List<SliderModels> data = response.body();

                if (response.code() == 200){
                    if (data.get(0).getMessage() == 1) {
                        binding.dataShow.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Submit Successfully.", Toast.LENGTH_SHORT).show();


                    }

                    if (data.get(0).getMessage() == 0) {
                        Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<SliderModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }




}