package com.example.administrator.systemwidgetdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyHorizontalScrollViewTestActivity extends AppCompatActivity {
    private int mScreenHeight;
    private int mScreenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_horizontal_scroll_view_test);
        MyHorizontalScrollView myHorizontalScrollView= (MyHorizontalScrollView) findViewById(R.id.mhsv_test);

//        LinearLayout container= (LinearLayout) findViewById(R.id.container);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScreenWidth=dm.widthPixels;
        ImageView im1=new ImageView(this);
        im1.setImageResource(R.mipmap.test1);
        ImageView im2=new ImageView(this);
        im2.setImageResource(R.mipmap.test2);
        ImageView im3=new ImageView(this);
        im3.setImageResource(R.mipmap.test3);
//        container.addView(im1,mScreenWidth,mScreenHeight);
//        container.addView(im2,mScreenWidth,mScreenHeight);
//        container.addView(im3,mScreenWidth,mScreenHeight);
        myHorizontalScrollView.addView(im1);
        myHorizontalScrollView.addView(im2);
        myHorizontalScrollView.addView(im3);
    }
}
