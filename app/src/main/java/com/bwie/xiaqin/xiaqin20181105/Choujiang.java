package com.bwie.xiaqin.xiaqin20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by lenovo on 2018/11/5.
 */

public class Choujiang extends View implements View.OnClickListener{
    private int[] color = new int[]{Color.parseColor("#8EE5EE"),Color.parseColor("#FFD700"),Color.parseColor("#FFD39B"),Color.parseColor("#FF8247"),Color.parseColor("#FF34B3"),Color.parseColor("#F0E68C")};
    private String[] textColor = new String[]{"一等奖","二等奖","三等奖","四等奖","参与奖","谢谢参与"};
    private int mWidthPixels;
    private int mHeightPixels;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Animation mAnimation;
    private boolean isRote;


    public Choujiang(Context context) {
        this(context,null);
    }

    public Choujiang(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Choujiang(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mWidthPixels = displayMetrics.widthPixels;
        mHeightPixels = displayMetrics.heightPixels;
        mWidth = mWidthPixels / 2;
        mHeight = mHeightPixels / 2;
        initPaint();
        initAnimation();
        setOnClickListener(this);
    }

    private void initAnimation() {
        mAnimation = new RotateAnimation(0, 360, mWidth, mHeight);
        //设置重复次数
        mAnimation.setRepeatCount(-1);
        mAnimation.setFillAfter(true);
        mAnimation.setInterpolator(new LinearInterpolator());
        //设置重复模式
        mAnimation.setRepeatMode(Animation.RESTART);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth,mHeight);
        RectF rectF = new RectF(-260, -260, 260, 260);
        float start = 60;
        for (int i = 0;i < 6;i++){
          mPaint.setColor(color[i]);
          canvas.drawArc(rectF,start*i,60,true,mPaint);

        }
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0,0,100,mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(20);

        Rect rect = new Rect();
        mPaint.getTextBounds("start",0,5,rect);
        int width = rect.width();
        int height = rect.height();
        canvas.drawText("start",-width/2,height/2,mPaint);
        RectF rectF1 = new RectF(-200, -200, 200, 200);
        for (int i = 0;i < 6;i++){
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(20);
            Path path = new Path();
            path.addArc(rectF1,start*i+20,60);
            canvas.drawTextOnPath(textColor[i],path,0,0,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private void start(){
        isRote = true;
    }
    private void stop(){
        isRote = false;
    }

    @Override
    public void onClick(View v) {
        if (isRote){
            stop();
            radom();
        }else {
            start();
        }
    }

    private void radom() {
        double random = Math.random();
        RotateAnimation rotateAnimation = new RotateAnimation(0, (float) (random * 360), mWidth, mHeight);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        startAnimation(rotateAnimation);
    }
}
