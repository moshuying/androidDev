package com.example.moshuying;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moshuying.Unit3.Layout;
import com.example.moshuying.lib.LayoutAdapter;
import com.example.moshuying.lib.LayoutList;

import java.util.ArrayList;
import java.util.List;

public class UnitChoose extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        LinearLayout BaseLinearLayout =findViewById(R.id.auto_list_item);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams RlayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(RlayoutParams);
        BaseLinearLayout.addView(relativeLayout);

        List<LayoutList> layoutLists = new ArrayList<>();
        layoutLists.add(new LayoutList("一单元", "开发起步", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this,Unit1.class));
            }
        }));
        layoutLists.add(new LayoutList("二单元", "活动", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this,MainActivity.class));
            }
        }));
        layoutLists.add(new LayoutList("三单元", "UI设计", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this, Layout.class));
            }
        }));
        layoutLists.add(new LayoutList("四单元", "广播机制", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this, com.example.moshuying.Unit4.Layout.class));
            }
        }));
        layoutLists.add(new LayoutList("五单元", "数据存储", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this, com.example.moshuying.Unit5.Layout.class));
            }
        }));

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        relativeLayout.addView(recyclerView);
    }
}
