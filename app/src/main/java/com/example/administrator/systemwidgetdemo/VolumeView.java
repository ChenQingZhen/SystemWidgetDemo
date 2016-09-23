package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class VolumeView extends View {
    private Paint mPaint;

    private int mRectCount;
    private int mWidth;
    private int mRectWidth;
    private int mRectHeight;
    private double mRandom;
    private int offset=5;

    private LinearGradient mLinearGradient;

    public VolumeView(Context context) {
        this(context,null);
    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mRectCount=12;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getWidth();
        mRectHeight=getHeight();
        mRectWidth=(int)(mWidth*0.6/mRectCount);
        mLinearGradient=new LinearGradient(0,0,mRectWidth,mRectHeight,Color.YELLOW,Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<mRectCount;i++){
            mRandom= Math.random();
            float currentHeight=(float) (mRectHeight*mRandom);
            canvas.drawRect((float) (mWidth*0.4/2+mRectWidth*i+offset),currentHeight,(float) (mWidth*0.4/2+mRectWidth*(i+1)),mRectHeight,mPaint);
        }
        postInvalidateDelayed(300);
    }
}
