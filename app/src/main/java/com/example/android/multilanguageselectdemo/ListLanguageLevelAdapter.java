package com.example.android.multilanguageselectdemo;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.multilanguageselectdemo.itemtype.NotSelectedItem;
import com.example.android.multilanguageselectdemo.itemtype.SearchBox;
import com.example.android.multilanguageselectdemo.itemtype.SelectedItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by android on 25/4/17.
 */

public class ListLanguageLevelAdapter extends RecyclerView.Adapter<ListLanguageLevelAdapter.ItemHolder> {


    private final int ITEM_SELECTED = 1;
    private final int ITEM_NOT_SELECTED = 2;

    private List<String> itemList;
    private Activity mActivity;
    private CallBackLanguageSelected mCallBack;


    public ListLanguageLevelAdapter(Activity activity, List<String> items, CallBackLanguageSelected callBackLanguageSelected) {
        this.itemList = items;
        this.mActivity = activity;
        mCallBack = callBackLanguageSelected;

    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lang_level_selected, parent, false);
        return new ItemHolder(itemView);


    }

    @Override
    public void onBindViewHolder(ItemHolder viewHolder, int position) {


    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class ItemHolder extends RecyclerView.ViewHolder {

        ItemHolder(View view) {
            super(view);


        }

    }


}