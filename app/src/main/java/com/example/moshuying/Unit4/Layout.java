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
//public class Layout extends Unit4Layout {
//    private List<LayoutList> layoutLists = new ArrayList<>();
//    @Override
//    protected void onCreate(Bundle state){
//        super.onCreate(state);
//        LinearLayout linearLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//        linearLayout.setLayoutParams(layoutParams);
//        setContentView(linearLayout);
//
//        setTitle("第四章 广播机制");
//        showBackwardView("首页",true);
//        AddLayoutList("4.2.1","静态注册广播接收器","发送一个广播消息");
//        AddLayoutList("4.2.2","动态注册和注销广播接收器","用户控制是否启用接收器来接受广播");
//
//
//        RecyclerView recyclerView = new RecyclerView(this);
//        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        linearLayout.addView(recyclerView);
//    }
//    private void AddLayoutList(final String unit, final String title, final String subtitle){
//        layoutLists.add(new LayoutList(unit+" "+title, subtitle, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Layout.this,AutoLayout.class);
//                intent.putExtra("unit",unit);
//                intent.putExtra("title",title);
//                intent.putExtra("subtitle",subtitle);
//                startActivity(intent);
//            }
//        }));
//    }
//}
public class Layout extends AppCompatActivity {
    private List<LayoutList> layoutLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);

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