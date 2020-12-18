package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver432 extends BroadcastReceiver {
    public MyReceiver432(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyReceiver432 接收到广播",Toast.LENGTH_SHORT).show();
    }
}
