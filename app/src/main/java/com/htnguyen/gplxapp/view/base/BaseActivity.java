package com.htnguyen.gplxapp.view.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        initView();
        initData();
        initEvent();
    }

    protected abstract View getLayoutResourceId();

    protected abstract void initView();

    protected abstract void initData();

    protected  abstract void initEvent();

}
