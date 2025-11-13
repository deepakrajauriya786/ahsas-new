package com.nativegame.juicymatch.ui.activity;


import static com.nativegame.juicymatch.ui.config.apicontroller.shop_logo_url;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile extends AppCompatActivity {
    LinearLayout whishlist,myprofile,logout,sellmysecondhand,orderhistory,bestdeal11,shareapp;
    User user;
    String id;
    ImageView updateprofile;
    CircleImageView imageView;

    List<LoginModels> data;
    TextView hiiname,emailnumber,phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        user=new User( getApplicationContext() );

        whishlist=findViewById( R.id.whishlistnext);
        imageView=findViewById( R.id.image);
        myprofile=findViewById( R.id.myprofile );
        hiiname=findViewById( R.id.settinghi);
        shareapp=findViewById( R.id.shareapp);
        sellmysecondhand=findViewById( R.id.sellmysecondhand);
        logout=findViewById( R.id.linealogout );
        emailnumber=findViewById( R.id.emailnumber );
        phonenumber=findViewById( R.id.phonenumber );
        updateprofile=findViewById( R.id.updateprofile );
        orderhistory=findViewById( R.id.orderhistory );
        bestdeal11=findViewById( R.id.bestdeal11 );

        id=user.getUserid();
        if (id.equals( "" )){
            hiiname.setText( "Setting" );

        }else {
            // login.setVisibility( View.GONE );
            emailnumber.setVisibility( View.VISIBLE );
            phonenumber.setVisibility( View.VISIBLE );
            updateprofile.setVisibility( View.VISIBLE );
            hiiname.setText( new StringBuilder( "" ).append( user.getUsername() ) );
            emailnumber.setText( user.getUseremail() );
            phonenumber.setText( user.getUserphone() );
            myprofile.setVisibility( View.VISIBLE );
        }

//        whishlist.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity( new Intent( getApplicationContext(), FavouriteShop.class ) );
//            }
//        } );
       /* login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( ProfileActivity.this, SignUp.class ) );
            }
        } );*/
        myprofile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MyProfile.this, MyAccount.class ) );
            }
        } );

        if (user.getUserid().isEmpty()){
            startActivity(new Intent(getApplicationContext(),LogIn.class));
            SharedPreferences sp = getSharedPreferences("userinfo",MODE_PRIVATE);
            sp.edit().remove("userphone").commit();
            finish();
        }else {
            logout.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity( new Intent( MyProfile.this, ChangePassword.class ) );

                }
            } );

        }


//        sellmysecondhand.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity( new Intent( MyProfile.this, SecondHandProduct.class ) );
//            }
//        } );

        updateprofile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MyProfile.this,ShopLogo.class ) );
            }
        } );



        shareapp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity( new Intent( MyProfile.this,WithdrawDetail.class ) );

            }
        } );

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAutoZoomEnabled(true)
//                        .setCropShape(CropImageView.CropShape.OVAL)
//                        .setAspectRatio(160, 160)
//                        .start(MyProfile.this);

            }
        });

    login();

    }


    private void login() {
        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().vendorProfile(user.getUserid());
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                data = response.body();

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {


                    Glide.with(getApplicationContext()).load(shop_logo_url + data.get(0).getShop_logo())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .placeholder(R.drawable.profile).into(imageView);


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
