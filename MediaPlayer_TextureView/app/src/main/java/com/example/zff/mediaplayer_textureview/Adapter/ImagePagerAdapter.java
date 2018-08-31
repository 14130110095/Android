package com.example.zff.mediaplayer_textureview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by zff on 2018/6/26.
 */

public class ImagePagerAdapter extends PagerAdapter{

    private Context mContext;

    private static int[] IMAGES = null;

    private onClick mOnClick = null;

    public ImagePagerAdapter(Context context,int[] imagelist) {
        super();
        IMAGES = imagelist;
        mContext = context;
    }


    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object );
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(IMAGES[position]);
        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(mOnClick != null){
                     mOnClick.onClickItem(v, (Integer) v.getTag());
                 }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    public interface onClick{
        void onClickItem(View v ,int Position);
    }

    public void setmOnClick(onClick onClick){
        mOnClick = onClick;
    }
}
