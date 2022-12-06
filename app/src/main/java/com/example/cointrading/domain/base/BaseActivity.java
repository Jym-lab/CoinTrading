package com.example.cointrading.domain.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivity extends AppCompatActivity {

    // 기본 데이터 초기화
    protected abstract void initDefaultData();

    // 레이아웃 관련 초기화
    protected abstract void initLayout();

    // 리스터 초기화
    protected abstract void initListener();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    initDefaultData();
    initLayout();
    initListener();
}
}
