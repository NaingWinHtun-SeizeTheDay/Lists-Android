package com.seizetheday.lists.data.models;

import com.seizetheday.lists.network.dataagents.RetrofitDataAgent;

public class ListsModel {

    //static attribute
    private static ListsModel objInstance;

    //retrofit data agent
    private RetrofitDataAgent mRetrofitDataAgent;

    //private constructor
    private ListsModel() {
        //initialize data agent(singleton pattern)
        mRetrofitDataAgent = RetrofitDataAgent.getObjInstance();
    }

    //static getter method
    public static ListsModel getObjInstance() {

        if (objInstance == null) {
            objInstance = new ListsModel();
        }
        return objInstance;
    }

    //load from data agent
    public void loadLists() {
        mRetrofitDataAgent.loadLists();
    }
}
