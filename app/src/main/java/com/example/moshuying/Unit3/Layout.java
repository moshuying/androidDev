package com.example.moshuying.Unit3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moshuying.R;

import java.util.ArrayList;
import java.util.List;
public class Layout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);
        List<LayoutList> layoutLists = new ArrayList<>();
        layoutLists.add(new LayoutList("3.1.3 线性布局", "简单线性布局分配weight", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.1.3-1");
                startActivity(intent);
            }
        }));

        layoutLists.add(new LayoutList("3.1.3 线性布局", "短信发送界面", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.1.3-2");
                startActivity(intent);
            }
        }));
        layoutLists.add(new LayoutList("3.1.4 相对布局", "简单登录界面", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.1.4");
                startActivity(intent);
            }
        }));
        layoutLists.add(new LayoutList("3.1.5 帧布局", "层叠视图", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.1.5");
                startActivity(intent);
            }
        }));
        layoutLists.add(new LayoutList("3.2.3 文本字段", "自动补全的文本字段", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.2.3");
                startActivity(intent);
            }
        }));
        layoutLists.add(new LayoutList("3.2.7 微调框", "下拉列表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.2.7");
                startActivity(intent);
            }
        }));
        layoutLists.add(new LayoutList("3.2.8 图片视图", "动态切换", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit","3.2.8");
                startActivity(intent);
            }
        }));

        RecyclerView recyclerView = findViewById(R.id.unit3_recyclerview);
        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
class LayoutList {
    private String title;
    private String subTitle;
    private View.OnClickListener listener;
    public LayoutList(String title,String subTitle,View.OnClickListener listener){
        this.title = title;
        this.subTitle = subTitle;
        this.listener = listener;
    }
    public String getTitle(){return title;}
    public String getSubTitle() { return subTitle; }
    public View.OnClickListener getListener() { return listener; }
}
class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.ItemViewHolder>{
    private List<LayoutList> itemList;
    static class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView subTitle;
        private Button button;
        public ItemViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            button = itemView.findViewById(R.id.goNext);
        }
    }
    public LayoutAdapter(List<LayoutList> itemList){
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit3_item,parent,false);
        return new ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder,int position){
        LayoutList item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());
        holder.button.setOnClickListener(item.getListener());
    }
    @Override
    public int getItemCount(){
        return itemList.size();
    }
}