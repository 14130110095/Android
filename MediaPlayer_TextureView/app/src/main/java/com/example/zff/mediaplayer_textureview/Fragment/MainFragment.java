package com.example.zff.mediaplayer_textureview.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.zff.mediaplayer_textureview.Adapter.ImagePagerAdapter;
import com.example.zff.mediaplayer_textureview.Adapter.MainRecyclerAdapter;
import com.example.zff.mediaplayer_textureview.R;
import com.example.zff.mediaplayer_textureview.entity.ItemInfo;
import com.example.zff.mediaplayer_textureview.interface_.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener,ImagePagerAdapter.onClick,OnItemClickListener {


    private ScrollView scrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager viewPager;
    private int currentPosition = 1;
    private int AddataList = IMAGES.length;
    private Handler handler = new Handler();
    private static final int[] IMAGES = {
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_add,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_add
    };

    private RecyclerView recyclerView_chinese;
    private MainRecyclerAdapter chineseRecyclerAdapter;
    private List<ItemInfo> chineseRecyclerList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment_layout,container,false);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView_main);
        scrollView.smoothScrollTo(0,0);
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        recyclerView_chinese = (RecyclerView)view.findViewById(R.id.recyclerview_chinese_erea);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.gank_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //服务器重新请求数据
                refreshList();
            }
        });
        initRecycler();
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getContext(),IMAGES);
        imagePagerAdapter.setmOnClick(this);
        viewPager.setAdapter(imagePagerAdapter);
        if(AddataList > 1) {
            viewPager.setCurrentItem(1);
        }else{
            viewPager.setCurrentItem(0);
        }
        viewPager.addOnPageChangeListener(this);
        viewPager.requestFocus();
        return view;
    }

    public void initRecycler(){
        chineseRecyclerAdapter = new MainRecyclerAdapter(getContext(),chineseRecyclerList,0);
        chineseRecyclerAdapter.setItemClickListener(this);
        final GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new GridStaggerLoopup());
        recyclerView_chinese.setLayoutManager(manager);
        recyclerView_chinese.setAdapter(chineseRecyclerAdapter);
        recyclerView_chinese.setHasFixedSize(true);
        recyclerView_chinese.setNestedScrollingEnabled(false);
        initData();
    }

    class GridStaggerLoopup extends GridLayoutManager.SpanSizeLookup{

        @Override
        public int getSpanSize(int position) {
            //0位置占两个跨度，其他是一个
            return ((position == 0 || position == 5) ? 2 : 1);
        }
    }

    private void initData(){

        for(int i=0;i<10;i++){
            if(i == 0 || i == 5){
                ItemInfo itemInfo_0 = new ItemInfo();
                itemInfo_0.setPicname("");
                itemInfo_0.setPicture(R.mipmap.bg);
                chineseRecyclerList.add(itemInfo_0);
            }else {
                ItemInfo itemInfo = new ItemInfo();
                itemInfo.setPicname(String.valueOf(i));
                itemInfo.setPicture(R.mipmap.bg);
                chineseRecyclerList.add(itemInfo);
            }
        }
        chineseRecyclerAdapter.notifyDataSetChanged();
    }

    private void refreshList(){
        chineseRecyclerList.clear();
        for(int i=0;i<10;i++){
            if(i == 0 || i == 5){
                ItemInfo itemInfo_0 = new ItemInfo();
                itemInfo_0.setPicname("");
                itemInfo_0.setPicture(R.mipmap.bg);
                chineseRecyclerList.add(itemInfo_0);
            }else {
                ItemInfo itemInfo = new ItemInfo();
                itemInfo.setPicname(String.valueOf(i)+"****");
                itemInfo.setPicture(R.mipmap.bg1);
                chineseRecyclerList.add(itemInfo);
            }
        }
        //隐藏加载圈
        swipeRefreshLayout.setRefreshing(false);
        chineseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(AddataList > 1) {
            //position是当前滑动到的位置
            currentPosition = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(AddataList > 1) {

            if (state != ViewPager.SCROLL_STATE_IDLE) {
                handler.removeCallbacks(runnable);
                return;
            }


            // 当视图在第一个时，将页面号设置为图片的最后一张。
            if (currentPosition == 0) {
                viewPager.setCurrentItem(IMAGES.length - 2, false);

            } else if (currentPosition == IMAGES.length - 1) {
                // 当视图在最后一个是,将页面号设置为图片的第一张。
                viewPager.setCurrentItem(1, false);
            }
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                handler.postDelayed(runnable, 3000);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(AddataList > 1) {
            handler.post(runnable);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(currentPosition == IMAGES.length - 1){
                currentPosition = 1;
            }
            viewPager.setCurrentItem(currentPosition++,false);
            handler.postDelayed(runnable,3000);

        }
    };

    @Override
    public void onPause() {
        super.onPause();
        if(AddataList > 1) {
            handler.removeCallbacks(runnable);
        }
    }


    //AD ViewPager
    @Override
    public void onClickItem(View v, int Position) {

         //根据链接启动url
    }

    //RecyclerView Item
    @Override
    public void onItemClick(View v, int position, int erea) {

    }
}
