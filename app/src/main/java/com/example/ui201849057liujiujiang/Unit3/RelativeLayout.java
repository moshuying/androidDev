package com.example.ui201849057liujiujiang.Unit3;

import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui201849057liujiujiang.R;
import com.google.android.material.button.MaterialButton;

public class RelativeLayout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);
        ConstraintLayout constraintLayout = findViewById(R.id.baseLayout);
        // 声明相对布局
        android.widget.RelativeLayout relativeLayout = new android.widget.RelativeLayout(this);
        android.widget.RelativeLayout.LayoutParams relativeLayoutLayoutParams = new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(relativeLayoutLayoutParams);
        // 声明编辑文本框
        EditText editText = new EditText(this);
        int id = 1999999001;
        editText.setText("输入用户名");
        editText.setId(id);
        // 获取文本框的布局属性
        android.widget.RelativeLayout.LayoutParams editTextLayoutParams = new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 为文本框设置上边框与父视图的上边框对其
        editTextLayoutParams.addRule(android.widget.RelativeLayout.ALIGN_PARENT_TOP, android.widget.RelativeLayout.TRUE);
        editText.setLayoutParams(editTextLayoutParams);
        relativeLayout.addView(editText);

        EditText editText2 = new EditText(this);
        int id2 = id+1;
        editText2.setText("输入密码");
        editText2.setId(id2);
        editText2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        android.widget.RelativeLayout.LayoutParams passwordLayoutParams = new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        passwordLayoutParams.addRule(android.widget.RelativeLayout.BELOW,id);
        editText2.setLayoutParams(passwordLayoutParams);
        relativeLayout.addView(editText2);

        MaterialButton button = new MaterialButton(this);
        button.setText("确定");
        android.widget.RelativeLayout.LayoutParams buttonLayoutParams = new android.widget.RelativeLayout.LayoutParams(300, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.addRule(android.widget.RelativeLayout.BELOW,id2);
        buttonLayoutParams.addRule(android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, android.widget.RelativeLayout.TRUE);
        button.setLayoutParams(buttonLayoutParams);
        relativeLayout.addView(button);

        MaterialButton back = new MaterialButton(this);
        back.setText("返回");
        back.setBackgroundColor(getResources().getColor(R.color.back));
        android.widget.RelativeLayout.LayoutParams backLayoutParams = new android.widget.RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        backLayoutParams.addRule(android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM, android.widget.RelativeLayout.TRUE);
        back.setLayoutParams(backLayoutParams);
        relativeLayout.addView(back);

        constraintLayout.addView(relativeLayout);
    }
}
