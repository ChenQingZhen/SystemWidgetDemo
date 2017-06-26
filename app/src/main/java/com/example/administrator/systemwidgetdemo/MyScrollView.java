package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class MyScrollView extends ViewGroup {
    private int mScreenHeight;
    private IBehavior mBehavior;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBehavior=new VerticalViewPagerBehavior(context,this);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l, mScreenHeight * i, r, mScreenHeight * (i + 1));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return mBehavior.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        mBehavior.computeScroll();
    }
}
