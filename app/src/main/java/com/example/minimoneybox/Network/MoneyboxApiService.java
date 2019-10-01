package com.example.minimoneybox.Network;

import com.example.minimoneybox.Model.ApiUser;
import com.example.minimoneybox.Model.JsonAddMoney;
import com.example.minimoneybox.Model.JsonAddMoneyResponse;
import com.example.minimoneybox.Model.JsonProductResponse;
import com.example.minimoneybox.Model.Session;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MoneyboxApiService {
    // API service for the user
    @Headers({"AppId: 3a97b932a9d449c981b595",
            "Content-Type: application/json",
            "appVersion: 5.10.0",
            "apiVersion: 3.0.0"})
    @POST("users/login")
    Observable<Session> getUserSessionId(@Body ApiUser apiUser);

    // api for products
    @Headers({"AppId: 3a97b932a9d449c981b595",
            "Content-Type: application/json",
            "appVersion: 5.10.0",
            "apiVersion: 3.0.0"
            })
    @GET("/investorproducts")
    Observable<JsonProductResponse> getInvestorProducts(@Header("Authorization") String bearerToken);

    // api to add money
    @Headers({"AppId: 3a97b932a9d449c981b595",
            "Content-Type: application/json",
            "appVersion: 5.10.0",
            "apiVersion: 3.0.0"
    })
    @POST("/oneoffpayments")
    Observable<JsonAddMoneyResponse> addPayment(@Header("Authorization") String bearerToken, @Body JsonAddMoney jsonAddMoney);
}
