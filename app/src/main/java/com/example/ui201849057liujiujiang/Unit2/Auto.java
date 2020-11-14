package com.example.ui201849057liujiujiang.Unit2;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.ui201849057liujiujiang.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class Auto extends AppCompatActivity {
    public LinearLayout linearLayout;
    public Intent intent;
    private static final String packageName = "com.example.liujiujiang";
    private static final String className = "Auto";

    private static void printState(){
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : stes) {
            if ((ste.getClassName().equals(packageName+"."+className)) && (!ste.getMethodName().equals("printState"))) {
                System.out.println("正在执行" + ste.getClassName().replace(packageName+".","") + "." + ste.getMethodName()+"()");
            }
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        printState();
    }
    @Override
    protected void onStart(){
        super.onStart();
        printState();
    }
    @Override
    protected void onStop(){
        super.onStop();
        printState();
    }
    @Override
    protected void onPause(){
        super.onPause();
        printState();
    }
    @Override
    protected void onResume(){
        super.onResume();
        printState();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        printState();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Boolean AddBack = true;
        printState();
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        intent = getIntent();
        switch (intent.getStringExtra("type")){
            case "callback": setCallback();AddBack=false; break;
            case "Simple":SimpleHandle(); break;
            case "Parcelable":ParcelableHandle();break;
            case "Serializable":SerializableHandle(); break;
            case "Bundle":BundleHandle(); break;
            case "Json":jsonHandle();break;
            default: addTitle("未接受到任何数据");break;
        }

        if(AddBack){addBack();}
    }
    protected void setCallback(){
        final EditText editText = new EditText(this);
        editText.setText("这里填入活动返回的值");
        linearLayout.addView(editText);

        Button button = new Button(this);
        button.setText("输入完成");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resintent = new Intent();
                resintent.putExtra("data",editText.getText().toString());
                setResult(RESULT_OK,resintent);
                finish();
            }
        });
        linearLayout.addView(button);
    }
    protected void SerializableHandle(){
        addTitle("获取到的数据类型是Serializable");
        User user = (User) intent.getSerializableExtra("user");
        create("name",user.getName());
        create("age",String.valueOf(user.getAge()));
    }
    protected void ParcelableHandle(){
        addTitle("获取到的数据类型是Parcelable");
        User2 user2 = intent.getParcelableExtra("user2");
        create("name",user2.getName());
        create("age",String.valueOf(user2.getAge()));
    }
    protected void SimpleHandle(){
        addTitle("获取到的数据类型是简单数据类型");
        create("simpleDataKey1",intent.getStringExtra("simpleDataKey1"));
        create("simpleDataKey2",intent.getStringExtra("simpleDataKey2"));
    }
    protected void BundleHandle(){
        addTitle("获取到的数据类型是Bundle");
        Bundle bd = intent.getExtras();
        String string = bd.getString("String");

        // 展示接收到的数据
        TextView textView = new TextView(this);
        textView.setText(string);
        textView.setTextSize(22);
        textView.setWidth(358);
        linearLayout.addView(textView);

        final String[] stringArray = bd.getStringArray("StringArray");
        System.out.println(stringArray.toString());
        for(int i = 0,l=stringArray.length;i<l;i++){
            create(stringArray[i],stringArray[i]);
        }

    }

    protected void jsonHandle(){
        addTitle("获取到的数据类型是JSON");

        String data = intent.getStringExtra("data");

        // 展示接收到的数据
        TextView textView = new TextView(this);
        textView.setText(data);
        textView.setTextSize(22);
        textView.setWidth(358);
        linearLayout.addView(textView);

        // 去掉第二个参数就是无序遍历
        LinkedHashMap<String,String> jsonMap = JSON.parseObject(data,new TypeReference<LinkedHashMap<String, String>>(){});
        for(final Map.Entry<String,String>entry:jsonMap.entrySet()){
            create(entry.getKey(),entry.getValue());
        }
    }
    public void create( final String key, final String value){
        Button autoButton = new Button(Auto.this);
        autoButton.setText(key+":"+value);
        autoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str = "你点击了"+key+"按键,它的值是"+value;
                Toast.makeText(Auto.this,str,Toast.LENGTH_LONG).show();
            }
        });
        linearLayout.addView(autoButton);
    }

    protected void addTitle(String title){
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(22);
        textView.setWidth(358);
        linearLayout.addView(textView);
    }
    protected void addBack(){
        Button back = new Button(this);
        back.setText("返回");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout.addView(back);

        TextView textView = new TextView(this);
        textView.setText(R.string.hello_liujiujiang_welcome_to_the_main_activity);
        textView.setAutoLinkMask(Linkify.WEB_URLS);
        linearLayout.addView(textView);
    }
}
