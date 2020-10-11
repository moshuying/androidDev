package com.example.moshuying;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.ShapeAppearanceModel;

import androidx.appcompat.app.AppCompatActivity;

public class StartupMode extends AppCompatActivity {
    public LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        createStandard();
    }
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void createStandard(){
        TextView textView = new TextView(this);
        textView.setText(String.format("任务ID：%d\n 活动实例：%s",getTaskId(),this.toString()));
        linearLayout.addView(textView);
        MaterialButton button = new MaterialButton(this);
        button.setShapeAppearanceModel(new ShapeAppearanceModel());
        button.setText("启动MainActivity");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartupMode.this,StartupMode.class);
                startActivity(intent);
            }
        });
        linearLayout.addView(button);
    }
}
