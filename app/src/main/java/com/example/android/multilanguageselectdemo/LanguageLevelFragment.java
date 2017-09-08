package com.example.android.multilanguageselectdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.multilanguageselectdemo.databinding.FragmentLanguagesLevelBinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LanguageLevelFragment extends Fragment implements CallBackLanguageSelected {

    private FragmentLanguagesLevelBinding binding;
    private List<String> mItemList;

    /**
     * Create a new instance of the fragment
     */
    public static LanguageLevelFragment newInstance() {
        LanguageLevelFragment fragment = new LanguageLevelFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_languages_level, container, false));

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addItems();
        init();
    }

    void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        ListLanguageLevelAdapter mAdapter = new ListLanguageLevelAdapter(getActivity(), mItemList, this);
        binding.recyclerView.setAdapter(mAdapter);

    }


    private void addItems() {
        mItemList = new ArrayList<>();
        mItemList.add("Begginer");
        mItemList.add("Intermediate");
        mItemList.add("Fluent");

    }

    @Override
    public void languageSelected(String value) {

    }

}
