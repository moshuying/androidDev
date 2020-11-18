package com.example.moshuying.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moshuying.R;

import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.ItemViewHolder>{
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