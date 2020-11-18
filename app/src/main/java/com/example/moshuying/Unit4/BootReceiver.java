package com.example.moshuying.Unit4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    private static String BOOT_ACTION ="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"bootup",Toast.LENGTH_SHORT).show();
        String action = intent.getAction();
        if(action.equals(BOOT_ACTION)){
            Intent boot = new Intent(context,AutoLayout.class);
            boot.putExtra("unit","4.4");
            boot.putExtra("title","开机启动应用");
            boot.putExtra("subtitle","开机自启动的应用");
            boot.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(boot);
        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}