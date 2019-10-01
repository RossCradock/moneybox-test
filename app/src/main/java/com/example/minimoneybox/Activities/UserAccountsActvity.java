package com.example.minimoneybox.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.minimoneybox.Adapters.ProductArrayAdapter;
import com.example.minimoneybox.Model.ApiUser;
import com.example.minimoneybox.Model.JsonProductResponse;
import com.example.minimoneybox.Model.ProductResponse;
import com.example.minimoneybox.Model.Session;
import com.example.minimoneybox.Network.MoneyboxApiService;
import com.example.minimoneybox.Network.RetrofitFactory;
import com.example.minimoneybox.R;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserAccountsActvity extends AppCompatActivity implements ProductArrayAdapter.ItemClickListener {

    private Retrofit retrofit;
    private String bearerToken;
    private TextView usernameTextView;
    private TextView totalPlanValueTextView;
    private Context context;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ProductResponse> productResponses;
    private ProductArrayAdapter.ItemClickListener itemClickListener;
    private double totalPlanValue;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_user_account);
        context = this;

        retrofit = RetrofitFactory.getClient();

        setupViews();

        //get username and set it to hello username text view
        Intent intent = getIntent();
        String userName = intent.getStringExtra("UserName");
        // if the username is not entered remove the view
        if(userName.isEmpty()){
            usernameTextView.setVisibility(View.GONE);
        }else {
            String helloUser = getString(R.string.hello) + userName;
            usernameTextView.setText(helloUser);
        }

        setupArrayAdapter();

        getSessionId();
    }

    private void getSessionId() {
        MoneyboxApiService apiService = retrofit.create(MoneyboxApiService.class);
        ApiUser apiUser = new ApiUser("androidtest@moneyboxapp.com", "P455word12", "ANYTHING");
        apiService.getUserSessionId(apiUser) // hardcoded values for test login
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Session>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("NETWORK===============", "SUBSCRIBED");
                    }

                    @Override
                    public void onNext(Session session) {
                        bearerToken = session.getBearerToken();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("NETWORK===============", "ERROR");
                    }

                    @Override
                    public void onComplete() {
                        getInvestorProducts();
                    }
                });
    }

    private void getInvestorProducts(){
        MoneyboxApiService apiService = retrofit.create(MoneyboxApiService.class);
        apiService.getInvestorProducts("Bearer " + bearerToken) // hardcoded values for test login
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<JsonProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("NETWORK===============", "SUBSCRIBED");
                    }

                    @Override
                    public void onNext(JsonProductResponse jsonProductResponse) {
                        totalPlanValue = jsonProductResponse.getTotalPlanValue();
                        productResponses = jsonProductResponse.getProductResponses();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("NETWORK===============", "ERROR");
                    }

                    @Override
                    public void onComplete() {
                        // Once the json request has responded, create the array adapter
                        adapter = new ProductArrayAdapter(context, productResponses);
                        ((ProductArrayAdapter) adapter).setClickListener(itemClickListener);
                        recyclerView.setAdapter(adapter);

                        totalPlanValueTextView.setText("Total Plan Value: £" + String.valueOf(totalPlanValue));
                    }
                });
    }

    private void setupArrayAdapter(){
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        itemClickListener = this;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupViews(){
        usernameTextView = findViewById(R.id.tv_username);
        totalPlanValueTextView = findViewById(R.id.tv_total_plan_value);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, IndividualAccountActivity.class);
        intent.putExtra("product", productResponses.get(position));
        intent.putExtra("token", bearerToken);
        startActivity(intent);
    }
}
