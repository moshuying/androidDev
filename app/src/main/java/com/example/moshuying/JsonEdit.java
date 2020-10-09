package com.example.moshuying;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class JsonEdit extends AppCompatActivity {
    private static final int REQUEST_CODE = 1004;//标识当前活动的请求
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_edit);

        findViewById(R.id.simpleData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonEdit.this,Auto.class);
                TextView textView1 = findViewById(R.id.simpleDataKey1);
                TextView textView2 = findViewById(R.id.simpleDataKey2);
                intent.putExtra("type","Simple");
                intent.putExtra("simpleDataKey1",textView1.getText());
                intent.putExtra("simpleDataKey2",textView2.getText());
                startActivity(intent);
            }
        });
        findViewById(R.id.sendSerializableObject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonEdit.this,Auto.class);
                TextView name = findViewById(R.id.SerializableName);
                TextView age  = findViewById(R.id.SerializableAge);
                intent.putExtra("type","Serializable");
                intent.putExtra("user",new User(name.getText().toString(),Integer.valueOf(age.getText().toString()).intValue()));
                startActivity(intent);
            }
        });

        findViewById(R.id.sendParcelableObject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonEdit.this,Auto.class);
                TextView name = findViewById(R.id.ParcelableName);
                TextView age  = findViewById(R.id.ParcelableAge);
                intent.putExtra("type","Parcelable");
                intent.putExtra("user2",new User2(name.getText().toString(),Integer.valueOf(age.getText().toString()).intValue()));
                startActivity(intent);
            }
        });

        final TextView editText = (TextView) findViewById(R.id.editTextTextMultiLine);
        editText.setText("{\"name\":\"moshuying\",\"url\":\"moshuying.top\",\"email\":\"1460083332@qq\",\"github\":\"moshuying.top\"}");

        findViewById(R.id.sendJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(JsonEdit.this,Auto.class);
                intent.putExtra("type","Json");
                intent.putExtra("data",editText.getText().toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.json_edit_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.sendBundle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonEdit.this,Auto.class);
                intent.putExtra("type","Bundle");
                Bundle bd = new Bundle();
                TextView stringKey = findViewById(R.id.bundleKeyString);
                String string = stringKey.getText().toString();
                bd.putString("String",string);
                TextView stringArray = findViewById(R.id.stringArray);
                bd.putStringArray("StringArray",stringArray.getText().toString().split(","));
                intent.putExtras(bd);
                startActivity(intent);
            }
        });

        findViewById(R.id.getActivityBackData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JsonEdit.this,Auto.class);
                intent.putExtra("type","callback");
                intent.putExtra("request_code",REQUEST_CODE);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(data.getStringExtra("data"));
        if(requestCode == REQUEST_CODE){ // 返回的请求码与当前活动请求码一致时，才执行后继操作
            if(resultCode == RESULT_OK){ // RESULT_OK表示返回的活动已成功处理请求
                Button button = findViewById(R.id.getActivityBackData);
                button.setText("从另一个Activity获取到返回值："+data.getStringExtra("data"));
            }
        }
    }
}
class User implements Serializable{
    private String name;
    private int age;
    public User(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}
}

class User2 implements Parcelable{
    private String name;
    private int age;
    public User2(String name,int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}
    @Override
    public int describeContents(){
        return  0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        // 把对象成员写入dest
        dest.writeString(getName());
        dest.writeInt(getAge());
    }
    public static Creator<User2> CREATOR = new Creator<User2>() {
        @Override
        public User2 createFromParcel(Parcel source) {
            return new User2(source.readString(),source.readInt());
        }
        @Override
        public User2[] newArray(int size) {
            return new User2[size];
        }
    };
}