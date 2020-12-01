package com.example.moshuying.Unit8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moshuying.R;
import com.example.moshuying.Unit6.AutoLayout;
import com.example.moshuying.lib.LayoutAdapter;
import com.example.moshuying.lib.LayoutList;

import java.util.ArrayList;
import java.util.List;

public class Layout extends AppCompatActivity {
    private List<LayoutList> layoutLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);

        AddLayoutList("6.4","编程实践：自定义音乐播放器","实现一个音乐播放器");

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