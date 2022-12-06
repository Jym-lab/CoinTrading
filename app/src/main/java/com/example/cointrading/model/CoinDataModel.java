package com.example.cointrading.model;

import com.google.gson.annotations.SerializedName;

/**
 * 코인 데이터 모델
 */
public class CoinDataModel {

    @SerializedName("id")
    private int id = 0;

    // 코인 이름
    @SerializedName("coinName")
    private String coinName = "";

    // 현재가
    @SerializedName("nowPrice")
    private int nowPrice = 0;

    // 어제 금액
    @SerializedName("yesterdayPrice")
    private int yesterdayPrice = 0;

    // 총 거래대금
    @SerializedName("totalPrice")
    private int totalPrice = 0;

    public static class Builder {
        private int id = 0;
        private String coinName = "";
        private int nowPrice = 0;
        private int yesterdayPrice = 0;
        private int totalPrice = 0;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCoinName(String coinName) {
            this.coinName = coinName;
            return this;
        }

        public Builder setNowPrice(int nowPrice) {
            this.nowPrice = nowPrice;
            return this;
        }

        public Builder setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setYesterdayPrice(int yesterdayPrice) {
            this.yesterdayPrice = yesterdayPrice;
            return this;
        }

        public CoinDataModel build() {
            return new CoinDataModel(this);
        }
    }

    private CoinDataModel(Builder builder) {
        this.id = builder.id;
        this.coinName = builder.coinName;
        this.nowPrice = builder.nowPrice;
        this.totalPrice = builder.totalPrice;
        this.yesterdayPrice = builder.yesterdayPrice;
    }

    public int getId() {
        return id;
    }

    public int getNowPrice() {
        return nowPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getYesterdayPrice() {
        return yesterdayPrice;
    }

    public String getCoinName() {
        return coinName;
    }
}
