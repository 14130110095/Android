package com.example.zff.roundrectpicturetest;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends AppCompatActivity {

    private String path_1= "http://img4.jiecaojingxuan.com/2016/3/14/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg";
    private String path_2 = "http://cn.bing.com/az/hprichbg/rb/HoliMunich_ZH-CN12353152381_1920x1080.jpg";
    private int mWidth = R.dimen.view_width_drawable;
    private int mHight = R.dimen.view_height_drawable;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BitmapShader用于自定义view
        BitmapShaderImageView picture_1 = (BitmapShaderImageView)findViewById(R.id.imageview_0);
        picture_1.setUrl(path_2);


        imageView = (ImageView)findViewById(R.id.imageview_1);
//        getURLImageView();

    }

    private void getURLImageView(){
        ImageLoader.getInstance().loadImage(path_2, new ImageSize(mWidth-2, mHight-2), null, this.loadingListener);
    }



    private ImageLoadingListener loadingListener = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if(loadedImage != null){
                imageView.setImageDrawable(new RoundImageDrawable(loadedImage,1));
            }

        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    };
}
