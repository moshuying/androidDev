package com.example.moshuying.Unit4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.moshuying.R;
import com.example.moshuying.Unit4.receiver.MyReceiver421;
import com.example.moshuying.Unit4.receiver.MyReceiver422;
import com.example.moshuying.Unit4.receiver.MyReceiverLocal;
import com.example.moshuying.Unit4.receiver.StopReceiver;
import com.google.android.material.button.MaterialButton;
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
            case "4.2.1": create421();break;
            case "4.2.2": create422();break;
            case "4.2.3": create423();break;
            case "4.2.4": create424();break;
            case "4.3": create43();break;
            case "4.4": create44();break;
            default:break;
        }
    }
    final MyReceiver422[] receiver422 = {null};
    private void create421(){
        AutoAddTitle();
        MaterialButton button = new MaterialButton(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        button.setText("发送静态广播");
        button.setOnClickListener(v -> sendBroadcast(new Intent(AutoLayout.this, MyReceiver421.class)));
        layout.addView(button);
    }
    private void create422(){
        AutoAddTitle();
        MaterialButton button = new MaterialButton(this);
        button.setText("注册广播接收器");
        button.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        button.setOnClickListener(v -> {
            if(receiver422[0] ==null){
                receiver422[0] = new MyReceiver422();
                registerReceiver(receiver422[0],new IntentFilter(MyReceiver422.ACTION));
            }
        });
        layout.addView(button);

        MaterialButton button1 = new MaterialButton(this);
        button1.setText("注销广播接收器");
        button1.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        button1.setOnClickListener(v->{
            if(receiver422[0]!=null){
                unregisterReceiver(receiver422[0]);
                receiver422[0]=null;
            }
        });
        layout.addView(button1);

        MaterialButton button2 = new MaterialButton(this);
        button2.setText("发送广播消息");
        button2.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        button2.setOnClickListener(v->{
            sendBroadcast(new Intent(MyReceiver422.ACTION));// 发送广播消息
        });
        layout.addView(button2);
    }
    @SuppressLint("SetTextI18n")
    private void create423(){
        AutoAddTitle();
        MaterialTextView textView = new MaterialTextView(this);
        layout.addView(textView);
    }
    private LocalBroadcastManager localBroadcastManager = null;
    private MyReceiverLocal localReceiver = null;
    private void create424(){
        AutoAddTitle();
        IntentFilter intentFilter = new IntentFilter("MyLocalBroadcastReceiver");
        localBroadcastManager = LocalBroadcastManager.getInstance(AutoLayout.this);
        localReceiver = new MyReceiverLocal();//创建广播接收器
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播接收器

        MaterialButton materialButton = new MaterialButton(AutoLayout.this);
        materialButton.setText("发送广播消息");
        materialButton.setOnClickListener(v->{
            localBroadcastManager.sendBroadcast(new Intent("MyLocalBroadcastReceiver"));//用注册的操作创建Intent
        });
        layout.addView(materialButton);
    }
    private void create43(){
        AutoAddTitle();
        layout.addView(AddMinTitle("会有两次Toast短提示，有先后顺序",12));
        MaterialButton materialButton = new MaterialButton(this);
        materialButton.setText("发送广播默认顺序");
        materialButton.setOnClickListener(v->{
            sendBroadcast(new Intent("com.example.moshuying.ACTION"));
        });
        MaterialButton materialButton2 = new MaterialButton(this);
        materialButton2.setText("发送有序广播");
        materialButton2.setOnClickListener(v->{
            sendBroadcast(new Intent("com.example.moshuying.ACTION"),null);//发送有序广播
        });
        MaterialButton materialButton3 = new MaterialButton(this);
        materialButton3.setText("中止广播传递");
        materialButton3.setOnClickListener(v->{
            sendBroadcast(new Intent(AutoLayout.this,StopReceiver.class));
        });

        layout.addView(materialButton);
        layout.addView(materialButton2);
        layout.addView(materialButton3);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);//注销本地广播接收器
    }

    @SuppressLint("SetTextI18n")
    public void create44(){
        AutoAddTitle();
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
    private void AutoAddTitle(){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
    }
}