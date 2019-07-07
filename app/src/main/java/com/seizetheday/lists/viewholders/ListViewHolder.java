package com.seizetheday.lists.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.seizetheday.lists.R;
import com.seizetheday.lists.data.vos.ListsVO;
import com.seizetheday.lists.delegates.ListDelegate;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private TextView tvItemNumber;
    private TextView tvItemName;
    private TextView tvStoreName;
    private TextView tvTotalItem;
    private TextView tvItemPrice;
    private TextView tvTotalAmount;

    private ListDelegate mListDelegate;
    private ListsVO mListsVO;

    public ListViewHolder(@NonNull View itemView, ListDelegate listDelegate) {
        super(itemView);
        mListDelegate = listDelegate;

        tvItemNumber = itemView.findViewById(R.id.tv_item_number);
        tvItemName = itemView.findViewById(R.id.tv_item_name);
        tvStoreName = itemView.findViewById(R.id.tv_store_name);
        tvTotalItem = itemView.findViewById(R.id.tv_item_count);
        tvItemPrice = itemView.findViewById(R.id.tv_item_amount);
        tvTotalAmount = itemView.findViewById(R.id.tv_total_amount);

        //click on item view
        tvItemNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListDelegate.onTapListNumber();
            }
        });

        //click on item amount
        tvItemPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListDelegate.onTapItemAmount();
            }
        });
    }

    public void onBindData(ListsVO listsVO) {
        mListsVO = listsVO;

        tvItemNumber.setText(mListsVO.getId() + "");
        tvItemName.setText(mListsVO.getItemName());
        tvStoreName.setText(mListsVO.getStoreName());
        tvTotalItem.setText(mListsVO.getTotalItem() + "");
        tvItemPrice.setText(mListsVO.getItemPrice() + "");
        tvTotalAmount.setText((mListsVO.getTotalItem() * mListsVO.getItemPrice()) + "");
    }
}