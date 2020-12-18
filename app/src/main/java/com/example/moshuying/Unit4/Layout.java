package com.example.moshuying.Unit4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moshuying.R;

import com.example.moshuying.lib.LayoutAdapter;
import com.example.moshuying.lib.LayoutList;
import com.example.moshuying.lib.Unit4Layout;

import java.util.ArrayList;
import java.util.List;

public class Layout extends AppCompatActivity {
    private List<LayoutList> layoutLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);

        AddLayoutList("4.1","广播机制简介","广播可分为标准广播和有序广播。");
        AddLayoutList("4.2.1","静态注册广播接收器","Android提供一个BroadcastReceiver类，通过扩展该类，重写onReceive()方法，即可创建一个广播接收器。");
        AddLayoutList("4.2.2","动态注册和注销广播接收器","通过执行持续代码来注册和注销（系统全局）广播接收器。");
        AddLayoutList("4.2.3","接收系统广播","WIFI断开或连接时，系统会发送广播信息。");
        AddLayoutList("4.2.4","发送本地广播","不希望关键的广播消息被其他应用接收。");
        AddLayoutList("4.3","广播接收器优先级与有序广播","接收器的优先级。");
        AddLayoutList("4.4","开机启动应用","开机自启动的应用");


        RecyclerView recyclerView = findViewById(R.id.unit3_recyclerview);
        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void AddLayoutList(final String unit, final String title, final String subtitle){
        layoutLists.add(new LayoutList(unit+" "+title, subtitle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this, AutoLayout.class);
                intent.putExtra("unit",unit);
                intent.putExtra("title",title);
                intent.putExtra("subtitle",subtitle);
                startActivity(intent);
            }
        }));
    }
}