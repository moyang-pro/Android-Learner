package com.moyang.room.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moyang.room.R;


/**
 * 广播的载体: 包含action的Intent
 * 广播的接收: BroadcastReceiver
 *
 * 1. 如何接收一个广播?
 * A) 动态注册: 代码里注册receiver
 *  --- 自定义一个Receiver类,复写onReceive方法, 根据intent.action区分不同的广播
 *  --- 新建intentFilter: 通过addAction添加感兴趣的广播类型
 *  --- registerReceiver 注册广播
 *  --- onDestroy中取消注册unregisterReceiver
 *  缺点: 只有app运行起来才能注册广播,如果app未运行不能收到广播
 * B) 静态注册: manifest文件里注册
    --- google限制静态广播可注册类型: 指定类型的系统广播; 指定packageName的自定义广播
    注册方式:
    --- 自定义一个Receiver类,复写onReceive方法, 根据intent.action区分不同的广播
    --- 在 manifest文件添加一个receiver,在intent-filter标签中添加感兴趣的广播
 * 2. 如何发送一个广播?
 * 广播的载体: 包含action的Intent
 *
 *   标准广播:
 *   1) 自定义一个action, Intent的action = 自定义广播的action, 通过sendBroadcast发送即可
 *   2) 如果接收方是静态注册,intent还必须指定packageName
 *
 *   有序广播:
 *   sendOrderedBroadcast(intent, permission);
 *   有序广播接收时:按照intent-filter的priority(-1000~1000)
 *   有序广播接收时,可以通过abortBroadcast()方法截断广播
 *
 */
public class BroadCastLearnActivity extends AppCompatActivity {

    private MyBroadcastReceiver myBroadcastReceiver;

    private static final String CUSTOM_BROADCAST = "moyang.intent.action.CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_learn);
        // 动态注册
        IntentFilter filter = new IntentFilter();
        // action: 对应感兴趣的广播类型
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(CUSTOM_BROADCAST);
        myBroadcastReceiver = new MyBroadcastReceiver();

        registerReceiver(myBroadcastReceiver, filter);

        Button button = findViewById(R.id.btn_send_broadcast);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 静态广播接受监听不到
                // sendBroadcast(new Intent(CUSTOM_BROADCAST));
                Intent intent = new Intent(CUSTOM_BROADCAST);
                intent.setPackage(getPackageName());
                // 标准广播
                // sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);
            }
        });
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("moyang99", "onReceive action ==== " + intent.getAction());
            Toast.makeText(context, "接收到广播啦...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册广播: 否则会出现内存泄漏
        unregisterReceiver(myBroadcastReceiver);
    }
}