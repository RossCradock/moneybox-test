package com.example.minimoneybox.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonAddMoney {

    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("InvestorProductId")
    @Expose
    private int InvestorProductId;

    public JsonAddMoney(int amount, int investorProductId) {
        this.amount = amount;
        InvestorProductId = investorProductId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInvestorProductId() {
        return InvestorProductId;
    }

    public void setInvestorProductId(int investorProductId) {
        InvestorProductId = investorProductId;
    }
}
