package com.example.zff.mediaplayer_textureview.component;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zff.mediaplayer_textureview.R;

public class Tablayout_item extends LinearLayout {

    private Context mContext;
    private ImageView imageView;
    private TextView textView;
    String[] str = new String[]{"首页","分类","活动"};
    int[] arr_normal = new int[]{R.mipmap.ic_tab_home_normal,R.mipmap.ic_tab_category_normal,R.mipmap.ic_tab_activity_normal};
    int[] arr_active = new int[]{R.mipmap.ic_tab_home_active,R.mipmap.ic_tab_category_active,R.mipmap.ic_tab_activity_active};
    int mPosition =0;

    public Tablayout_item(Context context) {
        super(context);
        mContext = context;
        intiView();
    }

    public Tablayout_item(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        intiView();
    }

    public Tablayout_item(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        intiView();
    }

    public Tablayout_item(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        intiView();
    }

    private void intiView(){
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView = new ImageView(mContext);
        imageView.setPadding(0,0,0,2);
        textView = new TextView(mContext);
        addView(imageView,lp);
        addView(textView,lp);
    }

    public void setImage_text(int position){
        mPosition = position;
        if(textView != null && imageView != null){
            imageView.setBackgroundResource(arr_normal[mPosition]);
            textView.setText(str[mPosition]);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if(selected == true){
            imageView.setBackgroundResource(arr_active[mPosition]);
            textView.setTextColor(Color.parseColor("#ff917e"));
        }else{
            imageView.setBackgroundResource(arr_normal[mPosition]);
            textView.setTextColor(Color.BLACK);
        }
    }
}
