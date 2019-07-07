package com.seizetheday.lists.events;

import com.seizetheday.lists.data.vos.ListsVO;

import java.util.List;

public class SuccessGetListsEvent {

    private List<ListsVO> mList;

    public SuccessGetListsEvent(List<ListsVO> list) {
        mList = list;
    }

    public List<ListsVO> getList() {
        return mList;
    }
}
