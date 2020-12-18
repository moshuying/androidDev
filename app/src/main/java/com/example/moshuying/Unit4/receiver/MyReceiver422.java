package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver422 extends BroadcastReceiver {
    public static String ACTION="learnbroadcastreceiver2.Myreceiver2";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"收到一条广播消息",Toast.LENGTH_LONG).show();
    }
}