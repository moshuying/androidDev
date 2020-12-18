package com.example.moshuying.Unit5;

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

        AddLayoutList("5.1.1","读写内部存储文件","[改编自书5.1.1示例]");
        AddLayoutList("5.1.2","读写外部存储文件","[改编自书5.1.1示例]");
        AddLayoutList("5.1.3","应用的私有文件","[改编自书5.1.1示例]");
        AddLayoutList("5.1.4","访问公共目录","[改编自书5.1.1示例]");
        AddLayoutList("5.2.3","实现记住密码功能","避免用户下一次登陆时再次输入登录信息");
        AddLayoutList("5.4","编程实践：基于数据库的登录验证","基于数据库sqlite的登录验证");

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