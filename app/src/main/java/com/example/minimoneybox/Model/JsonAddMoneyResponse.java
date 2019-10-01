package com.example.minimoneybox.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonAddMoneyResponse {
    @SerializedName("Moneybox")
    @Expose
    private Double moneybox;

    public Double getMoneybox() {
        return moneybox;
    }

    public void setMoneybox(Double moneybox) {
        this.moneybox = moneybox;
    }
}
