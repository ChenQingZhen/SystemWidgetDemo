package com.example.administrator.systemwidgetdemo;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/6/26.
 */

public interface IBehavior {
    boolean onTouchEvent(MotionEvent event);
    void computeScroll();
}
