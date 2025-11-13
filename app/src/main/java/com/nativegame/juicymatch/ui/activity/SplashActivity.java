package com.nativegame.juicymatch.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.nativegame.juicymatch.databinding.ActivitySplashBinding;
import com.nativegame.juicymatch.ui.models.User;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        user = new User(getApplicationContext());
//
//        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
//                R.anim.move_left);
//        binding.logo.startAnimation(hyperspaceJumpAnimation);
//        Animation hyperspaceJumpAnimation1 = AnimationUtils.loadAnimation(this,
//                R.anim.move_right);
//        binding.logo.startAnimation(hyperspaceJumpAnimation);
//
//        binding.india.startAnimation(hyperspaceJumpAnimation1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread() {

            public void run() {
                try {
                    sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    Uri uri = getIntent().getData();
                    if (uri != null) {
                        String uu = String.valueOf(uri);
                        if (uu.equals("https://getmii.in/") || uu.equals("www.getmii.in") || uu.equals("www.getmii.in/") || uu.equals("http://getmii.in")
                                || uu.equals("http://getmii.in/") || uu.equals("https://www.getmii.in/")) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));

                        } else {
                            String removedata = uu.substring(uu.lastIndexOf(".in/") + 4);
                            String postid = removedata.substring(0, 8);

                            if (postid.equals("product/")) {
                                String isone = removedata.substring( removedata.lastIndexOf( "product/" ) + 8 );
                                String dataremove = isone.substring(0, isone.lastIndexOf('/'));
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                intent.putExtra("p_id", dataremove);
                                intent.putExtra("cs_id", "");
                                intent.putExtra("coupon", "");
                                startActivity(intent);
                                finish();
                            }


                        }
                    } else {
                        startActivity(new Intent(SplashActivity.this, LogIn.class));
                        finish();
                    }
                }

            }

        };
        thread.start();


    }
}