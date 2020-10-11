package com.example.moshuying;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class SingleTop extends AppCompatActivity {
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        LinearLayout linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        MaterialTextView textView = new MaterialTextView(this);
        textView.setText(String.format("任务ID：%d\n 活动实例：%s",getTaskId(),this.toString()));
        linearLayout.addView(textView);

        MaterialButton button = new MaterialButton(this);
        button.setText("启动 B Activity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTop.this,SingleTop.class));
            }
        });
        linearLayout.addView(button);

        MaterialButton startMain = new MaterialButton(this);
        startMain.setText("启动 Main Activity");
        startMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTop.this,StartupMode.class));
            }
        });
        linearLayout.addView(startMain);

        MaterialButton back = new MaterialButton(this);
        back.setBackgroundColor(Color.parseColor("#3CB371"));
        back.setText("返回上一级");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearLayout.addView(back);
    }
}

