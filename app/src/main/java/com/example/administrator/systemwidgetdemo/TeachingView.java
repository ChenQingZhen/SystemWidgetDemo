package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class TeachingView extends View {
    private Bitmap mBitmap1;
    private Bitmap mBitmap2;
    private Bitmap mMutableBitmap;

    public TeachingView(Context context) {
        this(context, null);
    }

    public TeachingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mBitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.landscape1);
        mBitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.landscape2);
        mMutableBitmap = mBitmap2.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas1 = new Canvas(mMutableBitmap);
        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        p1.setTextSize(20);
        canvas1.drawText("风景1", 20, 20, p1);
        Paint p2=new Paint();
        p2.setColor(Color.YELLOW);
        canvas1.drawLine(0,40,200,40,p2);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap1, 0, 0, null);
        canvas.drawBitmap(mMutableBitmap,0, 0, null);
    }


}