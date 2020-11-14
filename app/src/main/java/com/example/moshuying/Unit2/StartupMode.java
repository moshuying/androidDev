package com.example.moshuying.Unit2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.moshuying.R;
import com.example.moshuying.Unit2.startupMode.SingleInstance;
import com.example.moshuying.Unit2.startupMode.SingleTask;
import com.example.moshuying.Unit2.startupMode.SingleTop;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textview.MaterialTextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartupMode extends AppCompatActivity {
    public LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        createStandard();
        createSingleTop();
        createSingleTask();
        createSingleInstance();
        addBack();
    }
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void createStandard(){
        addTitle("Standard 模式 （默认模式）",18);
        addTitle(String.format("任务ID：%d\n 活动实例：%s",getTaskId(),this.toString()));
        addStartSelf();
    }
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void createSingleTop(){
        addTitle("single Top 模式",18);
        addTitle(String.format("任务ID：%d\n 活动实例：%s",getTaskId(),this.toString()));

        MaterialButton BActivity = new MaterialButton(this);
        BActivity.setText("进入 single Top 模式");
        BActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupMode.this, SingleTop.class));
            }
        });
        linearLayout.addView(BActivity);
    }
    @SuppressLint("SetTextI18n")
    public void createSingleTask(){
        addTitle("SingleTask 模式",18);
        MaterialButton button = new MaterialButton(this);
        button.setText("进入 Single Task 活动");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupMode.this, SingleTask.class));
            }
        });
        linearLayout.addView(button);
    }
    @SuppressLint("SetTextI18n")
    public void createSingleInstance(){
        addTitle("SingleInstance 模式",18);
        MaterialButton button = new MaterialButton(this);
        button.setText("进入 Single Instance 活动");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartupMode.this, SingleInstance.class));
            }
        });
        linearLayout.addView(button);
    }
    public TextView addTitle(String title){
        return createTitle(title);
    }
    public void addTitle(String title ,int size){
        TextView textView = createTitle(title);
        textView.setTextSize(size);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(10,20,10,20);
        textView.setLayoutParams(layoutParams);
    }
    public TextView createTitle(String title){
        TextView textView = new MaterialTextView(this);
        textView.setText(title);
        linearLayout.addView(textView);
        return textView;
    }
    @SuppressLint("SetTextI18n")
    public void addStartSelf(){
        MaterialButton button = new MaterialButton(this);
        button.setShapeAppearanceModel(new ShapeAppearanceModel());
        button.setText("启动 Main Activity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartupMode.this,StartupMode.class);
                startActivity(intent);
            }
        });
        linearLayout.addView(button);
    }

    public void addBack(){
        MaterialButton button = new MaterialButton(this);
        button.setBackgroundColor(getResources().getColor(R.color.back));
        button.setText("返回上一级");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout.addView(button);
    }
}