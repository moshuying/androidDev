package com.example.moshuying.Unit2.startupMode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.R;
import com.example.moshuying.Unit2.StartupMode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class SingleTop extends AppCompatActivity {
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        System.out.println("任务ID"+getTaskId()+"\n"+this.toString()+"正在创建");
        LinearLayout linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(this);
        title.setTextSize(18);
        title.setText("SingleTop模式");
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,20,10,20);
        title.setLayoutParams(layoutParams);
        linearLayout.addView(title);

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
                startActivity(new Intent(SingleTop.this, StartupMode.class));
            }
        });
        linearLayout.addView(startMain);

        MaterialButton back = new MaterialButton(this);
        back.setBackgroundColor(getResources().getColor(R.color.back));
        back.setText("返回上一级");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linearLayout.addView(back);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("任务ID"+getTaskId()+"\n"+this.toString()+"已经销毁");
    }
}

