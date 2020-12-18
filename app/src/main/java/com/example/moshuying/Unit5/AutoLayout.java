package com.example.moshuying.Unit5;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
            case "5.3": create53();break;
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
            return !state.equals(Environment.MEDIA_MOUNTED) && !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
        }
        //检测存储卡是否可写
        private boolean isWriteable(){
            String state = Environment.getExternalStorageState();
            return !state.equals(Environment.MEDIA_MOUNTED) && !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
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
                case PRIVATE_FILE:
                    privateFile(rw);break;
                case APP_DIR:
                    appDir(rw);break;
                case PUBLIC_DIR:
                    publicDir(rw);break;
                default:break;
            }
        }
        private void appDir(String rw){
            dir=null;
            mf =null;
            rwHandle(rw);
        }
        @SuppressLint("SetTextI18n")
        private void sdcard(String rw){
            dir = Environment.getExternalStorageDirectory();
            mf = new File(dir, Objects.requireNonNull(f_name.getText()).toString());
            if(rw.equals("w")){
                if(isWriteable()){
                    show.setText("SD卡不可用");
                    return;
                }
            }else {
                if(isReadable()){
                    show.setText("SD卡不可读");
                    return;
                }
            }
            rwHandle(rw);
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void privateFile(String rw){
            dir = getExternalFilesDir(null);
            mf = new File(dir, Objects.requireNonNull(f_name.getText()).toString());
            rwHandle(rw);
        }
        @SuppressLint("SetTextI18n")
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        private void publicDir(String rw){
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            mf = new File(dir, Objects.requireNonNull(f_name.getText()).toString());
            if(rw.equals("w")){
                if(isWriteable()){
                    show.setText("SD卡不可用");
                    return;
                }
            }else {
                if(isReadable()){
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
    @SuppressLint("SetTextI18n")
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
    private void create53(){
        class  MySqlLiteHelper extends SQLiteOpenHelper{
            private  String CREEATE_TABLE_USER="create table users(id  integer primary key autoincrement,userid text,password text)";
            private Context sContext;
            private String CREATE_TABLE_TYPE="create table types(id integer primary key autoincrement,type_code,describe text)";
            public MySqlLiteHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
                super(context,name,factory,version);
                sContext=context;
            }
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREEATE_TABLE_USER);
                db.execSQL(CREATE_TABLE_TYPE);
                Toast.makeText(sContext,"成功创建数据表",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("drop table if exists users");
                db.execSQL("drop table if exists types");
                onCreate(db);
            }

            @Override
            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.setVersion(oldVersion);
            }
        }
        final MySqlLiteHelper[] mySqlLiteHelper = new MySqlLiteHelper[1];
        final SQLiteDatabase[] myDb = new SQLiteDatabase[1];
        MaterialTextView textView = createTextView("");
        createButton("创建数据库").setOnClickListener(v -> {
            mySqlLiteHelper[0] = new MySqlLiteHelper(AutoLayout.this,"userDb.db",null,1);
            myDb[0] = mySqlLiteHelper[0].getWritableDatabase();//完成创建数据库
            textView.setText("数据库："+ myDb[0].getPath());//显示数据库文件机器路径
        });
        layout.addView(textView);
        createButton("删除数据库").setOnClickListener(v -> {
            if(myDb[0].isOpen()){
                myDb[0].close();
            }
            String path = myDb[0].getPath();//获取数据库文件名 包含路径
            File db = new File(path);
            SQLiteDatabase.deleteDatabase(db);//删除数据库
            textView.setText("数据库已删除");
        });

        createButton("升级数据库").setOnClickListener(v->{
            mySqlLiteHelper[0]=new MySqlLiteHelper(AutoLayout.this,"userDb.db",null,2);
            myDb[0]=mySqlLiteHelper[0].getWritableDatabase();//完成数据库升级
            textView.setText("数据库升级成功");
        });

        EditText id  = createEditText("输入用户ID");
        EditText password  = createEditText("输入登陆密码");

        ListView listView = new ListView(this);
        createButton("添加记录").setOnClickListener(v -> {
            if(myDb[0]==null){
                return;
            }
            ContentValues cv = new ContentValues();
            String strId = id.getText().toString();
            String pwd = password.getText().toString();
            cv.put("userid",strId);
            cv.put("password",pwd);
            myDb[0].insert("users",null,cv);//添加数据
            Toast.makeText(AutoLayout.this,"成功添加记录",Toast.LENGTH_LONG).show();
            refreshList(listView,myDb[0]);
        });
        createButton("修改数据(根据id更新密码)").setOnClickListener(v->{
            if(myDb[0]==null){
                return;
            }
            ContentValues cv = new ContentValues();
            String strId = id.getText().toString();
            String pwd = password.getText().toString();
            cv.put("password",pwd);
            myDb[0].update("users",cv,"userid=?",new String[]{strId});//更新数据
            Toast.makeText(AutoLayout.this,"成功修改记录",Toast.LENGTH_LONG).show();
            refreshList(listView,myDb[0]);
        });
        createButton("删除数据(根据id删除)").setOnClickListener(v->{
            if(myDb[0]==null){
                return;
            }
            String strId = id.getText().toString();
            myDb[0].delete("users","userid=?",new String[]{strId});//删除数据
            Toast.makeText(AutoLayout.this,"成功删除记录",Toast.LENGTH_LONG).show();
            refreshList(listView,myDb[0]);
        });
        createButton("使用toast提示展示数据库所有数据").setOnClickListener(v -> {
            if(myDb[0]==null){
                return;
            }
            Cursor c = myDb[0].query("users",null,null,null,null,null,null);
            String msg = "当前记录如下:\n";
            if(c.moveToFirst()){
                do{
                    msg = msg+"userid:"+c.getString(c.getColumnIndex("userid"))+" password="+c.getString(c.getColumnIndex("password"))+"\n";
                }while (c.moveToNext());
            }
            Toast.makeText(AutoLayout.this,msg,Toast.LENGTH_LONG).show();
            Log.e("toast",msg);

//            @SuppressLint("HandlerLeak") Handler mHandler = new Handler() {
//                public void dispatchMessage(android.os.Message msg) {
//                    if (msg.what == 1) {
//                        Toast.makeText(AutoLayout.this, "这是一个toast", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            };
//            new Thread(() -> mHandler.sendEmptyMessage(1)).start();
        });
        layout.addView(listView);
    }
    private MaterialButton createButton(String text){
        MaterialButton button = new MaterialButton(this);
        button.setText(text);
        button.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        layout.addView(button);
        return button;
    }
    private MaterialTextView createTextView(String text){
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText(text);
        return textView;
    }
    private EditText createEditText(String hint){
        EditText editText = new EditText(this);
        editText.setHint(hint);
        editText.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT));
        layout.addView(editText);
        return editText;
    }
    private void refreshList(ListView listView,SQLiteDatabase myDb){
        Cursor c = myDb.query("users",new String[]{"id as _id","userid","password"},null,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(AutoLayout.this,R.layout.simple_database_list,c,new String[]{"userid","password"},new int[]{R.id.textID,R.id.textPwd});
        listView.setAdapter(adapter);

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
            @SuppressLint("Recycle") Cursor c = myDb.query("users",new String[]{"userid"},"userid=? and password=?",new String[]{id,pwd},null,null,null);
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