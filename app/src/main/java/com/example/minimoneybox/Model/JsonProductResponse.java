package com.example.minimoneybox.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonProductResponse {

    @SerializedName("MoneyboxEndOfTaxYear")
    @Expose
    private String moneyboxEndOfTaxYear;
    @SerializedName("TotalPlanValue")
    @Expose
    private Double totalPlanValue;
    @SerializedName("TotalEarnings")
    @Expose
    private Double totalEarnings;
    @SerializedName("TotalContributionsNet")
    @Expose
    private Integer totalContributionsNet;
    @SerializedName("TotalEarningsAsPercentage")
    @Expose
    private Double totalEarningsAsPercentage;
    @SerializedName("ProductResponses")
    @Expose
    private List<ProductResponse> productResponses = null;

    public String getMoneyboxEndOfTaxYear() {
        return moneyboxEndOfTaxYear;
    }

    public void setMoneyboxEndOfTaxYear(String moneyboxEndOfTaxYear) {
        this.moneyboxEndOfTaxYear = moneyboxEndOfTaxYear;
    }

    public Double getTotalPlanValue() {
        return totalPlanValue;
    }

    public void setTotalPlanValue(Double totalPlanValue) {
        this.totalPlanValue = totalPlanValue;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Integer getTotalContributionsNet() {
        return totalContributionsNet;
    }

    public void setTotalContributionsNet(Integer totalContributionsNet) {
        this.totalContributionsNet = totalContributionsNet;
    }

    public Double getTotalEarningsAsPercentage() {
        return totalEarningsAsPercentage;
    }

    public void setTotalEarningsAsPercentage(Double totalEarningsAsPercentage) {
        this.totalEarningsAsPercentage = totalEarningsAsPercentage;
    }

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }
}
