package com.example.moshuying.Unit6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.moshuying.R;
import com.example.moshuying.Unit6.SongLib.Song;
import com.example.moshuying.Unit6.SongLib.SongAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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
    private LinearLayout layout;
    @RequiresApi(api = Build.VERSION_CODES.Q)
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
            case "6.4": create64(layout);break;
            case "6.1.3": create613();break;
            default:break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void create613(){
        // MediaPlyer既可用于播放音频，也可用于播放视频，在用法上没有多大的区别，只是在播放视频时应使用SurfaceView控件
        AutoAddTitle();
        MaterialTextView textView = new MaterialTextView(this);
        textView.setText("视频不出来可能是没网或者链接失效，可以自行到源码中修改视频链接地址。也可以去github提个issue");
        layout.addView(textView);
        MediaPlayer mediaPlayer = new MediaPlayer();// 创建MediaPlayer对象
        // 动态鉴权
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            new Thread(() -> {
                try {
                    mediaPlayer.setDataSource(AutoLayout.this, Uri.parse("http://exampleapp.moshuying.top/exampleApp/resource/mengnanban_xinbaodao.mp4"));
                    mediaPlayer.prepare();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }

        SurfaceView surfaceView = new SurfaceView(this);
        LinearLayout.LayoutParams surfaceViewLayoutParams = new LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT);
        surfaceViewLayoutParams.weight = 1;
        surfaceView.setLayoutParams(surfaceViewLayoutParams);

        SurfaceHolder holder = surfaceView.getHolder();
        SurfaceHolder.Callback surfaceHolderCallback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                mediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        };
        holder.addCallback(surfaceHolderCallback);

        LinearLayout  videoControl = new LinearLayout(this);

        videoControl.addView(createButton("播放视频",v->{
            mediaPlayer.start();
        }));
        videoControl.addView(createButton("暂停视频",v->{mediaPlayer.pause();}));
        videoControl.addView(createButton("停止视频",v->{
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            }catch (IOException e ){
                e.printStackTrace();
            }
        }));

        layout.addView(videoControl);
        layout.addView(surfaceView);
    }

    private MaterialButton createButton(String text,View.OnClickListener l){
        MaterialButton button = new MaterialButton(this);
        button.setText(text);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
        layoutParams.weight = 1;
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(l);
        return button;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void create64(LinearLayout layout){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
        setContentView(R.layout.song_activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            // 动态申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        initAudioList();
        tvMusicName = (TextView) findViewById(R.id.tvName);
        tvLen = (TextView) findViewById(R.id.tvLen);
        sbSeek = (SeekBar) findViewById(R.id.sbSeek);
        sbSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarChange = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarChange = false;
                mediaPlayer.seekTo(seekBar.getProgress()); // 拖动进度改变播放进度
            }
        });
        // 开始播放
        findViewById(R.id.btPlay).setOnClickListener(v->{ if(mediaPlayer!=null) mediaPlayer.start(); });
        // 暂替
        findViewById(R.id.btPause).setOnClickListener(v->{
            if(null!=mediaPlayer&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });
        // 停止
        findViewById(R.id.btStop).setOnClickListener(v->{
            if(null!=mediaPlayer&&mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btRefresh).setOnClickListener(v->{
            initAudioList();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    //从媒体库获取MP3信息
    private void initAudioList(){
        MediaScannerConnection.scanFile(this,new String[]{Environment.getExternalStorageDirectory().getAbsolutePath()},null,null);
        listSong = new ArrayList<Song>();
        Cursor musics = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA,
                },MediaStore.Audio.Media.MIME_TYPE+"=?",new String[]{"audio/mpeg"},null);
        String fileName,title, singer, album, size, filePath = "";
        int duration,m,s;

        if(musics.moveToFirst()){
            do{
                fileName = musics.getString(1);
                title = musics.getString(2);
                duration = musics.getInt(3);
                singer = musics.getString(4);
                album = musics.getString(5);
                size = (musics.getString(6)==null)?"未知":musics.getInt(6)/1024/1024+"MB";
                if(musics.getString(7)!=null) filePath = musics.getString(7);
                listSong.add(new Song(fileName, title,duration, singer, album, size, filePath));
            }while (musics.moveToNext());
        }
        musics.close();
        SongAdapter adapter = new SongAdapter(this,R.layout.song_item,listSong);
        ListView listView = findViewById(R.id.listAudios);
        listView.setAdapter(adapter);
        listView .setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = listSong.get(position);
                initMediaPlayer(song);// 在歌曲列表中单击歌曲名称时播放该歌曲
            }
        });
    }
    private void initMediaPlayer(Song song){
        try{
            if(mediaPlayer==null){mediaPlayer = new MediaPlayer();}
            mediaPlayer.reset();
            mediaPlayer.setDataSource(song.getFilePath());
            mediaPlayer.prepare();//加载音频
            mediaPlayer.start();
            int m = song.getDuration()/60000;
            int s = (song.getDuration()-m*60000)/1000;
            tvLen.setText("时长："+m+"分"+s+"秒");
            tvMusicName.setText(song.getFileName());
            sbSeek.setMax(song.getDuration());
            // 实时更新SeekBar进度
            Timer mTimer = new Timer();
            TimerTask mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if (seekBarChange) return;
                    sbSeek.setProgress(mediaPlayer.getCurrentPosition());
                }
            };
            mTimer.schedule(mTimerTask,0,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(!(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(this,"未获得SD卡访问权限",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
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
    private void AutoAddTitle(){
        layout.addView(AddMinTitle(intent.getStringExtra("title"),18));
    }
}