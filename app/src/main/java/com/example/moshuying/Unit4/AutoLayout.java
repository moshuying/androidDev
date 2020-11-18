package com.example.moshuying.Unit4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.R;
import com.google.android.material.textview.MaterialTextView;

import android.widget.TextView;

public class AutoLayout extends AppCompatActivity{
    private LinearLayout layout;
    private Intent intent;
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        layout = findViewById(R.id.auto_list_item);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.height = MATCH_PARENT;
        layout.setLayoutParams(layoutParams);

        intent = getIntent();
        String unit = intent.getStringExtra("unit");
        assert unit != null;
        switch (unit){
            case "4.4": create44();break;
            default:break;
        }
    }
    public void create44(){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        MaterialTextView materialTextView = new MaterialTextView(this);
        materialTextView.setText("这是开机启动的app");
        layout.addView(materialTextView);
    }
    public TextView AddMinTitle(String title,int size){
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText(title);
        textView.setTextSize(size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}