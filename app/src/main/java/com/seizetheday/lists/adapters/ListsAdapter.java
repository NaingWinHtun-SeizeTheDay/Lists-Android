package com.seizetheday.lists.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.seizetheday.lists.R;
import com.seizetheday.lists.data.vos.ListsVO;
import com.seizetheday.lists.delegates.ListDelegate;
import com.seizetheday.lists.viewholders.ListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private ListDelegate mListDelegate;

    private List<ListsVO> mLists;

    //setter method for data to VO object
    public void setLists(List<ListsVO> lists) {
        mLists = lists;
        notifyDataSetChanged();
    }

    public ListsAdapter(ListDelegate listDelegate) {
        mListDelegate = listDelegate;
        mLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_viewholder, viewGroup, false), mListDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int position) {
        listViewHolder.onBindData(mLists.get(position));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }
}
