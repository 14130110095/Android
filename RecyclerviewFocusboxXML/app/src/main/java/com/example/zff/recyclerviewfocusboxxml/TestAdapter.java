package com.example.zff.recyclerviewfocusboxxml;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aspire on 2018/6/7.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{

    private List<ItemInfo> stringList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ContextListItem contextListItem;

        public ViewHolder(View itemView) {
            super(itemView);
            contextListItem = (ContextListItem)itemView;
        }
    }

    public TestAdapter(Context context, List<ItemInfo> list){
        mContext = context;
        stringList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ItemInfo itemInfo = stringList.get(position);
        holder.contextListItem.setInfo(itemInfo,position);
        holder.contextListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
