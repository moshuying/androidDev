package com.example.moshuying;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartAnother = (Button)findViewById(R.id.btnStartAnother);// 引用布局中的按钮
        btnStartAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });

        Button btnStartMyAty = (Button)findViewById(R.id.btnStartMyAty);
        btnStartMyAty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //这里显式的创建了一个Intent对象，MainActivity.this指定启动活动的上下文为当前活动，MyAty.class指定启动的目标组件为MyAty
                Intent startMyAty = new Intent(MainActivity.this,MyAty.class);
                startActivity(startMyAty);
            }
        });

        findViewById(R.id.btnStartMyAty2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toStartAnotherActivity 作为参数用于创建隐式Intent，以启动活动HideIntent
                startActivity(new Intent("toStartAnotherActivity"));
            }
        });
    }
}