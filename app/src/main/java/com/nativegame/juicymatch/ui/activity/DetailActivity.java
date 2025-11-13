package com.nativegame.juicymatch.ui.activity;


import static com.nativegame.juicymatch.ui.config.apicontroller.shop_logo_url;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityDetailBinding;
import com.nativegame.juicymatch.ui.activity.voiceCallAgora.CallInviteActivity;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    List<String> sizearray;
    String name, receiver_id;
    String l2, l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        name = getIntent().getStringExtra("name");
        receiver_id = getIntent().getStringExtra("receiver_id");
        binding.uId.setText("User Id : " + receiver_id);
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.language1.setText(getIntent().getStringExtra("language1"));
        binding.language2.setText(getIntent().getStringExtra("language2"));
        binding.language3.setText(getIntent().getStringExtra("language3"));
        binding.exp.setText("18-" + getIntent().getStringExtra("age") + " Years");
        binding.ratings.setText(getIntent().getStringExtra("follow") + "\nFollowers");

        l2 = getIntent().getStringExtra("language2");
        l3 = getIntent().getStringExtra("language3");
        if (l2.isEmpty())
            binding.language2.setVisibility(View.GONE);
        if (l3.isEmpty())
            binding.language3.setVisibility(View.GONE);


        Glide.with(this)
                .load(shop_logo_url + getIntent().getStringExtra("image")).placeholder(R.drawable.image).into(binding.profileImage);


        sizearray = new ArrayList<>();
        GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        binding.recimage.setLayoutManager(manager1);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recSimilar.setLayoutManager(manager);

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatRoomActivity.class);
                intent.putExtra("receiver_id", receiver_id);
                intent.putExtra("image", getIntent().getStringExtra("image"));

                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        binding.chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatRoomActivity.class);
                intent.putExtra("receiver_id", receiver_id);
                intent.putExtra("name", name);
                intent.putExtra("image", getIntent().getStringExtra("image"));
                startActivity(intent);
            }
        });
        binding.call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CallInviteActivity.class);
                intent.putExtra("receiver_id", receiver_id);
                intent.putExtra("name", name);
                intent.putExtra("image", getIntent().getStringExtra("image"));
                startActivity(intent);
            }
        });

        binding.searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), CallInviteActivity.class);
                intent1.putExtra("receiver_id", receiver_id);
                intent1.putExtra("name", name);
                intent1.putExtra("image", getIntent().getStringExtra("image"));
                startActivity(intent1);
            }
        });
        binding.assis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), ChatRoomActivity.class);
                intent1.putExtra("receiver_id", receiver_id);
                intent1.putExtra("name", name);
                intent1.putExtra("image", getIntent().getStringExtra("image"));
                startActivity(intent1);
            }
        });


        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                sizearray.clear();
//                astroDataFetch();
                binding.swipeContainer.setRefreshing(false);

            }
        });


//
//        astroDataFetch();
//        simillar();

    }
//    public void simillar() {
//
//        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().adsHomeFetch();
//        call.enqueue(new Callback<List<LoginModels>>() {
//            @Override
//            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
//                List<LoginModels> data = response.body();
//                binding.swipeContainer.setRefreshing(false);
////                String ssss = new Gson().toJson(data);
////                Toast.makeText(MainActivity.this, ssss, Toast.LENGTH_SHORT).show();
//
//
////                if (data.get(0).getMessage().equals("1")) {
////                    Toast.makeText(ChatFetchActivity.this, String.valueOf(data.size()), Toast.LENGTH_SHORT).show();
//
//                SimilarAdapter astroFetchAdapter = new SimilarAdapter( data,"1");
//                binding.recSimilar.setAdapter(astroFetchAdapter);
//
////                }
////                if (data.get(0).getMessage().equals("0")) {
////                    Toast.makeText(MainActivity.this, "hjhigcz", Toast.LENGTH_SHORT).show();
//
//
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
//                binding.swipeContainer.setRefreshing(false);
////                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }


//    public void astroDataFetch() {
//
//        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().detail_fetch(receiver_id,"u");
//        call.enqueue(new Callback<List<LoginModels>>() {
//            @Override
//            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
//                List<LoginModels> data = response.body();
//                binding.swipeContainer.setRefreshing(false);
//
//                if (String.valueOf(response.body()).contains("null")) {
//                    Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
//
//                }else {
//
//                    binding.name.setText(data.get(0).getName());
//                    binding.expert.setText(data.get(0).getExpert());
//                    binding.language.setText(data.get(0).getLanguage());
//                    binding.exp.setText("Exp: "+data.get(0).getExp()+ " years");
//                    binding.price.setText("₹ " +data.get(0).getD_chat_price());
//                    binding.pricePerDay.setText(data.get(0).getChat_price()+"/min");
//                    binding.pricePerDay.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                    binding.about.setText(data.get(0).getAbout_us());
//                    binding.ratings.setText(data.get(0).getRating()+ " orders");
//
//                    Glide.with(getApplicationContext() )
//                            .load( imageurl+ data.get(0).getPhoto1() )
//                            .diskCacheStrategy( DiskCacheStrategy.ALL )
//                            .placeholder( R.drawable.ic_image_black_24dp )
//                            .into( binding.profileImage );
//
//
//                    if (!data.get(0).getPhoto1().isEmpty()) {
//                        sizearray.add(imageurl + data.get(0).getPhoto1());
//                    }
//                    if (!data.get(0).getPhoto2().isEmpty()) {
//                        sizearray.add(imageurl + data.get(0).getPhoto2());
//                    }
//                    if (!data.get(0).getPhoto3().isEmpty()) {
//                        sizearray.add(imageurl + data.get(0).getPhoto3());
//                    }
//                    if (!data.get(0).getPhoto4().isEmpty()) {
//                        sizearray.add(imageurl + data.get(0).getPhoto4());
//                    }
//                    if (!data.get(0).getPhoto5().isEmpty()) {
//                        sizearray.add(imageurl + data.get(0).getPhoto5());
//                    }
//
//                    if (sizearray.size()>0){
//                        ImageAdapter adapter = new ImageAdapter(sizearray,"0");
//                        binding.recimage.setAdapter(adapter);
//
//                    }
//
//
//
//                }
//
//
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
//                binding.swipeContainer.setRefreshing(false);
////                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }


}