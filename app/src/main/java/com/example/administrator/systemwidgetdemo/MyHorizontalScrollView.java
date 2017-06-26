package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2017/6/26.
 */

public class MyHorizontalScrollView extends ViewGroup{
    private IBehavior mBehavior;
    private int mScreenWidth;
    public MyHorizontalScrollView(Context context) {
        this(context,null);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBehavior=new HorizontalViewPagerBehavior(context,this);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        {
            int childCount = getChildCount();
            MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
            mlp.width = mScreenWidth * childCount;
            setLayoutParams(mlp);
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE) {
                    child.layout(mScreenWidth * i,t, mScreenWidth * (i + 1) ,b);
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mBehavior.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        mBehavior.computeScroll();
    }
}
