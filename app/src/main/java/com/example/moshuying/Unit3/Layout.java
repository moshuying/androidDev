package com.example.moshuying.Unit3;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;

public class Layout extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);
        constraintLayout = findViewById(R.id.baseLayout);
        createRelativeLayout();
    }
    protected void createRelativeLayout(){
        // 声明相对布局
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeLayoutLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,500);
        relativeLayout.setLayoutParams(relativeLayoutLayoutParams);
        // 声明编辑文本框
        EditText editText = new EditText(this);
        int id = 1999999001;
        editText.setText("输入用户名");
        editText.setId(id);
        // 获取文本框的布局属性
        RelativeLayout.LayoutParams editTextLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 为文本框设置上边框与父视图的上边框对其
        editTextLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        editText.setLayoutParams(editTextLayoutParams);
        relativeLayout.addView(editText);

        EditText editText2 = new EditText(this);
        int id2 = id+1;
        editText2.setText("输入密码");
        editText2.setId(id2);
        editText2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        RelativeLayout.LayoutParams passwordLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        passwordLayoutParams.addRule(RelativeLayout.BELOW,id);
        editText2.setLayoutParams(passwordLayoutParams);
        relativeLayout.addView(editText2);

        MaterialButton button = new MaterialButton(this);
        button.setText("确定");
        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(300,RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonLayoutParams.addRule(RelativeLayout.BELOW,id2);
        buttonLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        button.setLayoutParams(buttonLayoutParams);
        relativeLayout.addView(button);

        constraintLayout.addView(relativeLayout);
    }
}
