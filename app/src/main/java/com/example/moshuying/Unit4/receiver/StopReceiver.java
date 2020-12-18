package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StopReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("StopReceiver 接收到广播");
        abortBroadcast();
    }
}