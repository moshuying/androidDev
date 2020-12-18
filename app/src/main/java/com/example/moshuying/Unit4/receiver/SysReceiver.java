package com.example.moshuying.Unit4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class SysReceiver extends BroadcastReceiver {
    public SysReceiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);
        if(state==WifiManager.WIFI_STATE_DISABLED){
            Toast.makeText(context,"WI-FI连接已关闭！",Toast.LENGTH_SHORT).show();
        }else if(state==WifiManager.WIFI_STATE_ENABLED){
            Toast.makeText(context,"WI-FI连接已关闭！",Toast.LENGTH_SHORT).show();
        }
    }
}
