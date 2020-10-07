package com.example.moshuying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moshuying.lib.PlatformUtil;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    /**
     * Navigate to the given fragment.
     *
     * @param fragment       Fragment to navigate to.
     * @param addToBackstack Whether or not the current fragment should be added to the backstack.
     */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
//    private static PlatformUtil Platformutil;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button btnStartAnother = (Button)findViewById(R.id.btnStartAnother);// 引用布局中的按钮
//        btnStartAnother.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, BtnStartAnother.class));
//            }
//        });
//
//        Button btnStartMyAty = (Button)findViewById(R.id.btnStartMyAty);
//        btnStartMyAty.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //这里显式的创建了一个Intent对象，MainActivity.this指定启动活动的上下文为当前活动，MyAty.class指定启动的目标组件为MyAty
//                Intent startMyAty = new Intent(MainActivity.this,MyAty.class);
//                startActivity(startMyAty);
//            }
//        });
//
//        findViewById(R.id.btnStartMyAty2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //toStartAnotherActivity 作为参数用于创建隐式Intent，以启动活动HideIntent
//                startActivity(new Intent("toStartAnotherActivity"));
//            }
//        });
//
//        findViewById(R.id.btnStartAnotherApplicationActivity).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shareQQ(MainActivity.this,"分享moshuying的纯文本到qq");
//            }
//        });
//
//        findViewById(R.id.openShare).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Share.class));
//            }
//        });
//
//        findViewById(R.id.usePreOperation).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,PredefinedOperation.class));
//            }
//        });
//
//        findViewById(R.id.jsonEdit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,JsonEdit.class));
//            }
//        });
//    }
//    /**
//     * 直接分享纯文本内容至QQ好友
//     * @param mContext
//     * @param content
//     */
//    public static void shareQQ(Context mContext, String content) {
//        if (PlatformUtil.isInstalledSpecifiedApp(mContext,PlatformUtil.PACKAGE_QQ)) {
//            Intent intent = new Intent("android.intent.action.SEND");
//            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//            intent.putExtra(Intent.EXTRA_TEXT, content);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity"));
//            mContext.startActivity(intent);
//        } else {
//            Toast.makeText(mContext, "您需要安装QQ客户端", Toast.LENGTH_LONG).show();
//        }
//    }
}