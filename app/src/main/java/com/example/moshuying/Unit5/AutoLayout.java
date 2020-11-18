package com.example.moshuying.Unit5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import android.widget.TextView;
import android.widget.Toast;

public class AutoLayout extends AppCompatActivity{
    private Intent intent;
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        LinearLayout layout = findViewById(R.id.auto_list_item);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.height = MATCH_PARENT;
        layout.setLayoutParams(layoutParams);

        intent = getIntent();
        String unit = intent.getStringExtra("unit");
        assert unit != null;
        switch (unit){
            case "5.2.3": create523(layout);break;
            case "5.4": create54(layout);break;
            default:break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void create523(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        final EditText username = makeLineLinearLayout(layout,"用户名");
        final EditText password = makeLineLinearLayout(layout,"密  码");
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText("记住密码");
        MaterialButton button = new MaterialButton(this);
        button.setText("登录");
        layout.addView(checkBox);
        layout.addView(button);

        // 存储
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemembered = pref.getBoolean("remembered",false);
        final SharedPreferences.Editor editor = pref.edit();
        if(isRemembered){
            username.setText(pref.getString("username",""));
            password.setText(pref.getString("password",""));
            checkBox.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(username.getText().toString().equals("admin")&&password.getText().toString().equals("123"))){
                    Toast.makeText(AutoLayout.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkBox.isChecked()){
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.putBoolean("remembered",true);
                    Toast.makeText(AutoLayout.this,"登录信息已保存！",Toast.LENGTH_SHORT).show();
                }else {
                    editor.clear();
                }
                editor.apply();
                Toast.makeText(AutoLayout.this,"登录成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public EditText makeLineLinearLayout(LinearLayout layout, String title){
        LinearLayout line = new LinearLayout(this);
        line.setOrientation(LinearLayout.HORIZONTAL);
        MaterialTextView materialTextView = new MaterialTextView(this);
        materialTextView.setText(title+":");
        materialTextView.setTextSize(20);
        LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        layoutParam.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        materialTextView.setLayoutParams(layoutParam);
        line.addView(materialTextView);

        EditText editText = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        layoutParams.weight = 1;
        editText.setLayoutParams(layoutParams);
        line.addView(editText);
        layout.addView(line);
//        editText.setId(View.generateViewId());
        return editText;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void create54(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        final EditText username = makeLineLinearLayout(layout,"用户名");
        final EditText password = makeLineLinearLayout(layout,"密  码");
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText("记住密码");
        MaterialButton button = new MaterialButton(this);
        button.setText("登录");
        layout.addView(checkBox);
        layout.addView(button);

        SQLiteOpenHelper sqLiteHelper = new MySQLiteHelper(this,"userdb.db",null,1);
        final SQLiteDatabase myDb = sqLiteHelper.getWritableDatabase();// 创建或打开数据库
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = pref.edit();
        boolean isRemembered = pref.getBoolean("remembered",false);
        if(isRemembered){
            Cursor c = myDb.rawQuery("select * from users where remembered=1",null);
            if(c.moveToFirst()){
                username.setText(c.getString(c.getColumnIndex("userid")));
                password.setText(c.getString(c.getColumnIndex("password")));
                checkBox.setChecked(true);
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = username.getText().toString();
                String pwd = password.getText().toString();
//                    Cursor c = myDb.rawQuery("select * from users where userid=? and password=?",new String[]{id,pwd});
                Cursor c = myDb.query("users",new String[]{"userid"},"userid=? and password=?",new String[]{id,pwd},null,null,null);
                if(!c.moveToFirst()){// 登录信息错误
                    Toast.makeText(AutoLayout.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkBox.isChecked()){
                    myDb.execSQL("update users set remembered=1 where userid=?",new String[]{id});
//                        myDb.execSQL("update users set remembered=0 where userid<>?",new String[]{id});
                    editor.putBoolean("remembered",true);
                }else{
                    myDb.execSQL("update users set remembered=0");
                    editor.clear();
                }
                editor.apply();
                Toast.makeText(AutoLayout.this,"登录成功！",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public TextView AddMinTitle(String title,int size){
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText(title);
        textView.setTextSize(size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}