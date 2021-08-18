package com.com.blog.view;

public interface InitActivity {
    void init();
    void initLr();
    void initSetting();
    default void initViewModel(){};
    default void initAdapter(){}
    default void initData(){}
}
