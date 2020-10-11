package com.example.moshuying.startupMode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textview.MaterialTextView;

public class SingleTaskB extends AppCompatActivity {
    private LinearLayout linearLayout;
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        addTitle("Single Task B Activity",18);
        addTitle(String.format("任务ID：%d\n 活动实例：%s",getTaskId(),this.toString()));
        MaterialButton button = new MaterialButton(this);

        button.setText("启动 Main Activity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskB.this,SingleTask.class));
            }
        });
        linearLayout.addView(button);

        addStartSelf("启动 B Activity");

        addBack();
    }
    public void addTitle(String title){
        createTitle(title);
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
    public void addStartSelf(String title){
        MaterialButton button = new MaterialButton(this);
        button.setShapeAppearanceModel(new ShapeAppearanceModel());
        button.setText(title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleTaskB.this,SingleTaskB.class);
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