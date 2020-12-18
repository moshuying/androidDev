package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiverLocal extends BroadcastReceiver {
    public MyReceiverLocal(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"收到一个本地广播消息",Toast.LENGTH_SHORT).show();
    }
}