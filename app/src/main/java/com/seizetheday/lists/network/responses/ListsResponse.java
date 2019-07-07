package com.seizetheday.lists.network.responses;

import com.google.gson.annotations.SerializedName;
import com.seizetheday.lists.data.vos.ListsVO;

import java.util.ArrayList;
import java.util.List;

public class ListsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("lists")
    private List<ListsVO> lists;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public List<ListsVO> getLists() {
        if (lists == null) {
            lists = new ArrayList<>();
        }
        return lists;
    }

    public Boolean isResponseOk() {
        return (code == 200 && lists != null);
    }
}
