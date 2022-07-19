package com.moyang.room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moyang.room.layout.LayoutLearnActivity;
import com.moyang.room.view.ViewLearnActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button learnViewBt = findViewById(R.id.bt_learn_view);
        learnViewBt.setOnClickListener(this);
        Button learnLayoutBt = findViewById(R.id.bt_learn_layout);
        learnLayoutBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_learn_view:
                startMyActivity(ViewLearnActivity.class);
                break;
            case R.id.bt_learn_layout:
                startMyActivity(LayoutLearnActivity.class);
                break;
        }
    }

    private void startMyActivity(Class<?> className) {
        Intent intent = new Intent(MainActivity.this, className);
        startActivity(intent);
    }
}