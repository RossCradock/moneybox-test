package com.example.minimoneybox.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minimoneybox.Model.JsonAddMoney;
import com.example.minimoneybox.Model.JsonAddMoneyResponse;
import com.example.minimoneybox.Model.ProductResponse;
import com.example.minimoneybox.Network.MoneyboxApiService;
import com.example.minimoneybox.Network.RetrofitFactory;
import com.example.minimoneybox.R;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class IndividualAccountActivity extends AppCompatActivity {

    private TextView productName;
    private TextView planValue;
    private TextView moneyBox;
    private Button addMoney;

    private ProductResponse productResponse;
    private Context context;
    private String bearerToken;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_individual_account);

        productResponse = (ProductResponse) getIntent()
                .getExtras().getSerializable("product");
        bearerToken = getIntent().getStringExtra("token");

        setupViews(productResponse);

        context = this;

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoney(productResponse);
            }
        });
    }

    public void setupViews(ProductResponse productResponse){
        productName = findViewById(R.id.tv_product_name);
        planValue = findViewById(R.id.tv_plan_value);
        moneyBox = findViewById(R.id.tv_money_box);
        addMoney = findViewById(R.id.bt_add_money);

        productName.setText(productResponse.getProduct().getName());
        planValue.setText("Plan Value: £" + productResponse.getPlanValue().toString());
        moneyBox.setText("Moneybox: £" + productResponse.getMoneybox().toString());
    }

    private void addMoney(ProductResponse productResponse){
        Retrofit retrofit = RetrofitFactory.getClient();
        MoneyboxApiService apiService = retrofit.create(MoneyboxApiService.class);
        JsonAddMoney addMoney = new JsonAddMoney(10, productResponse.getId());
        apiService.addPayment("Bearer " + bearerToken, addMoney) // hardcoded values for test login
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<JsonAddMoneyResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonAddMoneyResponse jsonAddMoneyResponse) {
                        Toast.makeText(context, "Money Added", Toast.LENGTH_LONG).show();
                        moneyBox.setText("Moneybox: £" + jsonAddMoneyResponse.getMoneybox().toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Cannot Add More Money", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
