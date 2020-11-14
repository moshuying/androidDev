package com.example.ui201849057liujiujiang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui201849057liujiujiang.Unit3.Layout;

import java.util.ArrayList;
import java.util.List;

public class UnitChoose extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);
        setContentView(R.layout.auto);
        LinearLayout BaseLinearLayout =findViewById(R.id.auto_list_item);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams RlayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.setLayoutParams(RlayoutParams);
        BaseLinearLayout.addView(relativeLayout);

        List<LayoutList> layoutLists = new ArrayList<>();
        layoutLists.add(new LayoutList("一单元", "开发起步", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this,Unit1.class));
            }
        }));
        layoutLists.add(new LayoutList("二单元", "活动", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this,MainActivity.class));
            }
        }));
        layoutLists.add(new LayoutList("三单元", "UI设计", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitChoose.this, Layout.class));
            }
        }));
//        layoutLists.add(new LayoutList("四单元", "广播机制", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UnitChoose.this, com.example.liujiujiang.Unit4.Layout.class));
//            }
//        }));

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(new LayoutAdapter(layoutLists));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        relativeLayout.addView(recyclerView);
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
    public LayoutAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unit3_item,parent,false);
        return new LayoutAdapter.ItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(LayoutAdapter.ItemViewHolder holder, int position){
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