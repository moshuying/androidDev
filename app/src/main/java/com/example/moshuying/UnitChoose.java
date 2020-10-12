package com.example.moshuying;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.Unit3.Layout;
import com.google.android.material.button.MaterialButton;

public class UnitChoose extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit_choose);

        RelativeLayout relativeLayout = findViewById(R.id.unit_choose);

        RelativeLayout.LayoutParams RlayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        MaterialButton unit2 = new MaterialButton(this);
        unit2.setText("二单元 活动");
        unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this,MainActivity.class));
            }
        });
        int id = 1000000324;
        unit2.setId(id);
        unit2.setLayoutParams(RlayoutParams);
        relativeLayout.addView(unit2);

        MaterialButton unit3 = new MaterialButton(this);
        unit3.setText("三单元 UI设计");
        unit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this, Layout.class));
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW,id);
        unit3.setLayoutParams(layoutParams);
        relativeLayout.addView(unit3);
    }
}
