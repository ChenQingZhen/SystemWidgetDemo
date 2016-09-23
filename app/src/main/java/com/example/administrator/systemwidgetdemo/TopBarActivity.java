package com.example.administrator.systemwidgetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TopBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);
        initView();
    }

    private void initView() {
        TopBar topBar= (TopBar) findViewById(R.id.top_bar);
        topBar.setOnTopbarClickListener(new TopBar.TopbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBarActivity.this,"左按钮",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBarActivity.this,"右按钮",Toast.LENGTH_SHORT).show();
            }
        });
        topBar.setButtonVisable(1,false);
    }
}
