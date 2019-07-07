package com.seizetheday.lists.network.dataagents;

import com.seizetheday.lists.events.APIErrorEvent;
import com.seizetheday.lists.events.SuccessGetListsEvent;
import com.seizetheday.lists.network.ListApi;
import com.seizetheday.lists.network.responses.ListsResponse;
import com.seizetheday.lists.utils.ListsConstraints;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgent implements DataAgent {

    private static RetrofitDataAgent objInstance;

    private ListApi mListApi;

    private RetrofitDataAgent() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ListsConstraints.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build();

        mListApi = mRetrofit.create(ListApi.class);
    }

    public static RetrofitDataAgent getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadLists() {
        Call<ListsResponse> loadLists = mListApi.loadLists();

        loadLists.enqueue(new Callback<ListsResponse>() {
            @Override
            public void onResponse(Call<ListsResponse> call, Response<ListsResponse> response) {
                ListsResponse listsResponse = response.body();

                if (listsResponse != null && listsResponse.isResponseOk()) {
                    SuccessGetListsEvent successGetListsEvent = new SuccessGetListsEvent(listsResponse.getLists());
                    EventBus.getDefault().post(successGetListsEvent);
                } else {
                    APIErrorEvent apiErrorEvent = new APIErrorEvent("Sorry,Error occur.");
                    EventBus.getDefault().post(apiErrorEvent);
                }
            }

            @Override
            public void onFailure(Call<ListsResponse> call, Throwable t) {
                APIErrorEvent apiErrorEvent = new APIErrorEvent(t.getMessage());
                EventBus.getDefault().post(apiErrorEvent);
            }
        });
    }
}
