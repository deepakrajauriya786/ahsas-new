package com.nativegame.juicymatch.ui.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {

    public static final String imageurl = "https://ahsaschats.com/ahsas/treds/images/";
    public static final String front_display_img = "https://ahsaschats.com/ahsas/treds/front_display_img/";
    public static final String sliderurl = "https://ahsaschats.com/ahsas/treds/banner_img/";
    public static final String shop_logo_url = "https://ahsaschats.com/ahsas/treds/shop_logo/";
    public static final String demand_img = "https://ahsaschats.com/ahsas/treds/demand_img/";
    public static final String shop_display = "https://ahsaschats.com/ahsas/treds/shop_display/";
    public static final String second_hand_img = "https://ahsaschats.com/ahsas/treds/second_hand_img/";
    public static final String coupan_img = "https://ahsaschats.com/ahsas/treds/coupan_img/";
    public static final String url = "https://ahsaschats.com/ahsas/treds/api/";
    private static apicontroller clientobject;
    private static Retrofit retrofit;

    apicontroller(){

        OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build();


        retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(new NullOnEmptyConverterFactory())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

    }

    public static synchronized apicontroller getInstance(){
        if (clientobject==null)
            clientobject = new apicontroller();

        return clientobject;
    }

    public static apiset getapi(){
        return retrofit.create(apiset.class);
    }

}
