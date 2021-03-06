package com.example.administrator.systemwidgetdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, MyViewTestActivity.class);
    }

    public void btnTeachingView(View view) {
        mIntent.putExtra("flag", 0);
        startActivity(mIntent);
    }

    public void btnMyTextView(View view) {
        mIntent.putExtra("flag", 1);
        startActivity(mIntent);
    }

    public void btnShineTextView(View view) {
        mIntent.putExtra("flag", 2);
        startActivity(mIntent);
    }

    public void btnCircleProgressView(View view){
        mIntent.putExtra("flag",3);
        startActivity(mIntent);
    }
    public void btnVolumeView(View view){
        mIntent.putExtra("flag",4);
        startActivity(mIntent);
    }
    public void btnMyScrollView(View view){
        mIntent.putExtra("flag",5);
        startActivity(mIntent);
    }
    public void btnCqzShineTextView(View view){
        mIntent.putExtra("flag",6);
        startActivity(mIntent);
    }
    public void btnCqzScrollView(View view){
        mIntent.putExtra("flag",7);
        startActivity(mIntent);
    }
    public void TopBar(View view) {
        Intent intent = new Intent(this, TopBarActivity.class);
        startActivity(intent);
    }
    public void btnMyHorizontalScrollView(View view){
        Intent intent = new Intent(this, MyHorizontalScrollViewTestActivity.class);
        startActivity(intent);
    }
}
