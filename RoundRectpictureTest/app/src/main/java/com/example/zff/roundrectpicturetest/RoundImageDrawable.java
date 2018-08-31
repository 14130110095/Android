package com.example.zff.roundrectpicturetest;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RoundImageDrawable extends Drawable {

    private Paint mPaint;
    private Bitmap mBitmap;
    private RectF rectF;
    private Rect srcrect;
    private int mWidth;
    int mShape = -1;
    int[] mDirection = null;
    int roundX = 25;
    int roundY = 25;

    //shape=0是圆角矩形，shape= 1是圆形图片
    //direction是指哪个角不是圆角，左上1，右上2，左下3，右下4，如果全部指定，则不做圆角处理
    public RoundImageDrawable(Bitmap bitmap,int shape,int[] direction){
        mShape = shape;
        mDirection = direction;
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        if((mDirection == null && mShape == 1) || (mShape == 0 && (mDirection == null || mDirection.length != 4))){
            BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mPaint.setShader(bitmapShader);
        }
        mWidth = Math.min(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left,top,right,bottom);
        srcrect = new Rect(left,top,right,bottom);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //四个角都不切圆角
        if(mDirection != null && mDirection.length == 4){
            canvas.drawBitmap(mBitmap,srcrect,rectF,mPaint);
        }
        //矩形四个角都切圆角，或者是切圆形
        else if(mDirection == null){
            if(mShape == 0){
                canvas.drawRoundRect(rectF, roundX, roundY, mPaint);
            }else{
                canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
            }
        }
        //矩形某个角不切圆角
        else if(mDirection != null && mDirection.length != 4){
            canvas.drawRoundRect(rectF, roundX, roundY, mPaint);
            repairCorner(canvas);
        }
    }

    //先全部切圆角，这里补全
    public void repairCorner(Canvas canvas){
        for(int i = 0;i<mDirection.length;i++){
            if(mDirection[i] == 1){
                 final Rect rect = new Rect(srcrect.left,srcrect.top,roundX,roundY);
                 canvas.drawRect(rect,mPaint);
            }
            if(mDirection[i] == 2){
                final Rect rect = new Rect(srcrect.right-roundX,0,srcrect.right,roundY);
                canvas.drawRect(rect,mPaint);
            }
            if(mDirection[i] == 3){
                final Rect rect = new Rect(0,srcrect.bottom-roundY,roundX, srcrect.bottom);
                canvas.drawRect(rect,mPaint);
            }
            if(mDirection[i] == 4){
                final Rect rect = new Rect(srcrect.right - roundX,srcrect.bottom-roundY, srcrect.right, srcrect.bottom);
                canvas.drawRect(rect,mPaint);
            }
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
