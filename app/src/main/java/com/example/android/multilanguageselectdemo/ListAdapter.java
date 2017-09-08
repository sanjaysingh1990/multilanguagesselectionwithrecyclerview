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

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int ITEM_SELECTED = 1;
    private final int ITEM_NOT_SELECTED = 2;
    private final int SEARCH_BOX = 3;

    private List<Object> itemList;
    private Activity mActivity;
    FilterClass mFilter;
    private boolean mFirstTime;
    private List<Object> mFilteredList = new ArrayList<>();
    private CallBackLanguageSelected mCallBack;


    public ListAdapter(Activity activity, List<Object> items, CallBackLanguageSelected callBackLanguageSelected) {
        this.itemList = items;
        this.mFilteredList.addAll(items);
        this.mActivity = activity;
        mCallBack = callBackLanguageSelected;
        mFilter = new FilterClass(items, this);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ITEM_SELECTED:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_selected, parent, false);
                return new SelectedItemHolder(itemView);
            case ITEM_NOT_SELECTED:

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_not_selected, parent, false);
                return new NotSelectedItemHolder(view);
            case SEARCH_BOX:

                View addressView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_searchbox, parent, false);
                return new SearchHolder(addressView);

        }


        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM_SELECTED:
                SelectedItemHolder holder1 = (SelectedItemHolder) viewHolder;
                SelectedItem selectedItem = (SelectedItem) mFilteredList.get(position);
                holder1.mTextValue.setText(selectedItem.getValue());

                break;
            case ITEM_NOT_SELECTED:
                NotSelectedItemHolder holder2 = (NotSelectedItemHolder) viewHolder;
                NotSelectedItem notSelectedItem = (NotSelectedItem) mFilteredList.get(position);
                holder2.mTextValue.setText(notSelectedItem.getValue());
                break;
            case SEARCH_BOX:
                SearchHolder searchHolder = (SearchHolder) viewHolder;
                //to avoid focus first time
                if (!mFirstTime) {
                    mFirstTime = true;
                } else {
                    searchHolder.mSearchBox.requestFocus();
                }
                break;
        }

    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mFilteredList.get(position) instanceof SelectedItem) {
            return ITEM_SELECTED;
        } else if (mFilteredList.get(position) instanceof NotSelectedItem) {
            return ITEM_NOT_SELECTED;
        } else {
            return SEARCH_BOX;

        }
    }


    class SelectedItemHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mConstraintLayout;
        TextView mTextValue;

        SelectedItemHolder(View view) {
            super(view);
            mConstraintLayout = (ConstraintLayout) view.findViewById(R.id.item_container);
            mTextValue = (TextView) view.findViewById(R.id.text_value);

            mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String value = ((SelectedItem) mFilteredList.get(getAdapterPosition())).getValue();
                    Log.e("data", value);
                    mFilteredList.remove(getAdapterPosition());
                    searchAndRemoveSelected(value);
                    mFilteredList.add(new NotSelectedItem(value));
                    itemList.add(new NotSelectedItem(value));

                    notifyDataSetChanged();
                    Collections.sort(mFilteredList, new Comparator<Object>() {
                        public int compare(Object obj1, Object obj2) {
                            if ((obj1 instanceof NotSelectedItem) && obj2 instanceof NotSelectedItem) {
                                String val1 = ((NotSelectedItem) obj1).getValue();
                                String val2 = ((NotSelectedItem) obj2).getValue();

                                return val1.compareTo(val2);
                            }
                            return 0;
                        }
                    });

                    Collections.sort(itemList, new Comparator<Object>() {
                        public int compare(Object obj1, Object obj2) {
                            if ((obj1 instanceof NotSelectedItem) && obj2 instanceof NotSelectedItem) {
                                String val1 = ((NotSelectedItem) obj1).getValue();
                                String val2 = ((NotSelectedItem) obj2).getValue();

                                return val1.compareTo(val2);
                            }
                            return 0;
                        }
                    });

                    notifyDataSetChanged();
                    MainActivity mainActivity = (MainActivity) mActivity;
                    mainActivity.scrollToTop(0);


                }
            });

        }

    }

    //call when you want to filter
    public void filterList(String text) {
        mFilter.filter(text);
    }

    class SearchHolder extends RecyclerView.ViewHolder {

        EditText mSearchBox;
        int i = 0;

        SearchHolder(View view) {
            super(view);
            mSearchBox = (EditText) view.findViewById(R.id.edittext_search);
            /**
             ************************ filter result ****************
             */
            mSearchBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if (charSequence.length() > 0)
                        filterList(charSequence.toString());
                    else {
                        mFilteredList = new ArrayList<>();
                        mFilteredList.addAll(itemList);
                        notifyDataSetChanged();

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            mSearchBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = (MainActivity) mActivity;
                    mainActivity.moveto(getAdapterPosition());


                }
            });

        }


    }

    public void setList(List<Object> list) {

        this.mFilteredList = list;

    }

    class NotSelectedItemHolder extends RecyclerView.ViewHolder {


        ConstraintLayout mConstraintLayout;
        TextView mTextValue;

        NotSelectedItemHolder(View view) {
            super(view);
            mConstraintLayout = (ConstraintLayout) view.findViewById(R.id.item_container);
            mTextValue = (TextView) view.findViewById(R.id.text_value);
            mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!(getAdapterPosition() >= 0 && getAdapterPosition() < mFilteredList.size())) {
                        return;
                    }
                    String value = ((NotSelectedItem) mFilteredList.get(getAdapterPosition())).getValue();
                    if (mCallBack != null) {
                        mCallBack.languageSelected(value);
                    }
                    mFilteredList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    MainActivity mainActivity = (MainActivity) mActivity;
                    searchAndRemoveNotSelected(value);

                    int i = 0;
                    for (Object object : mFilteredList) {
                        if (mFilteredList.get(i) instanceof SearchBox) {
                            mFilteredList.add(i, new SelectedItem(value, getAdapterPosition() + 1));
                            notifyDataSetChanged();

                            break;
                        }
                        i++;
                    }
                    i = 0;
                    for (Object object : itemList) {
                        if (itemList.get(i) instanceof SearchBox) {
                            itemList.add(i, new SelectedItem(value, (getAdapterPosition() + 1)));
                            break;
                        }
                        i++;
                    }
                    //mainActivity.scrollToTop(0);

                }
            });
        }


    }

    private void searchAndRemoveNotSelected(final String value) {

        int i = 0;
        for (Object obj : itemList) {

            if (obj instanceof NotSelectedItem) {
                String text = ((NotSelectedItem) itemList.get(i)).getValue();
                if (text.compareToIgnoreCase(value) == 0) {
                    itemList.remove(i);
                    break;
                }
            }
            i++;

        }

    }

    private void searchAndRemoveSelected(final String value) {

        int i = 0;
        for (Object obj : itemList) {

            if (obj instanceof SelectedItem) {
                String text = ((SelectedItem) itemList.get(i)).getValue();
                if (text.compareToIgnoreCase(value) == 0) {
                    itemList.remove(i);
                    break;
                }
            }
            i++;

        }

    }


}