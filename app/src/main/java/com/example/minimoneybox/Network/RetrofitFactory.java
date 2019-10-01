package com.example.minimoneybox.Network;

import com.example.minimoneybox.Model.Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit getClient() {

        Gson builder = new GsonBuilder()
                .registerTypeAdapter(Session.class, new SessionDeserializer()).create();

        // Causes PROTOCOL ERROR due to bug in OkHTTPClient https://github.com/square/okhttp/issues/4485
        OkHttpClient.Builder okHttpClient = new OkHttpClient()
                .newBuilder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1));

        return new Retrofit.Builder()
                .baseUrl("https://api-test01.moneyboxapp.com/")
                .addConverterFactory(GsonConverterFactory.create(builder))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();
    }
}