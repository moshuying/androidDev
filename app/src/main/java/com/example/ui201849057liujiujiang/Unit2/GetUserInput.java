package com.example.ui201849057liujiujiang.Unit2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ui201849057liujiujiang.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

public class GetUserInput extends AppCompatActivity {
    public LinearLayout linearLayout;
    public MaterialTextView userInput;
    public MaterialTextView textView;
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        textView = new MaterialTextView(this);
        textView.setText("这是默认布局");
        linearLayout.addView(textView);

        MaterialButton button = new MaterialButton(this);
        button.setText("设置");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        linearLayout.addView(button);

        userInput = new MaterialTextView(this);
        userInput.setText("");
        linearLayout.addView(userInput);
        addBack();
    }
    protected void createDialog(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("布局设置");
        builder.setIcon(R.drawable.ic_learning_material);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText editText = new EditText(this);builder.setView(editText);
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText("请在下面输入1、2或其他数据");

        layout.addView(textView);
        layout.addView(editText);

        builder.setView(layout);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeLayout(editText.getText().toString());
//                finish(); // 这里无需finish()点击取消什么的会自己关闭掉自己
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    protected void changeLayout(String input){
        userInput.setText(String.format("用户输入的是%s,设置为默认布局%s",input,input));
        if(input.equals("1")){
            textView.setText("这是默认布局");
        }
        if(input.equals("2")){
            textView.setText("这是自定义的另一个布局");
        }
    }

    public void addBack(){
        MaterialButton button = new MaterialButton(this);
        button.setBackgroundColor(getResources().getColor(R.color.back));
        button.setText("返回上一级");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linearLayout.addView(button);
    }
}
