package com.example.zff.roundrectpicturetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/*方法一
*BitmapShader用于自定义view
 */
public class BitmapShaderImageView extends View {

    private Bitmap defaultBitmap;
    private RectF rect;
    private Paint mPaint;
    private Paint textPaint;
    private int mWidth = (int) getResources().getDimension(R.dimen.view_width);
    private int mHight = (int) getResources().getDimension(R.dimen.view_height);
    private int mMinWidth = Math.min(mWidth,mHight);
    String mURL = null;
    private ImageLoadingListener loadingListener = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
               if(mURL.equals(imageUri)){
                   if(loadedImage != null){
//                       defaultBitmap.recycle();
                       defaultBitmap = loadedImage;

                       if(defaultBitmap != null){
                           BitmapShader bs = new BitmapShader(defaultBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                           mPaint.setShader(bs);
                       }else{
                           mPaint.setShader(null);
                       }
                   }
                   invalidate();
               }
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    };

    public BitmapShaderImageView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public void setBitmap(Bitmap bitmap){
//        if(defaultBitmap != bitmap) {
//            defaultBitmap = bitmap;
//            if (defaultBitmap != null) {
//                BitmapShader bs = new BitmapShader(defaultBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//                mPaint.setShader(bs);
//            } else {
//                mPaint.setShader(null);
//            }
//            invalidate();
//        }
//    }

    public void setUrl(String url){
        if(url != null) {
            mURL = url;
            ImageLoader.getInstance().loadImage(mURL, new ImageSize(mWidth-2, mHight-2), null, this.loadingListener);
        }
    }

    private void init(){
        rect = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new TextPaint();
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) getResources().getDimension(R.dimen.view_width), (int) getResources().getDimension(R.dimen.view_height));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(defaultBitmap != null){
            //画圆角矩形
            rect.set(0,0,mWidth,mHight);
            canvas.drawRoundRect(rect,25,25,mPaint);
            canvas.translate((mWidth/3)*2,70);


            //画圆形
//            canvas.drawCircle(mMinWidth/2,mMinWidth/2,mMinWidth/2,mPaint);

        }

        //这里需要根据文字的长度来调节
        canvas.translate(mMinWidth/2,70);
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setTextSize(60);
        canvas.drawText("Test",1.0f,1.0f,textPaint);
    }
}
