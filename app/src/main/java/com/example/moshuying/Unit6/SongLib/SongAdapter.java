package com.example.moshuying.Unit6.SongLib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moshuying.R;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    public int resId;
    public SongAdapter(Context context, int resource, List<Song> objects){
        super(context,resource,objects);
        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Song song = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView Title = view.findViewById(com.example.moshuying.R.id.tvTitle);
        TextView Singer = view.findViewById(com.example.moshuying.R.id.tvSinger);
        TextView Album = view.findViewById(com.example.moshuying.R.id.tvAlbum);
        TextView Duration = view.findViewById(com.example.moshuying.R.id.tvDuration);
        TextView Size = view.findViewById(com.example.moshuying.R.id.tvSize);
        Title.setText("歌曲："+song.getTitle());
        Singer.setText("歌手："+song.getSinger());
        Album.setText("专辑："+song.getAlbum());
        int m = song.getDuration() / 60000;
        int s = (song.getDuration()-m*60000)/1000;
        Duration.setText("时长:"+m+"分"+s+"秒");
        Size.setText("大小："+song.getSize());
        return view;
    }
}
