package com.example.zff.mediaplayer_textureview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class DetailAcyivity extends AppCompatActivity {

    private JZVideoPlayerStandard videoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acyivity);
        videoplayer = (JZVideoPlayerStandard)findViewById(R.id.jzvideoplayer);
        videoplayer.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", JZVideoPlayer.SCREEN_WINDOW_NORMAL,"Test");
        Glide.with(this).load("http://img4.jiecaojingxuan.com/2016/3/14/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg").into(videoplayer.thumbImageView);
    }

    public static void actionStartDetailAcyivity(Context context){
        Intent intent = new Intent(context,DetailAcyivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }
}
