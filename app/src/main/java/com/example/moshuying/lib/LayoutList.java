package com.example.moshuying.lib;

import android.view.View;

public class LayoutList {
    private String title;
    private String subTitle;
    private View.OnClickListener listener;
    public LayoutList(String title,String subTitle,View.OnClickListener listener){
        this.title = title;
        this.subTitle = subTitle;
        this.listener = listener;
    }
    public String getTitle(){return title;}
    public String getSubTitle() { return subTitle; }
    public View.OnClickListener getListener() { return listener; }
}