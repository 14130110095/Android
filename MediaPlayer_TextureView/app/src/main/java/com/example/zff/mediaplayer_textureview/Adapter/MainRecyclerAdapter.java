package com.example.zff.mediaplayer_textureview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zff.mediaplayer_textureview.Constants;
import com.example.zff.mediaplayer_textureview.R;
import com.example.zff.mediaplayer_textureview.entity.ItemInfo;
import com.example.zff.mediaplayer_textureview.interface_.OnItemClickListener;

import java.util.List;

/**
 * Created by Aspire on 2018/6/7.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter{

    private List<ItemInfo> stringList;
    private Context mContext;
    private OnItemClickListener mItemClickListener;
    private final static int TYPE_HEAD = 0;
    private final static int TYPE_CONTENT = 1;
    private int mErea = 0;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            textView = (TextView)itemView.findViewById(R.id.textview);
            view = itemView;
        }
    }

    static class HeadHolder extends RecyclerView.ViewHolder{

        TextView textView_erea;
        TextView textView_more;

        public HeadHolder(View itemView) {
            super(itemView);
            textView_erea = (TextView)itemView.findViewById(R.id.textview_erea);
            textView_more = (TextView)itemView.findViewById(R.id.textview_more);
        }
    }

    public MainRecyclerAdapter(Context context, List<ItemInfo> list,int erea){
        mContext = context;
        stringList = list;
        mErea = erea;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainchineserecyclerheaditem, parent, false);
            HeadHolder headHolder = new HeadHolder(view);
            return headHolder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainchineserecycleritem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeadHolder){
            MainRecyclerAdapter.HeadHolder viewHolder = (MainRecyclerAdapter.HeadHolder)holder;
            if (position == 0) {
                viewHolder.textView_erea.setText(Constants.ereaList[mErea]);
            }else if(position == 5){
                viewHolder.textView_erea.setText(Constants.ereaList[mErea+1]);
            }
            viewHolder.textView_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, 0,mErea);
                    }
                }
            });
        }else {
            MainRecyclerAdapter.ViewHolder viewHolder = (MainRecyclerAdapter.ViewHolder)holder;
            ItemInfo itemInfo = stringList.get(position);
            viewHolder.imageView.setImageResource(itemInfo.getPicture());
            viewHolder.textView.setText(itemInfo.getPicname());
            viewHolder.view.setTag(position);
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, (int) v.getTag(),mErea);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == 5){
            return TYPE_HEAD;
        }else {
            return TYPE_CONTENT;
        }
    }
}
