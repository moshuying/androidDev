package com.example.moshuying.Unit7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.moshuying.R;
import com.example.moshuying.Unit6.SongLib.Song;
import com.example.moshuying.Unit6.SongLib.SongAdapter;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AutoLayout extends AppCompatActivity{
    private Intent intent;
    private final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private boolean seekBarChange;
    private MediaPlayer mediaPlayer = null;
    private ArrayList<Song> listSong;
    private TextView tvLen,tvMusicName;
    private SeekBar sbSeek;
    @RequiresApi(api = Build.VERSION_CODES.Q)
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
            case "7.5": create75(layout);break;
            default:break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void create75(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText("我的课表");
        layout.addView(textView);
        Spinner spinner = new Spinner(this);
        layout.addView(spinner);
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        listView.setLayoutParams(layoutParams);
        class BookAdapter extends ArrayAdapter<MyClass> {
            public int resId;
            public BookAdapter(Context context, int resource, List<MyClass> objects){
                super(context,resource,objects);
                resId = resource;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                MyClass myClass = getItem(position);
                View view = LayoutInflater.from(getContext()).inflate(resId,parent,false);

                LinearLayout linearLayout = view.findViewById(R.id.auto_list_item);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

                TextView textView1 = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(100,WRAP_CONTENT);
                layoutParams1.gravity = Gravity.CENTER_VERTICAL;
                textView1.setLayoutParams(layoutParams1);
                linearLayout.addView(textView1);

                TextView textView2 = new TextView(getContext());
                textView2.setLayoutParams(layoutParams1);

                TextView textView3 = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
                layoutParams2.gravity = Gravity.CENTER_VERTICAL;
                layoutParams2.weight=1;
                linearLayout.addView(textView3);
                return view;
            }
        }
        String[] week = {"星期一","星期二","星期三","星期四","星期五"};
        String[] nos = {"第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节"};
        String[] times = {"08:00-08:50", "09:00-09:50", "10:00-10:50", "11:00-11:50", "14:00-14:50", "15:00-15:50", "16:00-16:50"};

        List<MyClass> schedul = null;
        
    }

    public TextView AddMinTitle(String title, int size){
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText(title);
        textView.setTextSize(size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}