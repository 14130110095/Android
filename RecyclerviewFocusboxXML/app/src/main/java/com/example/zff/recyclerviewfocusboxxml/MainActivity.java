package com.example.zff.recyclerviewfocusboxxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TestAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    private List<ItemInfo> list = new ArrayList<>();

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
        recyclerView.scrollToPosition(0);
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

        if (this.recyclerView != null) {
            Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();
        }
    }


}
