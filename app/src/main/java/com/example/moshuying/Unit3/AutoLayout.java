package com.example.moshuying.Unit3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AutoLayout extends AppCompatActivity{
    private LinearLayout linearLayout;
    private Intent intent;
    private Menu ClassMenu;
    private String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);

        linearLayout = findViewById(R.id.auto_list_item);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = MATCH_PARENT;
        linearLayout.setLayoutParams(layoutParams);

        intent = getIntent();
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
            case "3.2.9": create329((LinearLayout) createLayout("Linear"));break;
            case "3.2.10": create3210((LinearLayout) createLayout("Linear"));break;
            case "3.3.1": create331((LinearLayout) createLayout("Linear"));break;
            case "3.3.2": create332((LinearLayout) createLayout("Linear"));break;
            case "3.4.1": create341((LinearLayout) createLayout("Linear"));break;
            case "3.4.2": create342((LinearLayout) createLayout("Linear"));break;
            case "3.4.3": create343((LinearLayout) createLayout("Linear"));break;
            case "3.4.4": create344((LinearLayout) createLayout("Linear"));break;
            case "3.5": create35((LinearLayout) createLayout("Linear"));break;
            case "3.6.1": create361((LinearLayout) createLayout("Linear"));break;
            case "3.6.2": create362((LinearLayout) createLayout("Linear"));break;
            case "3.6.3": create363((LinearLayout) createLayout("Linear"));break;
            case "3.7.1": create371((LinearLayout) createLayout("Linear"));break;
            case "3.7.2": create372((LinearLayout) createLayout("Linear"));break;
            case "3.7.3": create373((LinearLayout) createLayout("Linear"));break;
            case "3.7.3-2": create3732((LinearLayout) createLayout("Linear"));break;
            case "3.7.4": create374((LinearLayout) createLayout("Linear"));break;
            case "3.8": create38((LinearLayout) createLayout("Linear"));break;
            case "3.8-2": create382((LinearLayout) createLayout("Linear"));break;
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
        final MyImageView shapeableImageView = new MyImageView(this);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(MATCH_PARENT,500);
        shapeableImageView.setLayoutParams(layoutParams1);
        layout.addView(shapeableImageView);

        Spinner spinner = new Spinner(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        spinner.setLayoutParams(layoutParams);
        Object[] array = JSON.parseArray(sendByOKHttp()).toArray();

        final CharSequence[] strings = new CharSequence[array.length];
        for(int i =0 ;i<array.length;i++){
            strings[i] = JSON.parseObject(array[i].toString()).get("url").toString();
        }

        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item,0, Arrays.asList(strings));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shapeableImageView.setImageURL(strings[position].toString());
                System.out.println("onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                shapeableImageView.setImageURL(strings[0].toString());
                System.out.println("onNothingSelected");
            }
        });
        layout.addView(spinner);
    }
    public void create329(LinearLayout layout){
        layout.addView(AddMinTitle("默认样式的进度条",18));
        ProgressBar progressBar = new ProgressBar(this);
        layout.addView(progressBar);
        layout.addView(AddMinTitle("大图标的圆环进度条",18));
        ProgressBar progressBar1 = new ProgressBar(this,null,android.R.attr.progressBarStyleLarge);
        layout.addView(progressBar1);
        layout.addView(AddMinTitle("小图标的圆环进度条",18));
        ProgressBar progressBar2 = new ProgressBar(this,null,android.R.attr.progressBarStyleSmall);
        layout.addView(progressBar2);
        layout.addView(AddMinTitle("水平样式环进度条",18));
        ProgressBar progressBar3 = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
        layout.addView(progressBar3);
    }
    public void create3210(LinearLayout layout){
        layout.addView(AddMinTitle("拖动条",18));
        SeekBar seekBar = new SeekBar(this);
        seekBar.setMax(100);
        final MaterialTextView textView = new MaterialTextView(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("当前拖动条值："+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        layout.addView(seekBar);
        layout.addView(textView);
    }
    @SuppressLint("SetTextI18n")
    public void create331(LinearLayout layout){
        layout.addView(AddMinTitle("使用Toast",18));
        MaterialButton shortButton = new MaterialButton(this);
        shortButton.setText("显示较短时间的Toast");
        shortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast tost = Toast.makeText(AutoLayout.this,"这是一个较短时间的Toast",Toast.LENGTH_SHORT);
                tost.show();
            }
        });
        layout.addView(shortButton);

        MaterialButton longButton = new MaterialButton(this);
        longButton.setText("显示较长时间的Toast");
        longButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast tost = Toast.makeText(AutoLayout.this,"这是一个较长时间的Toast",Toast.LENGTH_LONG);
                tost.show();
            }
        });
        layout.addView(longButton);

        MaterialButton customButton = new MaterialButton(this);
        customButton.setText("显示自定义视图的Toast");
        customButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                final Toast tost = new Toast(AutoLayout.this);
                LinearLayout linearLayout = new LinearLayout(AutoLayout.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                final ImageView imageView = new ImageView(AutoLayout.this);
                linearLayout.addView(imageView);

                @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case 1:
                                Bitmap bitmap = (Bitmap) msg.obj;
                                imageView.setImageBitmap(bitmap);
                                tost.show();
                                break;
                            case 2:
                                Toast.makeText(AutoLayout.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(AutoLayout.this,"服务器发生错误",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };
                //开启一个线程用于联网
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            //把传过来的路径转成URL
                            URL url = new URL("https://exampleapp.liujiujiang.top/assets/images/book.png");
                            //获取连接
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            //使用GET方法访问网络
                            connection.setRequestMethod("GET");
                            //超时时间为10秒
                            connection.setConnectTimeout(10000);
                            //获取返回码
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream inputStream = connection.getInputStream();
                                //使用工厂把网络的输入流生产Bitmap
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                //利用Message把图片发给Handler
                                Message msg = Message.obtain();
                                msg.obj = bitmap;
                                msg.what = 1;
                                handler.sendMessage(msg);
                                inputStream.close();
                            }else {
                                //服务启发生错误
                                handler.sendEmptyMessage(3);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            //网络连接错误
                            handler.sendEmptyMessage(2);
                        }
                    }
                }.start();

                MaterialTextView textView = new MaterialTextView(AutoLayout.this);
                textView.setText("这是一个自定义视图的Toast");
                tost.setView(linearLayout);
                tost.setGravity(Gravity.CENTER_VERTICAL,0,0);
                linearLayout.addView(textView);
            }
        });
        layout.addView(customButton);
    }
    public void create332(LinearLayout layout){
        layout.addView(AddMinTitle("使用Notification",18));
        MaterialButton button = new MaterialButton(this);
        layout.addView(button);
        button.setText("创建一个Notification");
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String id ="channel_1";//channel的id
                String description = "123";//channel的描述信息
                int importance = NotificationManager.IMPORTANCE_LOW;//channel的重要性
                NotificationChannel channel = new NotificationChannel(id, "123", importance);//生成channel
                //为channel添加属性
                channel.enableVibration(true); //震动
                channel.enableLights(true);//提示灯
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                manager.createNotificationChannel(channel);//添加channel
                Notification notification = new Notification.Builder(AutoLayout.this,id)
                        //注意这里多了一个参数id，指配置的NotificationChannel的id
                        //你可以自己去试一下 运行一次后 即配置完后 将这行代码以上的代
                        //码注释掉 将参数id直接改成“channel_1”也可以成功运行
                        //但改成别的如“channel_2”就不行了
                        .setCategory(Notification.CATEGORY_MESSAGE)
                        .setSmallIcon(R.mipmap.logo)
                        .setContentTitle("(｡･∀･)ﾉﾞ嗨， 你有一个新消息")
                        .setContentText("你已经学会创建Notification了")
//                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void create341(LinearLayout layout){
        layout.addView(AddMinTitle("Alert Dialog",18));
        MaterialButton button = new MaterialButton(AutoLayout.this);
        layout.addView(button);
        button.setText("显示一个Alert Dialog");
        final MaterialTextView textView = new MaterialTextView(this);
        layout.addView(textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AutoLayout.this);
                dialog.setTitle("这是一个Alert Dialog");
                dialog.setMessage("对话框的详细信息：请选择\"取消\"还是\"确认\"");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("你选择了确认");
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("你选择了取消");
                    }
                });
                dialog.setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("你选择了中立");
                    }
                });

                dialog.show();
            }
        });
    }
    public void create342(LinearLayout layout){
        layout.addView(AddMinTitle("ProgressDialog",18));
        MaterialButton button = new MaterialButton(this);
        button.setText("打开进度条对话框");
        layout.addView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(AutoLayout.this);
                dialog.setTitle("这是一个进度条对话框");// 设置标题
                dialog.setMessage("请耐心等待，正在处理数据.....");
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void create343(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        MaterialButton button = new MaterialButton(this);
        layout.addView(button);
        button.setText("启动"+intent.getStringExtra("title"));
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AutoLayout.this,new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        TextView textView = new TextView(AutoLayout.this);
                        textView.setText(String.format("你选择的日期：%d年%d月%d日",year,month,dayOfMonth));
                    }
                },2020,11,6);
                datePickerDialog.show();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void create344(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        MaterialButton button = new MaterialButton(this);
        layout.addView(button);
        button.setText("启动"+intent.getStringExtra("title"));
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AutoLayout.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        TextView textView = new TextView(AutoLayout.this);
                        textView.setText(String.format("你选择的时间：%d:%d",hourOfDay,minute));
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });
    }
    public void create35(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        MaterialButton button = new MaterialButton(this);
        layout.addView(button);
        button.setText("启动菜单项");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoLayout.this.onCreateOptionsMenu(ClassMenu);
            }
        });
    }
    public void create361(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        layout.addView(listView);
    }
    class Book{
        private int picId;
        private String name;
        Book(String name,int picId){
            this.picId = picId;
            this.name = name;
        }
        public String getName() { return name; }
        public int getPicId() { return picId; }
    }
    class BookAdapter extends ArrayAdapter<Book>{
        public int resId;
        public BookAdapter(Context context, int resource, List<Book> objects){
            super(context,resource,objects);
            resId = resource;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Book book = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resId,parent,false);
            TextView bookname = new TextView(getContext());
            bookname.setText(book.getName());
            bookname.setGravity(Gravity.CENTER_VERTICAL);
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(book.getPicId());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
            imageView.setLayoutParams(layoutParams);
            LinearLayout linearLayout = view.findViewById(R.id.auto_list_item);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.addView(imageView);
            linearLayout.addView(bookname);
            return view;
        }
    }
    private List<Book> bookList;
    public ListView create362(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        bookList = new ArrayList<Book>();
        String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
        bookList.add(new Book(data[0],R.drawable.ic_chemistry));
        bookList.add(new Book(data[1],R.drawable.ic_education_forum));
        bookList.add(new Book(data[2],R.drawable.ic_learning_material));

        BookAdapter adapter = new BookAdapter(AutoLayout.this,R.layout.auto,bookList);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        layout.addView(listView);
        return listView;
    }
    public void create363(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        ListView listView = create362(layout);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = bookList.get(position);
                Toast.makeText(AutoLayout.this,book.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void create371(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        RecyclerView recyclerView = new RecyclerView(this);
        layout.addView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView itemViewHolder;
                public ItemViewHolder(TextView textView){
                    super(textView);
                    itemViewHolder = textView;
                }
                public TextView getItemViewHolder(){
                    return itemViewHolder;
                }
            }
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new ItemViewHolder(new TextView(viewGroup.getContext()));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                itemViewHolder.getItemViewHolder().setText(i+":"+data[i%3]);
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
    }
    public RecyclerView create372(final LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        class BookAdpater extends RecyclerView.Adapter<BookAdpater.ItemViewHolder>{
            private List<Book> bookList;
             class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView bookName;
                private ImageView bookPic;
                public ItemViewHolder(View itemView){
                    super(itemView);
                    bookName = itemView.findViewById(R.id.title);
                    bookPic = itemView.findViewById(R.id.imageView);
                }
            }
            public BookAdpater(List<Book> bookList){
                 this.bookList = bookList;
            }
            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unti3_7_2,parent,false);
                 return new ItemViewHolder(view);
            }
            @Override
            public void onBindViewHolder(ItemViewHolder holder,int positon){
                 Book book = bookList.get(positon);
                 holder.bookName.setText(book.getName());
                 holder.bookPic.setImageResource(book.getPicId());
            }
            @Override
            public int getItemCount(){
                 return bookList.size();
            }
        }
        bookList = new ArrayList<Book>();
        String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
        for(int i=0;i<30;i++){
            bookList.add(new Book(data[0],R.drawable.ic_chemistry));
            bookList.add(new Book(data[1],R.drawable.ic_education_forum));
            bookList.add(new Book(data[2],R.drawable.ic_learning_material));
        }
        RecyclerView recyclerView = new RecyclerView(this);
        layout.addView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BookAdpater(bookList));
        return recyclerView;
    }
    public RecyclerView create373(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        class BookAdpater extends RecyclerView.Adapter<BookAdpater.ItemViewHolder>{
            private List<Book> bookList;
            class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView bookName;
                private ImageView bookPic;
                public ItemViewHolder(View itemView){
                    super(itemView);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(300, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.orientation = LinearLayout.VERTICAL;
                    itemView.setLayoutParams(layoutParams);

                    bookPic = itemView.findViewById(R.id.imageView);
                    bookName = itemView.findViewById(R.id.title);
                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams1.topToBottom = R.id.imageView;
                    bookName.setLayoutParams(layoutParams1);
                    bookName.setGravity(Gravity.CENTER_VERTICAL);

//                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                }
            }
            public BookAdpater(List<Book> bookList){
                this.bookList = bookList;
            }
            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unti3_7_2,parent,false);
                return new ItemViewHolder(view);
            }
            @Override
            public void onBindViewHolder(ItemViewHolder holder,int positon){
                Book book = bookList.get(positon);
                holder.bookName.setText(book.getName());
                holder.bookPic.setImageResource(book.getPicId());
            }
            @Override
            public int getItemCount(){
                return bookList.size();
            }
        }
        bookList = new ArrayList<Book>();
        String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
        for(int i=0;i<30;i++){
            bookList.add(new Book(data[0],R.drawable.ic_chemistry));
            bookList.add(new Book(data[1],R.drawable.ic_education_forum));
            bookList.add(new Book(data[2],R.drawable.ic_learning_material));
        }
        RecyclerView recyclerView = new RecyclerView(this);
        layout.addView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new BookAdpater(bookList));
        return recyclerView;
    }
    public void create3732(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        class BookAdpater extends RecyclerView.Adapter<BookAdpater.ItemViewHolder>{
            private List<Book> bookList;
            class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView bookName;
                private ImageView bookPic;
                public ItemViewHolder(View itemView){
                    super(itemView);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(300, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.orientation = LinearLayout.VERTICAL;
                    itemView.setLayoutParams(layoutParams);

                    bookPic = itemView.findViewById(R.id.imageView);
                    bookName = itemView.findViewById(R.id.title);
                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams1.topToBottom = R.id.imageView;
                    bookName.setLayoutParams(layoutParams1);
                    bookName.setGravity(Gravity.CENTER_VERTICAL);

//                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                }
            }
            public BookAdpater(List<Book> bookList){
                this.bookList = bookList;
            }
            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unti3_7_2,parent,false);
                return new ItemViewHolder(view);
            }
            @Override
            public void onBindViewHolder(ItemViewHolder holder,int positon){
                Book book = bookList.get(positon);
                holder.bookName.setText(book.getName());
                holder.bookPic.setImageResource(book.getPicId());
            }
            @Override
            public int getItemCount(){
                return bookList.size();
            }
        }
        bookList = new ArrayList<Book>();
        String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
        for(int i=0;i<30;i++){
            bookList.add(new Book(data[0],R.drawable.ic_chemistry));
            bookList.add(new Book(data[1],R.drawable.ic_education_forum));
            bookList.add(new Book(data[2],R.drawable.ic_learning_material));
        }
        RecyclerView recyclerView = new RecyclerView(this);
        layout.addView(recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new BookAdpater(bookList));
    }
    public void create374(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        class BookAdpater extends RecyclerView.Adapter<BookAdpater.ItemViewHolder>{
            private List<Book> bookList;
            class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView bookName;
                private ImageView bookPic;
                public ItemViewHolder(View itemView){
                    super(itemView);
                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(300, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.orientation = LinearLayout.VERTICAL;
                    itemView.setLayoutParams(layoutParams);

                    bookPic = itemView.findViewById(R.id.imageView);
                    bookName = itemView.findViewById(R.id.title);
                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams1.topToBottom = R.id.imageView;
                    bookName.setLayoutParams(layoutParams1);
                    bookName.setGravity(Gravity.CENTER_VERTICAL);

//                    ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                }
            }
            public BookAdpater(List<Book> bookList){
                this.bookList = bookList;
            }
            @Override
            public ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unti3_7_2,parent,false);
                final ItemViewHolder itemViewHolder =new ItemViewHolder(view);
                itemViewHolder.bookPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int index = itemViewHolder.getAdapterPosition();
                        Book book = bookList.get(index);
                        Toast.makeText(v.getContext(),"你单击了: "+book.getName()+"的图片",Toast.LENGTH_LONG).show();
                    }
                });
                itemViewHolder.bookName.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int index = itemViewHolder.getAdapterPosition();
                        Book book = bookList.get(index);
                        Toast.makeText(v.getContext(),"你单击了: "+book.getName()+"的书名",Toast.LENGTH_LONG).show();
                    }
                });
                return itemViewHolder;
            }
            @Override
            public void onBindViewHolder(ItemViewHolder holder,int positon){
                Book book = bookList.get(positon);
                holder.bookName.setText(book.getName());
                holder.bookPic.setImageResource(book.getPicId());
            }
            @Override
            public int getItemCount(){
                return bookList.size();
            }
        }
        bookList = new ArrayList<Book>();
        String[] data = {"使用Android Studio环境","Android Studio 实战","Android 编程权威指南"};
        for(int i=0;i<30;i++){
            bookList.add(new Book(data[0],R.drawable.ic_chemistry));
            bookList.add(new Book(data[1],R.drawable.ic_education_forum));
            bookList.add(new Book(data[2],R.drawable.ic_learning_material));
        }
        RecyclerView recyclerView = new RecyclerView(this);
        layout.addView(recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new BookAdpater(bookList));
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View[] create38View(LinearLayout layout){
        int MATCH_PARENT=RelativeLayout.LayoutParams.MATCH_PARENT,WRAP_CONTENT=RelativeLayout.LayoutParams.WRAP_CONTENT;
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams RLayoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        RLayoutParams.setMargins(10,170,10,0);
        relativeLayout.setLayoutParams(RLayoutParams);
        layout.addView(relativeLayout);

        final EditText username = new EditText(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        layoutParams.topMargin=50;
        username.setLayoutParams(layoutParams);
        username.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        username.setId(View.generateViewId());
        username.setHint("请输入用户名");
        relativeLayout.addView(username);

        final EditText password = new EditText(this);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        layoutParams1.topMargin = 5;
        layoutParams1.addRule(RelativeLayout.BELOW,username.getId());
        password.setLayoutParams(layoutParams1);
        password.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        password.setId(View.generateViewId());
        password.setHint("请输入密码");
        relativeLayout.addView(password);

        final MaterialTextView textView = new MaterialTextView(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        layoutParams2.topMargin = 5;
        layoutParams2.addRule(RelativeLayout.BELOW,password.getId());
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(layoutParams2);
        textView.setId(View.generateViewId());
        textView.setTextColor(Color.parseColor("#FF0000"));
        relativeLayout.addView(textView);

        MaterialButton button = new MaterialButton(this);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        layoutParams3.setMargins(20,50,20,0);
        layoutParams3.addRule(RelativeLayout.BELOW,password.getId());
        button.setText("登录");
        button.setLayoutParams(layoutParams3);
        relativeLayout.addView(button);
        return new View[]{username,password,textView,button};
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void create38(LinearLayout layout){
        View[] views = create38View(layout);
        final EditText username =(EditText) views[0];
        final EditText password = (EditText) views[1];
        final MaterialTextView textView = (MaterialTextView) views[2];
        MaterialButton button =(MaterialButton) views[3];
        // 布局结束
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { textView.setText(""); }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { textView.setText(""); }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNmae = username.getText().toString();
                String passWord = password.getText().toString();
                String[] u = {"Admin","Administrator","Android"};
                String[] p = {"123","12345","123456"};
                int i=0;
                for(;i<u.length;i++){
                    if(userNmae.equals(u[i])){
                        if(passWord.equals(p[i])){
                            textView.setTextColor(Color.parseColor("#00FF00"));
                            textView.setText("用户名和密码正确，登录成功！");
                            return;
                        }
                    }
                }
                textView.setTextColor(Color.parseColor("#FF0000"));
                textView.setText("用户名或密码错误，请重新登录！");
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void create382(LinearLayout layout){
        View[] views = create38View(layout);
        final EditText username =(EditText) views[0];
        final EditText password = (EditText) views[1];
        MaterialButton button =(MaterialButton) views[3];
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AutoLayout.this);
                dialog.setTitle("登录提示信息");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                String userNmae = username.getText().toString();
                String passWord = password.getText().toString();
                String[] u = {"Admin","Administrator","Android"};
                String[] p = {"123","12345","123456"};
                dialog.setMessage("用户名或密码错误，请重新登录！");
                for(int i=0;i<u.length;i++){
                    if(userNmae.equals(u[i])&&passWord.equals(p[i])){
                        dialog.setMessage("用户名和密码正确，登录成功！");
                        break;
                    }
                }
                dialog.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if(ClassMenu==null){
            ClassMenu = menu;
            return super.onCreateOptionsMenu(menu);
        }
        MenuItem menuItem = ClassMenu.add(1,100,1,"菜单项");
        menuItem.setTitle("我是一个菜单");
        menuItem.setIcon(R.mipmap.logo);
        menu.add(1,100,1,"菜单项1");
        menu.add(1,101,1,"菜单项2");
        return  true;
    }
    public static String sendByOKHttp() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        httpGetRequest.url = "https://exampleapp.liujiujiang.top/node_api/product_list";
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
class MyImageView extends androidx.appcompat.widget.AppCompatImageView {
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int SERVER_ERROR = 3;
    //子线程不能操作UI，通过Handler设置图片
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_DATA_SUCCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    setImageBitmap(bitmap);
                    break;
                case NETWORK_ERROR:
                    Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
                case SERVER_ERROR:
                    Toast.makeText(getContext(),"服务器发生错误",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置网络图片
    public void setImageURL(final String path) {
        //开启一个线程用于联网
        new Thread() {
            @Override
            public void run() {
                try {
                    //把传过来的路径转成URL
                    URL url = new URL(path);
                    //获取连接
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //使用GET方法访问网络
                    connection.setRequestMethod("GET");
                    //超时时间为10秒
                    connection.setConnectTimeout(10000);
                    //获取返回码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        //使用工厂把网络的输入流生产Bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        //利用Message把图片发给Handler
                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        msg.what = GET_DATA_SUCCESS;
                        handler.sendMessage(msg);
                        inputStream.close();
                    }else {
                        //服务启发生错误
                        handler.sendEmptyMessage(SERVER_ERROR);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //网络连接错误
                    handler.sendEmptyMessage(NETWORK_ERROR);
                }
            }
        }.start();
    }

}