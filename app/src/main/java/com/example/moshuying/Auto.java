package com.example.moshuying;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.LinkedHashMap;
import java.util.Map;

public class Auto extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto);
        LinearLayout layout = findViewById(R.id.auto_list_item);
        layout.setOrientation(LinearLayout.VERTICAL);
        Intent intent = getIntent();

        if(null != intent.getStringExtra("data") && !intent.getStringExtra("data").equals("")){
            addTitle(layout,"获取到的数据类型是JSON");
            jsonHandle(layout,intent);
        }else{
            addTitle(layout,"获取到的数据类型是Bundle");
            BundleHandle(layout,intent);
        }

        addBack(layout);
    }

    protected void BundleHandle(LinearLayout layout,Intent intent){
        Bundle bd = intent.getExtras();
        String string = bd.getString("String");

        // 展示接收到的数据
        TextView textView = new TextView(this);
        textView.setText(string);
        textView.setTextSize(22);
        textView.setWidth(358);
        layout.addView(textView);

        final String[] stringArray = bd.getStringArray("StringArray");
        System.out.println(stringArray.toString());
        for(int i = 0,l=stringArray.length;i<l;i++){
            Button autoButton = new Button(this);
            autoButton.setText(stringArray[i]);
            final int finalI = i;
            autoButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String str = "你点击了"+stringArray[finalI].toString();
                    Toast.makeText(Auto.this,str,Toast.LENGTH_LONG).show();
                }
            });
            layout.addView(autoButton);
        }

    }

    protected void jsonHandle(LinearLayout layout,Intent intent){
        String data = intent.getStringExtra("data");

        // 展示接收到的数据
        TextView textView = new TextView(this);
        textView.setText(data);
        textView.setTextSize(22);
        textView.setWidth(358);
        layout.addView(textView);

        // 去掉第二个参数就是无序遍历
        LinkedHashMap<String,String> jsonMap = JSON.parseObject(data,new TypeReference<LinkedHashMap<String, String>>(){});
        for(final Map.Entry<String,String>entry:jsonMap.entrySet()){
            Button autoButton = new Button(this);
            autoButton.setText(entry.getKey());
            autoButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String str = "你点击了"+entry.getKey()+"按键,它的值是"+entry.getValue();
                    Toast.makeText(Auto.this,str,Toast.LENGTH_LONG).show();
                }
            });
            layout.addView(autoButton);
        }
    }

    protected void addTitle(LinearLayout layout,String title){
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(22);
        textView.setWidth(358);
        layout.addView(textView);
    }
    protected void addBack(LinearLayout layout){
        Button back = new Button(this);
        back.setText("返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layout.addView(back);
    }
}
