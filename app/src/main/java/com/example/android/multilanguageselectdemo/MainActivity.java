package com.example.android.multilanguageselectdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.android.multilanguageselectdemo.fragment.LanguageFragment;

public class MainActivity extends AppCompatActivity {
     FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout= (FrameLayout) findViewById(R.id.container);


        init();
    }
    private void init()
    {
        LanguageFragment fragment=LanguageFragment.newInstance();
        String tag = fragment.getClass().getSimpleName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, tag)
                .commitAllowingStateLoss();

    }


}
