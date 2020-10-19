package com.example.moshuying.Unit3;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AutoLayout extends AppCompatActivity{
    private LinearLayout linearLayout;
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
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
            case "3.1.3-1": create3131((LinearLayout) createLayout("Linear"));break;
            case "3.1.3-2": create3132((LinearLayout) createLayout("Linear"));break;
            case "3.1.4": create314((RelativeLayout) createLayout("Relative"));break;
            case "3.1.5": create315((FrameLayout) createLayout("Frame"));break;
            case "3.2.3": create323((LinearLayout) createLayout("Linear"));break;
            case "3.2.7": create327((LinearLayout) createLayout("Linear"));break;
            case "3.2.8": create328((LinearLayout) createLayout("Linear"));break;
            default:break;
        }
    }
    public ViewGroup createLayout(String type){
        switch (type){
            case "Linear":return linearLayout;
            case "Relative":
                RelativeLayout relativeLayout = new RelativeLayout(this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
                relativeLayout.setLayoutParams(layoutParams);
                linearLayout.addView(relativeLayout);
                return relativeLayout;
            case "Frame":
                FrameLayout frameLayout = new FrameLayout(this);
                RelativeLayout.LayoutParams framelayoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT);
                frameLayout.setLayoutParams(framelayoutParams);
                linearLayout.addView(frameLayout);
                return frameLayout;
            default: return linearLayout;
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
        int startId = 1000000010;
        EditText editText = new EditText(this);
        editText.setHint("输入用户名");
        editText.setId(startId);
        RelativeLayout.LayoutParams EditlayoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        EditlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        editText.setLayoutParams(EditlayoutParams);
        layout.addView(editText);

        EditText password = new EditText(this);
        password.setHint("输入密码");
        startId+=1;
        password.setId(startId);
        RelativeLayout.LayoutParams PlayoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        PlayoutParams.addRule(RelativeLayout.BELOW,editText.getId());
        password.setLayoutParams(PlayoutParams);
        layout.addView(password);

        MaterialButton button = new MaterialButton(this);
        button.setText("确定");
        RelativeLayout.LayoutParams BlayoutParams = new RelativeLayout.LayoutParams(400,WRAP_CONTENT);
        BlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        BlayoutParams.addRule(RelativeLayout.BELOW,password.getId());
        button.setLayoutParams(BlayoutParams);
        layout.addView(button);

    }
    public void create315(FrameLayout layout){
        MaterialTextView textView  = new MaterialTextView(this);
        textView.setTextSize(40);
        textView.setText("第三层文本视图");
        textView.setTextColor(Color.parseColor("#ff0000"));
        FrameLayout.LayoutParams TlayoutParams = new FrameLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        textView.setLayoutParams(TlayoutParams);
        layout.addView(textView);

        MaterialTextView textView1 = new MaterialTextView(this);
        textView1.setText("第二层文本视图");
        textView1.setTextSize(30);
        textView1.setTextColor(Color.parseColor("#000dff"));
        FrameLayout.LayoutParams T1layoutParams = new FrameLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        T1layoutParams.gravity = Gravity.CENTER;
        textView1.setLayoutParams(T1layoutParams);
        layout.addView(textView1);

        MaterialButton button = new MaterialButton(this);
        button.setText("第一层按钮");
        button.setLayoutParams(TlayoutParams);
        FrameLayout.LayoutParams T2layoutParams = new FrameLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        T2layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM;
        button.setLayoutParams(T2layoutParams);
        layout.addView(button);
    }
    public void create323(LinearLayout layout){
        AutoCompleteTextView autoCompleteTextView = new AutoCompleteTextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        autoCompleteTextView.setLayoutParams(layoutParams);
        String[] select = {"cable","china","Chinese","Check"};
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,select));
        layout.addView(autoCompleteTextView);
    }
    public void create327(LinearLayout layout){
        Spinner spinner = new Spinner(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        spinner.setLayoutParams(layoutParams);

        final MaterialTextView textView = new MaterialTextView(this);
        layout.addView(textView);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_array, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layout.addView(spinner);
    }
    public void create328(LinearLayout layout){
        final ShapeableImageView shapeableImageView = new ShapeableImageView(this);
        shapeableImageView.setImageURI(Uri.parse(""));
        layout.addView(shapeableImageView);
        String res = sendByOKHttp();

        class type{
            String createtime;
            String id;
            String price;
            String title;
            String url;
        }
        ArrayList<type> jsonMap = JSON.parseObject(res,new TypeReference<ArrayList<type>>(){});

        System.out.println(jsonMap.toArray().toString());

        Spinner spinner = new Spinner(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        spinner.setLayoutParams(layoutParams);
        // todo bugs
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.addAll();
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shapeableImageView.setImageURI(Uri.parse(parent.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layout.addView(spinner);
    }
    public static String sendByOKHttp() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        httpGetRequest.url = "https://exampleapp.moshuying.top/node_api/product_list";
        Future<String> submit = executorService.submit(httpGetRequest);
        String data = "";
        try {
            data = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String,String> jsonMap = JSON.parseObject(data,new TypeReference<LinkedHashMap<String, String>>(){});
        return jsonMap.get("data");
    }
}
class HttpGetRequest implements Callable<String> {
    public String url = null;
    @Override
    public String call() {
        String returnValue = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();//发送请求
            returnValue = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}