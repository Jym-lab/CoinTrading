package com.example.cointrading.model;

import com.google.gson.annotations.SerializedName;

/**
 * 구매한 코인 model
 */
public class CoinBuyModel {

    // 코인 id
    @SerializedName("id")
    private int id = 0;

    // 코인 이름
    @SerializedName("name")
    private String name = "";

    // 구매가
    @SerializedName("buyPrice")
    private int buyPrice = 0;

    // 구매 수량
    @SerializedName("quantity")
    private float quantity;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
