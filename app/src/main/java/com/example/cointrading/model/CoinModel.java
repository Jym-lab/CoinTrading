package com.example.cointrading.model;

import com.google.gson.annotations.SerializedName;

/**
 * 코인 model
 */
public class CoinModel {

    // 코인 id
    @SerializedName("id")
    private int id = 0;

    // 코인 이름
    @SerializedName("name")
    private String name = "";

    // 고가
    @SerializedName("high")
    private int high = 0;

    // 저가
    @SerializedName("low")
    private int low = 0;

    // 현재가
    @SerializedName("now")
    private int now = 0;

    //거래 대금
    @SerializedName("transactionAmount")
    private int transactionAmount = 0;

    public static class Builder {
        private int id = 0;
        private String name = "";
        private int high = 0;
        private int low = 0;
        private int now = 0;
        private int transactionAmount = 0;

        public CoinModel.Builder setId(int id) {
            this.id = id;
            return this;
        }

        public CoinModel.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public CoinModel.Builder setHigh(int high) {
            this.high = high;
            return this;
        }

        public CoinModel.Builder setLow(int low) {
            this.low = low;
            return this;
        }

        public CoinModel.Builder setNow(int now) {
            this.now = now;
            return this;
        }

        public CoinModel.Builder setTransactionAmount(int transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public CoinModel build() {
            return new CoinModel(this);
        }
    }

    private CoinModel(CoinModel.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.high = builder.high;
        this.low = builder.low;
        this.now = builder.now;
        this.transactionAmount = builder.transactionAmount;
    }

    public int getHigh() {
        return high;
    }

    public int getId() {
        return id;
    }

    public int getLow() {
        return low;
    }

    public int getNow() {
        return now;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public String getName() {
        return name;
    }
}
