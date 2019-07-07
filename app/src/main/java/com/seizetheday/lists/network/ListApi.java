package com.seizetheday.lists.network;

import com.seizetheday.lists.network.responses.ListsResponse;
import com.seizetheday.lists.utils.ListsConstraints;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ListApi {
    @POST(ListsConstraints.GET_LISTS)
    Call<ListsResponse> loadLists();
}
