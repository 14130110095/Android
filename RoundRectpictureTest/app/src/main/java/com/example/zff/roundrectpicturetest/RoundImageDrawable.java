package com.example.zff.roundrectpicturetest;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RoundImageDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF rectF;
    private int mWidth;
    int mShape = -1;

    //int i ,i =0是圆角矩形，i= 1是圆形图片
    public RoundImageDrawable(Bitmap bitmap,int i){
        mShape = i;
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
        mWidth = Math.min(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left,top,right,bottom);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if(mShape == 0){
            canvas.drawRoundRect(rectF,25,25,mPaint);
        }else{
            canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint);
        }

    }

    @Override
    public int getIntrinsicWidth() {
        if(mShape == 0){
            return mBitmap.getWidth();
        }else{
            return mWidth;
        }

    }

    @Override
    public int getIntrinsicHeight() {
        if(mShape == 0){
            return super.getIntrinsicHeight();
        }else{
            return mWidth;
        }

    }

    @Override
    public void setAlpha(int alpha) {
         mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
         mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
