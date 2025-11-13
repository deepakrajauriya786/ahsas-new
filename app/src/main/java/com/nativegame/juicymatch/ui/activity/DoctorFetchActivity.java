package com.nativegame.juicymatch.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nativegame.juicymatch.databinding.ActivityDoctorFetchBinding;

public class DoctorFetchActivity extends AppCompatActivity {
    ActivityDoctorFetchBinding binding;
//    String c_id, sc_id, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorFetchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);

//        c_id = getIntent().getStringExtra("c_id");
//        sc_id = getIntent().getStringExtra("sc_id");
//        name = getIntent().getStringExtra("name");
//        binding.title.setText(name);

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        binding.recHomeAds1.setLayoutManager(manager1);

//        process();

    }

//    public void process() {
//
//        Call<List<DoctorDetailModel>> call = apicontroller.getapi().all_doctor_fetch(u);
//
//        call.enqueue(new Callback<List<DoctorDetailModel>>() {
//            @Override
//            public void onResponse(Call<List<DoctorDetailModel>> call, Response<List<DoctorDetailModel>> response) {
//                List<DoctorDetailModel> data = response.body();
//                if (String.valueOf(response.body()).contains("null")) {
//                    Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
//
//                } else {
//
//                    Gson gson = new Gson();
//                    String d= gson.toJson(data);
//                    Log.d("dhytfdgfghfcgf",d);
//
//                    DoctorFetchAdapter adsVendorProfileAdapter = new DoctorFetchAdapter( data);
//                    binding.recHomeAds1.setAdapter(adsVendorProfileAdapter);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DoctorDetailModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }

}