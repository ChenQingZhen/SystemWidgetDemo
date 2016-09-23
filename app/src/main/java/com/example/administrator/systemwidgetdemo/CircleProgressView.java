package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class CircleProgressView extends View {
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private float mCircleXY;
    private float mSweepAngle;
    private float mSweepValue = 66;

    private float mRadius;
    private Paint mCirclePaint;
    private RectF mArcRectF;
    private Paint mArcPaint;

    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;


    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasuredWidth, mMeasuredHeight);
        initView();
    }

    private void initView() {
        float length = 0;
        if (mMeasuredWidth > mMeasuredHeight) {
            length = mMeasuredHeight;
        } else {
            length = mMeasuredWidth;
        }
        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        mArcRectF = new RectF((float) (length * 0.1), (float) (length * 0.1), (float) (length * 0.9), (float) (length * 0.9));
        mSweepAngle = (mSweepValue / 100f) * 360f;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        //绘制弧线
        canvas.drawArc(mArcRectF,270,mSweepAngle,false,mArcPaint);
        //绘制文字
        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY,mCircleXY+(mShowTextSize/4),mTextPaint);
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    public void forceInvalidate(){
        this.invalidate();
    }

    public void setSweepValue(int sweepValue){
        if(sweepValue!=0){
            mSweepAngle=sweepValue;
        }else{
            mSweepAngle=25;
        }
        this.invalidate();
    }

}
