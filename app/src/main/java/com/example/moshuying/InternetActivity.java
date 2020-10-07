package com.example.moshuying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InternetActivity extends AppCompatActivity {
    @Override
    protected void  onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.internet_activity);
        LinearLayout layout = findViewById(R.id.internet_list_item);
        layout.setOrientation(LinearLayout.VERTICAL);
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri uri = appLinkIntent.getData();
        String name = uri.getQueryParameter("name");
        String data = uri.getQueryParameter("data");
        TextView textView = new TextView(this);
        textView.setText("从浏览器启动的页面appLinkAction为"+appLinkAction+"\n接受到了如下参数：\nname:" + name + "\ndata:" + data);
        layout.addView(textView);

        Button back = new Button(this);
        back.setText("返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InternetActivity.this,MainActivity.class));
            }
        });
        layout.addView(back);
        // ATTENTION: This was auto-generated to handle app links.
    }
}
