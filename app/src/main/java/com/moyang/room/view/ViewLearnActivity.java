package com.moyang.room.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
        EditText editText = findViewById(R.id.et_input);


        // 点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("moyang99", "onClick");
                Log.i("moyang99", "editText.getText(): " + editText.getText());
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


        ImageView imageView = findViewById(R.id.imv_one);
        ProgressBar progressBar = findViewById(R.id.pb_one);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = progressBar.getProgress();
                progressBar.setProgress(progress + 10);
            }
        });
    }
}