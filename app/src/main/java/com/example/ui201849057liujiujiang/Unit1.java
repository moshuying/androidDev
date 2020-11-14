package com.example.ui201849057liujiujiang;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class Unit1 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText("简单介绍起步章节，配置开发环境等");
        linearLayout.addView(textView);
    }
}
