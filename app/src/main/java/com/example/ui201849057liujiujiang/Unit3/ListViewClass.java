package com.example.ui201849057liujiujiang.Unit3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ui201849057liujiujiang.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewClass extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);// todo: need change
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("3.1 布局的定义方法","在设计Android应用程序UI时，可通过XML定义和代码定义两种方法来定义布局"));
        itemList.add(new Item("布局的定义方法","在设计Android应用程序UI时，可通过XML定义和代码定义两种方法来定义布局"));
        itemList.add(new Item("布局的定义方法","在设计Android应用程序UI时，可通过XML定义和代码定义两种方法来定义布局"));
        ItemAdapter itemAdapter = new ItemAdapter(this,R.layout.unit3_item,itemList);
        android.widget.ListView listView = findViewById(R.id.recyclerview);
        listView.setAdapter(itemAdapter);
    }
}
class Item {
    private String title;
    private String subTitle;
    public Item(String title,String subTitle){
        this.title = title;
        this.subTitle = subTitle;
    }
    public String getTitle(){return title;}
    public String getSubTitle() { return subTitle; }
}

class ItemAdapter extends ArrayAdapter<Item> {
    public int itemId;
    public ItemAdapter(Context context, int resource, List<Item> objects){
        super(context,resource,objects);
        itemId = resource;
    }
    @androidx.annotation.NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        Item item = getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(itemId,parent,false);
        TextView title = view.findViewById(R.id.title);
        assert item != null;
        title.setText(item.getTitle());
        TextView subTitle = view.findViewById(R.id.subTitle);
        subTitle.setText(item.getSubTitle());
        return view;
    }

}