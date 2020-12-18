package com.example.moshuying.Unit5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.telephony.emergency.EmergencyNumber;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.moshuying.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class AutoLayout extends AppCompatActivity{
    private Intent intent;
    private LinearLayout layout=null;
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        layout = findViewById(R.id.auto_list_item);
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        layoutParams.height = MATCH_PARENT;
        layout.setLayoutParams(layoutParams);

        intent = getIntent();
        String unit = intent.getStringExtra("unit");
        assert unit != null;
        switch (unit){
            case "5.1.1": create511();break;
            case "5.1.2": create512();break;
            case "5.1.3": create513();break;
            case "5.1.4": create514();break;
            case "5.2.3": create523(layout);break;
            case "5.4": create54(layout);break;
            default:break;
        }
    }
    class MyDataReaderTEST{
        public static final String SD_CARD="SD_CARD";//SD卡
        public static final String PRIVATE_FILE="PRIVATE_FILE";//应用私有文件
        public static final String APP_DIR="APP_DIR";//访问内部存储目录
        public static final String PUBLIC_DIR="PUBLIC_DIR";//访问公共目录
        private File dir = null;
        private File mf = null;
        // 检测存储卡是否可读
        private boolean isReadable(){
            String state = Environment.getExternalStorageState();
            return state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        }
        //检测存储卡是否可写
        private boolean isWriteable(){
            String state = Environment.getExternalStorageState();
            return state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        }
        private TextInputEditText f_name;
        private EditText f_data;
        private TextView show;
        @SuppressLint("SetTextI18n")
        public void createView(){
            LinearLayout linearLayout1 = new LinearLayout(AutoLayout.this);
            LinearLayout.LayoutParams l1Params = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
            linearLayout1.setLayoutParams(l1Params);
            linearLayout1.setOrientation(LinearLayout.HORIZONTAL);

            EditText editText = new EditText(AutoLayout.this);
            f_data=editText;
            editText.setText("测试文件内容");
            layout.addView(editText);

            TextInputEditText textInputEditText = new TextInputEditText(AutoLayout.this);
            f_name=textInputEditText;
            LinearLayout.LayoutParams tieParams= new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
            tieParams.weight = 1.0f;
            textInputEditText.setLayoutParams(tieParams);
            textInputEditText.setText("myFile.txt");
            linearLayout1.addView(textInputEditText);

            MaterialButton button = new MaterialButton(AutoLayout.this);
            LinearLayout.LayoutParams buttonParams= new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
            button.setLayoutParams(buttonParams);
            button.setText("创建文件");

            linearLayout1.addView(button);
            MaterialTextView textView = new MaterialTextView(AutoLayout.this);
            show=textView;

            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    handle("w");
                }
            });
            layout.addView(linearLayout1);
            layout.addView(textView);

            MaterialButton button1 = new MaterialButton(AutoLayout.this);
            button1.setText("读取文件\n（可以自行新增文件到"+translate()+"目录下）");
            button1.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT));

            button1.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    handle("r");
                }
            });
            layout.addView(button1);
        }

        private String TYPE;
        public MyDataReaderTEST(String type){
            TYPE = type;
            createView();
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void handle(String rw){
            switch (TYPE){
                case SD_CARD:sdcard(rw);break;
                case PRIVATE_FILE:privatefile(rw);break;
                case APP_DIR:appdir(rw);break;
                case PUBLIC_DIR:publicdir(rw);break;
                default:break;
            }
        }
        private void appdir(String rw){
            dir=null;
            mf =null;
            rwHandle(rw);
        }
        private void sdcard(String rw){
            dir = Environment.getExternalStorageDirectory();
            mf = new File(dir,f_name.getText().toString());
            if(rw.equals("w")){
                if(!isWriteable()){
                    show.setText("SD卡不可用");
                    return;
                }
            }else {
                if(!isReadable()){
                    show.setText("SD卡不可读");
                    return;
                }
            }
            rwHandle(rw);
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void privatefile(String rw){
            dir = getExternalFilesDir(null);
            mf = new File(dir, Objects.requireNonNull(f_name.getText()).toString());
            rwHandle(rw);
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void publicdir(String rw){
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            mf = new File(dir, Objects.requireNonNull(f_name.getText()).toString());
            if(rw.equals("w")){
                if(!isWriteable()){
                    show.setText("SD卡不可用");
                    return;
                }
            }else {
                if(!isReadable()){
                    show.setText("SD卡不可读");
                    return;
                }
            }
            rwHandle(rw);
        }
        private void rwHandle(String rw){
            if(rw.equals("r")){
                r();
            } else {
                w();
            }
        }
        private String translate(){
            switch (TYPE){
                case SD_CARD:return "SD卡";
                case PRIVATE_FILE:return "应用私有文件";
                case APP_DIR:return "内部存储目录";
                case PUBLIC_DIR:return "公共目录";
            }
            return "内部存储文件";
        }
        @SuppressLint("SetTextI18n")
        private void r(){
            String FILE_NAME = f_name.getText().toString();
            FileInputStream fis ;
            try {
                if(mf==null){
                    fis = openFileInput(FILE_NAME);
                }else{
                    fis = new FileInputStream(mf);
                }
                InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
                char[] data2 = new char[fis.available()];
                isr.read(data2);
                isr.close();
                fis.close();
                show.setText(translate()+"文件内容为："+new String(data2));
            }catch (Exception e){
                e.printStackTrace();
                show.setText(translate()+"文件读取失败");
            }
        }
        @SuppressLint("SetTextI18n")
        private void w(){
            String DATA = f_data.getText().toString();
            String FILE_NAME = f_name.getText().toString();
            FileOutputStream fos;
            try{
                if(mf==null){
                    fos= openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                }else{
                    fos = new FileOutputStream(mf);
                }
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(DATA);
                osw.flush();
                fos.flush();
                osw.close();
                fos.close();
                show.setText(translate()+"文件写入完成");
            }catch (Exception e){
                e.printStackTrace();
                show.setText(translate()+"文件写入失败");
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private void create511(){
        AutoAddTitle();
        layout.addView(AddMinTitle("tips:默认情况下，保存大内部存储器中的文件是当前应用的私有文件，其他应用或用户不能访问，在卸载应用时，文件也会随之删除。",12));
        new MyDataReaderTEST(MyDataReaderTEST.APP_DIR);
    }
    private void create512(){
        AutoAddTitle();
        new MyDataReaderTEST(MyDataReaderTEST.SD_CARD);
    }
    private void create513(){
        AutoAddTitle();
        new MyDataReaderTEST(MyDataReaderTEST.PRIVATE_FILE);
    }
    private void create514(){
        AutoAddTitle();
        new MyDataReaderTEST(MyDataReaderTEST.PUBLIC_DIR);
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

        button.setOnClickListener(v -> {
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
        button.setOnClickListener(v -> {
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
    private void AutoAddTitle(){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
    }
}