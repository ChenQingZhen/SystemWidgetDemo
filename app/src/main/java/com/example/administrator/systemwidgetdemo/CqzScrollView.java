package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by chenqz on 2016/10/4.
 */
public class CqzScrollView extends ViewGroup {
    private float mLastY;
    private int mScreenHeight;
    private Scroller mScroller;
    private int mStart;

    public CqzScrollView(Context context) {
        this(context, null);
    }

    public CqzScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller=new Scroller(context);
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
        int count = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = count * mScreenHeight;
        setLayoutParams(mlp);
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                childView.layout(l, mScreenHeight * i, r, mScreenHeight * (i + 1));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y=event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("cqz_touch","ACTION_DOWN");
                mLastY = y;
                mStart= getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("cqz_touch","ACTION_MOVE");
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = (int) (y - mLastY);
                if(getScrollY()<0){
                    dy=0;
                }
                if(getScrollY()>getHeight()-mScreenHeight){
                    dy=0;
                }
                scrollBy(0, -dy);
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP:
                int end=getScrollY();
                int distance=end-mStart;
                Log.d("cqz_touch","ACTION_UP  distance="+distance);
                if(Math.abs(distance)<mScreenHeight/3){
                    mScroller.startScroll(0,getScrollY(),0,-distance);
                }else{
                    mScroller.startScroll(0,getScrollY(),0,distance>0? mScreenHeight-distance :-mScreenHeight-distance);
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
           scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
