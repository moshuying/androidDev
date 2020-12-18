package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver431 extends BroadcastReceiver {
    public MyReceiver431(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyReceiver431 接收到广播",Toast.LENGTH_SHORT).show();
    }
}
