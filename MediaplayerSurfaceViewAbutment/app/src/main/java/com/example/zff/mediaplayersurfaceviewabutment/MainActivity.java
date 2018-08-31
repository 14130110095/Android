package com.example.zff.mediaplayersurfaceviewabutment;

import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    //用于播放视频的mediaPlayer对象
    private MediaPlayer firstPlayer,     //负责播放进入视频播放界面后的第一段视频
            nextMediaPlayer, //负责一段视频播放结束后，播放下一段视频
            cachePlayer,     //负责setNextMediaPlayer的player缓存对象
            currentPlayer;   //负责当前播放视频段落的player对象
    //负责配合mediaPlayer显示视频图像播放的surfaceView
    private SurfaceView surface;
    private SurfaceHolder surfaceHolder;

    private TextView textViewtime;
    private TextView textViewclose;
    private RelativeLayout relativelayoutContainer;

    private Handler mHandler = new Handler();

    //================================================================

    //存放所有视频端的url
    private ArrayList<String> VideoListQueue = new ArrayList<String>();
    //所有player对象的缓存
    private HashMap<String, MediaPlayer> playersCache = new HashMap<String, MediaPlayer>();
    //当前播放到的视频段落数
    private int currentVideoIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面控件
        initView();
    }

    /*
     * 负责界面销毁时，release各个mediaplayer
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (firstPlayer != null) {
            if (firstPlayer.isPlaying()) {
                firstPlayer.stop();
            }
            firstPlayer.release();
        }
        if (nextMediaPlayer != null) {
            if (nextMediaPlayer.isPlaying()) {
                nextMediaPlayer.stop();
            }
            nextMediaPlayer.release();
        }

        if (currentPlayer != null) {
            if (currentPlayer.isPlaying()) {
                currentPlayer.stop();
            }
            currentPlayer.release();
        }
        currentPlayer = null;
    }

    /*
     * 界面控件的初始化
     */
    private void initView() {
        relativelayoutContainer = (RelativeLayout)findViewById(R.id.relativelayout_Container);
        surface = (SurfaceView) findViewById(R.id.surfaceview);
        textViewtime = (TextView)findViewById(R.id.texttime);
        textViewclose = (TextView)findViewById(R.id.close);
        textViewclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(relativelayoutContainer.getVisibility() == View.VISIBLE)
                    firstPlayer.setDisplay(null);
                    firstPlayer.seekTo(firstPlayer.getDuration());
                    relativelayoutContainer.setVisibility(View.INVISIBLE);

            }
        });


        surfaceHolder = surface.getHolder();// SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); // 因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        //surfaceView创建完毕后，首先获取该直播间所有视频分段的url
        getVideoUrls();
        //然后初始化播放手段视频的player对象
        initFirstPlayer();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO 自动生成的方法存根

    }

    /*
     * 初始化播放首段视频的player
     */
    private void initFirstPlayer() {
        firstPlayer = new MediaPlayer();
        firstPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        firstPlayer.setDisplay(surfaceHolder);

        firstPlayer
                .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        onVideoPlayCompleted(mp);
                    }
                });

        //设置cachePlayer为该player对象
        cachePlayer = firstPlayer;
        initNexttPlayer();

        //player对象初始化完成后，开启播放
        startPlayFirstVideo();
    }

    private void startPlayFirstVideo() {
        try {
            firstPlayer.setDataSource(VideoListQueue.get(currentVideoIndex));
            firstPlayer.prepare();
            firstPlayer.start();
            mHandler.post(runnable);

        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    /*
     * 新开线程负责初始化负责播放剩余视频分段的player对象,避免UI线程做过多耗时操作
     */
    private void initNexttPlayer() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 1; i < VideoListQueue.size(); i++) {
                    nextMediaPlayer = new MediaPlayer();
                    nextMediaPlayer
                            .setAudioStreamType(AudioManager.STREAM_MUSIC);

                    nextMediaPlayer
                            .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    onVideoPlayCompleted(mp);
                                }
                            });

                    try {
                        nextMediaPlayer.setDataSource(VideoListQueue.get(i));
                        nextMediaPlayer.prepare();
                    } catch (IOException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }

                    //set next mediaplayer
                    cachePlayer.setNextMediaPlayer(nextMediaPlayer);
                    //set new cachePlayer
                    cachePlayer = nextMediaPlayer;
                    //put nextMediaPlayer in cache
                    playersCache.put(String.valueOf(i), nextMediaPlayer);

                }

            }
        }).start();
    }

    /*
     * 负责处理一段视频播放过后，切换player播放下一段视频
     */
    private void onVideoPlayCompleted(MediaPlayer mp) {
        Toast.makeText(this,"complete",Toast.LENGTH_SHORT).show();
        mHandler.removeCallbacks(runnable);
        textViewtime.setText(String.valueOf(0));
        relativelayoutContainer.setVisibility(View.INVISIBLE);

        mp.setDisplay(null);
        //get next player
        currentPlayer = playersCache.get(String.valueOf(++currentVideoIndex));
        if (currentPlayer != null) {
            currentPlayer.setDisplay(surfaceHolder);
        } else {
            Toast.makeText(MainActivity.this, "视频播放完毕..", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void getVideoUrls() {
        VideoListQueue.add("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        VideoListQueue.add("http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4");
//        VideoListQueue.add("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
//        VideoListQueue.add("http://vjs.zencdn.net/v/oceans.mp4");
//        VideoListQueue.add("http://ohjdda8lm.bkt.clouddn.com/course/sample1.mp4");
    }

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int duration = firstPlayer.getDuration();
            int position = firstPlayer.getCurrentPosition();
//            int duration = firstPlayer.getDuration();
            textViewtime.setText(String.valueOf((duration - position)/1000));
            mHandler.postDelayed(runnable,1000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(runnable);
    }
}
