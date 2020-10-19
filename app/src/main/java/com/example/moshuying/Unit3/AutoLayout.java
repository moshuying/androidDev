package com.example.moshuying.Unit3;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.RelativeLayout;
public class AutoLayout extends AppCompatActivity{
    private LinearLayout linearLayout;
    private int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);

        linearLayout = findViewById(R.id.auto_list_item);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = MATCH_PARENT;
        linearLayout.setLayoutParams(layoutParams);

        Intent intent = getIntent();
        String unit = intent.getStringExtra("unit");
        assert unit != null;
        switch (unit){
            case "3.1.3-1": create3131(linearLayout);break;
            case "3.1.3-2": create3132(linearLayout);break;
            case "3.1.4": create314((RelativeLayout) findViewById(createLayout("Relative")));break;
            default:break;
        }
    }
    public int createLayout(String type){
        switch (type){
            case "Linear":return linearLayout.getId();
            case "Relative":
                RelativeLayout relativeLayout = new RelativeLayout(this);
                RelativeLayout.LayoutParams rParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
                rParams.width = MATCH_PARENT;
                rParams.height = MATCH_PARENT;
                relativeLayout.setLayoutParams(rParams);
                linearLayout.addView(relativeLayout);
                return relativeLayout.getId();
            default: return linearLayout.getId();
        }
    }
    public void create3131(LinearLayout layout){
        MaterialButton button1 = new MaterialButton(this);
        button1.setText("按钮1");
        LinearLayout.LayoutParams b1Params = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        button1.setLayoutParams(b1Params);
        layout.addView(button1);

        MaterialButton button2 = new MaterialButton(this);
        button2.setText("按钮2");
        LinearLayout.LayoutParams b2Params = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        b2Params.weight = 1.0f;
        button2.setLayoutParams(b2Params);
        layout.addView(button2);

        MaterialButton button3 = new MaterialButton(this);
        button3.setText("按钮3");
        LinearLayout.LayoutParams b3Params = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        b3Params.weight = 1.0f;
        button3.setLayoutParams(b3Params);
        layout.addView(button3);
    }
    public void create3132(LinearLayout layout){
        LinearLayout linearLayout1 = new LinearLayout(this);
        LinearLayout.LayoutParams l1Params = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        linearLayout1.setLayoutParams(l1Params);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

        TextInputEditText textInputEditText = new TextInputEditText(this);
        LinearLayout.LayoutParams tieParams= new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        tieParams.weight = 1.0f;
        textInputEditText.setLayoutParams(tieParams);
        textInputEditText.setText("收件人");
        linearLayout1.addView(textInputEditText);

        MaterialButton button = new MaterialButton(this);
        LinearLayout.LayoutParams buttonParams= new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        button.setLayoutParams(buttonParams);
        button.setText("发送");
        linearLayout1.addView(button);
        layout.addView(linearLayout1);

        EditText editText = new EditText(this);
        editText.setText("短信内容");
        layout.addView(editText);
    }
    public void create314(RelativeLayout layout){
        EditText editText = new EditText(this);

    }
}