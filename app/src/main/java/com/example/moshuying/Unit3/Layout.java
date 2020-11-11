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
    private List<LayoutList> layoutLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.unit3_layout);

        AddLayoutList("3.1.3-1 线性布局","3","简单线性布局分配weight");
        AddLayoutList("3.1.3-2","线性布局","短信发送界面");
        AddLayoutList("3.1.4","相对布局","简单登录界面");
        AddLayoutList("3.1.5","帧布局","层叠视图");
        AddLayoutList("3.2.3","文本字段","自动补全的文本字段");
        AddLayoutList("3.2.7","微调框","下拉列表");
        AddLayoutList("3.2.8","图片视图","动态切换");
        AddLayoutList("3.2.9","进度条","进度条");
        AddLayoutList("3.2.10","拖动条","拖动条拖动滑块可获得标识的数值");
        AddLayoutList("3.3.1","使用Toast","类似于对话框的方式向用户提供消息通知");
        AddLayoutList("3.3.2","使用Notification","在通知区域显示通知图标，展开抽屉式通知栏，可查看通知的详细信息。");
        AddLayoutList("3.4.1","AlertDialog","对话框用于显示警告提示信息，可以在对话框中选择取消或确认操作");
        AddLayoutList("3.4.2","ProgressDialog","和AlertDialog类似，但有一个进度条");
        AddLayoutList("3.4.3","DatePickerDialog","日期选择器");
        AddLayoutList("3.4.4","TimePickerDialog","时间选择器");
        AddLayoutList("3.5","菜单","展示在顶部侧边的菜单栏");
        AddLayoutList("3.6.1","ListView","android内置列表样式");
        AddLayoutList("3.6.2","自定义ListView列表项布局","让列表项显示更丰富的内容");
        AddLayoutList("3.6.3","处理ListView单击事件","让列表项响应用户单机事件");
        AddLayoutList("3.7.1","RecyclerView","RecyclerView基本用法");
        AddLayoutList("3.7.2","RecyclerView","自定义RecyclerView列表项布局");
        AddLayoutList("3.7.3","RecyclerView","RecyclerView水平布局");
        AddLayoutList("3.7.3-2","RecyclerView","RecyclerView瀑布流布局");
        AddLayoutList("3.7.4","RecyclerView","处理RecyclerView单击事件");
        AddLayoutList("3.8","编程实践","用户登录界面设计");
        AddLayoutList("3.8-2","编程实践","3.8用户登录界面设计的对话框版本");


        RecyclerView recyclerView = findViewById(R.id.unit3_recyclerview);
        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void AddLayoutList(final String unit, final String title, final String subtitle){
        layoutLists.add(new LayoutList(unit+" "+title, subtitle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Layout.this,AutoLayout.class);
                intent.putExtra("unit",unit);
                intent.putExtra("title",title);
                intent.putExtra("subtitle",subtitle);
                startActivity(intent);
            }
        }));
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