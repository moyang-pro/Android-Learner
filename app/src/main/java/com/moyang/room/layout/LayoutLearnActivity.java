package com.moyang.room.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.moyang.room.R;

public class LayoutLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_learn);
        Toast.makeText(this, "布局学习活动正在启动...", Toast.LENGTH_LONG).show();
    }
}