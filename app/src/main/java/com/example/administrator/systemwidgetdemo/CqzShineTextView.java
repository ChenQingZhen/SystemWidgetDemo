package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by chenqz on 2016/9/30.
 */
public class CqzShineTextView extends TextView {
    private int mWidth;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private Paint mPaint;
    private float mDx;

    public CqzShineTextView(Context context) {
        this(context, null);
    }

    public CqzShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        Log.d("cqz_text", "mWidth=" + mWidth);
        mPaint = getPaint();
        mLinearGradient = new LinearGradient(0, 0, mWidth, getMeasuredHeight(), new int[]{ Color.RED,Color.TRANSPARENT,Color.RED, Color.RED, Color.RED,Color.RED,Color.RED}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDx > mWidth) {
            mDx =-mWidth;
        }
        mMatrix.setTranslate(mDx, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(100);
        mDx += mWidth/8;
    }

    private Handler mHandler = new Handler();
}
