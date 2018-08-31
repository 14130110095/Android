package com.example.zff.recyclerviewfocusboxxml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.widget.PopupMenu;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContextListItem extends LinearLayout{

    private TextView mTextView;
    private ImageView imageView;
    private ItemInfo itemInfo;
    private Paint mPaint;
    private Context mContext;

    public ContextListItem(Context context) {
        super(context);
        mContext = context;
    }

    public ContextListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ContextListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
//        setDividerDrawable(mContext.getDrawable(R.color.colorPrimary));
//        setOrientation(VERTICAL);
    }


    private void initView(){
        mTextView = (TextView)findViewById(R.id.textview);
        imageView = (ImageView)findViewById(R.id.imageview);
    }


    public void setInfo(ItemInfo info,int position){
        itemInfo = info;
        imageView.setImageResource(itemInfo.getPicture());
        mTextView.setText(itemInfo.getPicname());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
