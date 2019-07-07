package com.seizetheday.lists.data.vos;

import com.google.gson.annotations.SerializedName;

public class ListsVO {

    @SerializedName("id")
    private int id;

    @SerializedName("item_name")
    private String itemName;

    @SerializedName("store_name")
    private String storeName;

    @SerializedName("total_item")
    private int totalItem;

    @SerializedName("item_price")
    private int itemPrice;

    @SerializedName("total_price")
    private int totalPrice;

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
