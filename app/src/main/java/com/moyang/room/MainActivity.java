package com.moyang.room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moyang.room.anim.AnimLearnActivity;
import com.moyang.room.broadcast.BroadCastLearnActivity;
import com.moyang.room.database.DataBaseLearnActivity;
import com.moyang.room.file.FileLearnActivity;
import com.moyang.room.file.SharedPreferencesLearnActivity;
import com.moyang.room.fragment.FragmentLearnActivity;
import com.moyang.room.glide.GlideLearnActivity;
import com.moyang.room.layout.LayoutLearnActivity;
import com.moyang.room.provider.ContentProviderLearnActivity;
import com.moyang.room.service.ServiceLearnActivity;
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
        Button learnAnimBt = findViewById(R.id.bt_learn_anim);
        learnAnimBt.setOnClickListener(this);
        Button learnFragmentBt = findViewById(R.id.bt_learn_fragment);
        learnFragmentBt.setOnClickListener(this);
        Button learnBroadcastBt = findViewById(R.id.bt_learn_broadcast);
        learnBroadcastBt.setOnClickListener(this);
        Button learnFileBt = findViewById(R.id.bt_learn_file);
        learnFileBt.setOnClickListener(this);
        Button learnSpBt = findViewById(R.id.bt_learn_sp);
        learnSpBt.setOnClickListener(this);
        Button learnDbBt = findViewById(R.id.bt_learn_db);
        learnDbBt.setOnClickListener(this);
        Button learnServiceBt = findViewById(R.id.bt_learn_service);
        learnServiceBt.setOnClickListener(this);
        Button learnProviderBt = findViewById(R.id.bt_learn_provider);
        learnProviderBt.setOnClickListener(this);
        Button learnGlideBt = findViewById(R.id.bt_learn_glide);
        learnGlideBt.setOnClickListener(this);
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
            case R.id.bt_learn_anim:
                startMyActivity(AnimLearnActivity.class);
                break;
            case R.id.bt_learn_fragment:
                startMyActivity(FragmentLearnActivity.class);
                break;
            case R.id.bt_learn_broadcast:
                startMyActivity(BroadCastLearnActivity.class);
                break;
            case R.id.bt_learn_file:
                startMyActivity(FileLearnActivity.class);
                break;
            case R.id.bt_learn_sp:
                startMyActivity(SharedPreferencesLearnActivity.class);
                break;
            case R.id.bt_learn_db:
                startMyActivity(DataBaseLearnActivity.class);
                break;
            case R.id.bt_learn_service:
                startMyActivity(ServiceLearnActivity.class);
                break;
            case R.id.bt_learn_provider:
                startMyActivity(ContentProviderLearnActivity.class);
                break;
            case R.id.bt_learn_glide:
                startMyActivity(GlideLearnActivity.class);
                break;
            default:
                break;
        }
    }

    private void startMyActivity(Class<?> className) {
        Intent intent = new Intent(MainActivity.this, className);
        startActivity(intent);
    }
}