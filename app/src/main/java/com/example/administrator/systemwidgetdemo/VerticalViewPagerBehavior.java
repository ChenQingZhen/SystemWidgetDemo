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

public class VerticalViewPagerBehavior implements IBehavior{
    private int mScreenHeight;
    private Scroller mScroller;
    private int mLastY;
    private int mStart;
    private int mEnd;
    private View mView;
    public VerticalViewPagerBehavior(Context context,View view) {
        mView=view;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart=mView.getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if (mView.getScrollY() < 0) {
                    dy = 0;
                }
                if (mView.getScrollY() > mView.getHeight() - mScreenHeight) {
                    dy = 0;
                }
                mView.scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
//                mEnd=getScrollY();
//                int dScrollY=mEnd-mStart;
                int dScrollY = checkAlignment();
                if(dScrollY>0){
                    if(dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,mView.getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,mView.getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{
                    if(-dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,mView.getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,mView.getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }
        mView.postInvalidate();
        return true;
    }
    private int checkAlignment() {
        int mEnd = mView.getScrollY();
        boolean isUp = ((mEnd - mStart) > 0) ? true : false;
        int lastPrev = mEnd % mScreenHeight;
        int lastNext = mScreenHeight - lastPrev;
        if (isUp) {
            //向上的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mView.scrollTo(0, mScroller.getCurrY());
            mView.postInvalidate();
        }
    }
}
