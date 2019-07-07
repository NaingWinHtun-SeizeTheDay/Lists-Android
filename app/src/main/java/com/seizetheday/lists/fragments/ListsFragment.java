package com.seizetheday.lists.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.seizetheday.lists.R;
import com.seizetheday.lists.adapters.ListsAdapter;
import com.seizetheday.lists.data.models.ListsModel;
import com.seizetheday.lists.delegates.ListDelegate;
import com.seizetheday.lists.events.SuccessGetListsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListsFragment extends Fragment implements ListDelegate {

    private RecyclerView rvList;
    private ListsAdapter mListsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public ListsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        swipeRefreshLayout = view.findViewById(R.id.list_swipe_refresh_layout);

        rvList = view.findViewById(R.id.list_recycler_view);
        rvList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mListsAdapter = new ListsAdapter(this);
        rvList.setAdapter(mListsAdapter);

        swipeRefreshLayout.setRefreshing(true);
        //call singleton pattern model object for data
        ListsModel.getObjInstance().loadLists();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //call singleton pattern model object for data
                ListsModel.getObjInstance().loadLists();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //register on event bus
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //unregister on event bus
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapListNumber() {
        Toast.makeText(this.getContext(), "Tap on list number", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTapItemAmount() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Item Amount");
        builder.setCancelable(false);

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.edit_amount_pop_up, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // send data from the AlertDialog to the Activity
                EditText editText = customLayout.findViewById(R.id.et_item_amount);
                sendDialogDataToActivity(editText.getText().toString());
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // do something with the data coming from the AlertDialog
    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this.getContext(), data, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetLists(SuccessGetListsEvent event) {
        mListsAdapter.setLists(event.getList());
        swipeRefreshLayout.setRefreshing(false);
    }
}