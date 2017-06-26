package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/6/26.
 */

public class HorizontalViewPagerBehavior implements IBehavior {
    private int mScreenWidth;
    private Scroller mScroller;
    private int mLastX;
    private int mStart;
    private int mEnd;
    private View mView;
    public HorizontalViewPagerBehavior(Context context, View view) {
        mView=view;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mStart=mView.getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dx = mLastX - x;
                if (mView.getScrollX() < 0) {
                    dx = 0;
                }
                if (mView.getScrollX() > mView.getWidth() - mScreenWidth) {
                    dx = 0;
                }
                mView.scrollBy(dx, 0);
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
//                mEnd=getScrollX();
//                int dScrollY=mEnd-mStart;
                int dScrollX = checkAlignment();
                if(dScrollX>0){
                    if(dScrollX< mScreenWidth /3){
                        mScroller.startScroll(mView.getScrollX(),0,-dScrollX,0);
                    }else{
                        mScroller.startScroll(mView.getScrollX(),0, mScreenWidth -dScrollX,0);
                    }
                }else{
                    if(-dScrollX< mScreenWidth /3){
                        mScroller.startScroll(mView.getScrollX(),0,-dScrollX,0);
                    }else{
                        mScroller.startScroll(mView.getScrollX(),0,-mScreenWidth -dScrollX,0);
                    }
                }
                break;
        }
        mView.postInvalidate();
        return true;
    }
    private int checkAlignment() {
        int mEnd = mView.getScrollX();
        boolean isLeft = ((mEnd - mStart) > 0) ? true : false;
        int lastPrev = mEnd % mScreenWidth;
        int lastNext = mScreenWidth - lastPrev;
        if (isLeft) {
            //向左的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mView.scrollTo(mScroller.getCurrX(), 0);
            mView.postInvalidate();
        }
    }
}
