package com.example.zff.recyclerviewfocusbox;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TestAdapter.OnItemListener {

    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    private List<ItemInfo> list = new ArrayList<>();
    private View LastSelectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        testAdapter = new TestAdapter(this,list);
        testAdapter.setItemClickListener(this);
        recyclerView.setAdapter(testAdapter);
        recyclerView.requestFocus();
    }

    private void initList(){
        for(int i=0;i<40;i++){
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setPicture(R.mipmap.bg);
            itemInfo.setPicname(String.valueOf(i));
            itemInfo.setSelected(false);
            list.add(itemInfo);
        }
    }

    @Override
    public void onItemClick(View v,int position) {
        if (this.recyclerView != null && this.recyclerView.hasFocus()) {
            recyclerView.scrollToPosition(position);
            //recyclerView.getChildCount()得到的是当前可见的item
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                View indexView = recyclerView.getChildAt(i);
                if (indexView == v) {
                    indexView.setSelected(true);
                }else{
                    indexView.setSelected(false);
                }
            }
            testAdapter.notifyDataSetChanged();
            //这里再改变一次，防止因item不可见导致状态未改变
            if(LastSelectedView != null){
                LastSelectedView.setSelected(false);
            }
            LastSelectedView = v;
        }
    }
}
