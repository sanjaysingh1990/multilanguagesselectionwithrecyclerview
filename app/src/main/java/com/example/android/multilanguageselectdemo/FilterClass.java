package com.example.android.multilanguageselectdemo;

import android.support.v7.widget.RecyclerView;
import android.widget.Filter;

import com.example.android.multilanguageselectdemo.itemtype.NotSelectedItem;
import com.example.android.multilanguageselectdemo.itemtype.SearchBox;
import com.example.android.multilanguageselectdemo.itemtype.SelectedItem;

import java.util.ArrayList;
import java.util.List;

public class FilterClass extends Filter {

    private List<Object> mFriendList;
    private List<Object> mFilteredFriendList;
    private ListAdapter mAdapter;

    public FilterClass(List<Object> friendList, ListAdapter adapter) {
        this.mAdapter = adapter;
        this.mFriendList = friendList;
        this.mFilteredFriendList = new ArrayList();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        mFilteredFriendList.clear();
        final FilterResults results = new FilterResults();

        //here you need to add proper items do filteredContactList
        for (Object item : mFriendList) {
            if (item instanceof NotSelectedItem) {
                if (((NotSelectedItem) item).getValue().toLowerCase().trim().contains(constraint)){
                    mFilteredFriendList.add(item);
                }
            }

        }

        results.values = mFilteredFriendList;
        results.count = mFilteredFriendList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
//        if(mAdapter instanceof FriendsAdapter) {
//            FriendsAdapter adapter= (FriendsAdapter) this.mAdapter;
//            adapter.setList(mFilteredFriendList);
//            adapter.notifyDataSetChanged();
//            if (mFilteredFriendList.size() == 0)
//                adapter.noResultFound();
//        }
//        else if(mAdapter instanceof SelectFriendsAdapter)
//        {
//            SelectFriendsAdapter adapter= (SelectFriendsAdapter) this.mAdapter;
//            adapter.setList(mFilteredFriendList);
//            adapter.notifyDataSetChanged();
//            if (mFilteredFriendList.size() == 0)
//                adapter.noResultFound();
//
//        }
//
//        else if(mAdapter instanceof AddPlayersAdapter)
//        {
//            AddPlayersAdapter adapter= (AddPlayersAdapter) this.mAdapter;
//            adapter.setList(mFilteredFriendList);
//            adapter.notifyDataSetChanged();
//            if (mFilteredFriendList.size() == 0)
//                adapter.noResultFound();
//
//        }
        int pos=0;
        for (Object item : mFriendList) {

            if ((item instanceof SelectedItem)) {
                mFilteredFriendList.add(pos, item);
                pos++;
            }
            else
            {
                break;
            }
        }
        mFilteredFriendList.add(pos,new SearchBox());
        mAdapter.setList(mFilteredFriendList);
        mAdapter.notifyDataSetChanged();
    }
}