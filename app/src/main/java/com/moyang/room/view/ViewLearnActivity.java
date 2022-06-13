package com.moyang.room.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moyang.room.R;

public class ViewLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_learn);
        Toast.makeText(this, "视图学习活动正在启动...", Toast.LENGTH_LONG).show();

        TextView textView = findViewById(R.id.tv_title_one);
//        跑马灯效果必须要让控件获取焦点
//        textView.requestFocus();

        Button button = findViewById(R.id.bt_drawable);

        // 点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("moyang99", "onClick");
            }
        });

        // 长按事件
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("moyang99", "onLongClick");
                return false;
            }
        });

        // 触摸事件
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("moyang99", "onTouch: " + event.getAction());
                return false;
            }
        });
    }
}