package com.example.moshuying;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class UserInterfaceDesign extends AppCompatActivity {
    public LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        linearLayout = findViewById(R.id.auto_list_item);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        createLinearLayout();
    }
    protected void createLinearLayout(){
//        LinearLayout blockLinearLayout = new LinearLayout(this);
//        blockLinearLayout.setOrientation(LinearLayout.VERTICAL);

//        MaterialTextView materialTextView = new MaterialTextView(this);
//        materialTextView.setText("这是自定义的另一个布局");
//        blockLinearLayout.addView(materialTextView);
//
//        MaterialButton materialButton = new MaterialButton(this);
//        materialButton.setText("设置");
//        blockLinearLayout.addView(materialButton);
//        linearLayout.addView(blockLinearLayout);

        LinearLayout blockLinearLayout2 = new LinearLayout(this);
        blockLinearLayout2.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linearLayoutlayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        blockLinearLayout2.setLayoutParams(linearLayoutlayoutParams);

        MaterialButton materialButton1 = new MaterialButton(this);
        materialButton1.setText("按钮1");
        blockLinearLayout2.addView(materialButton1);

        MaterialButton materialButton2 = new MaterialButton(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1.0f;
        materialButton2.setLayoutParams(layoutParams);
        materialButton2.setText("按钮2");
        blockLinearLayout2.addView(materialButton2);

        MaterialButton materialButton3 = new MaterialButton(this);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.weight = 2.0f;
        materialButton3.setLayoutParams(layoutParams2);
        materialButton3.setText("按钮3");
        blockLinearLayout2.addView(materialButton3);
        linearLayout.addView(blockLinearLayout2);
    }

}
