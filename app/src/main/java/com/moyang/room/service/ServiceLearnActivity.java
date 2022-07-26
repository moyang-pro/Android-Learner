package com.moyang.room.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moyang.room.R;

/**
 * 1. android中的进程与线程
 *   1) 每一个APP就是一个进程,进程默认开启一个主线程(UI线程)
 *   2) 线程之间进行数据共享, 但进程之间无法直接进行数据共享
 *   3) 主线程才能更新UI !!!
 *   4) 不要把耗时的操作放在主线程
 * 2. 如何开启一个子线程?
 *   1) extends Thread 对象
 *   2) implements Runnable接口
 * --- 调用start方法开启
 * 注意事项: 开启线程执行后,哪一个线程先执行,无法预期(与线程调度有关)
 * 3. 如何在子线程中去更新UI?
 *   -- 自定义Handler类,复写handleMessage方法,(根据message的what/arg1/obj可以传递数据)
 *   -- 在主线程中实例化Handler对象
 *   -- 在子线程中通过Handler对象的sendMessage发送消息
 * 4. Handler如何切换线程
 *   -- 每个线程绑定一个Looper对象,主线程默认有looper
 *   -- 子线程调用sendMessage方法,会把消息发送到MessageQueue中
 *   -- 主线程Looper会去MessageQueue中取message对象,代码是在主线程中执行
 *   -- 主线程Looper会调用handle的handleMessage方法
 * 5. AsyncTask:
 *   -- 三个参数 Params, Progress, Result
 *   -- onPreExecute : 主线程 初始化
 *   -- doInBackground : 子线程 任务执行
 *   -- onProgressUpdate : 子线程 进度更新
 *   -- onPostExecute: 主线程 任务完成
 * 6. Service如何使用
 *   1) startService + stopService
 *     -- 生命周期的回调:
 *        - onCreate 只在第一次创建时调用
 *        - onStartCommand: 每次startService都调用
 *        - onDestroy: stopService时调用
 *     -- 不管调用几次startService,只需要调用一次stopService
 *   2) bindService + unbindService: 这种方式Service可以和Activity通信
 *     -- 如何使用:
 *        - 在Service的onBind方法返回一个自定义的Bind对象
 *        - 在Activity中定义一个 ServiceConnection 对象,复写其中的 onServiceConnected, 这里的binder就是Service返回的binder对象
 *        - 通过调用bindService(intent, myConnection, BIND_AUTO_CREATE); 就可以建立连接
 *        - 通过 unbindService(myConnection); 就可以取消连接
 *     -- 生命周期:
 *        - onCreate: 只在第一次创建时调用
 *        - onBind: 建立连接的时候回调,多次bindService不会一直回调
 *        - ServiceConnection.onServiceConnected: 只在第一次建立连接时调用
 *        - ServiceConnection.onServiceDisConnected: unbindService不会调用,这个方法用于非正常断开连接时重新连接
 *        - onDestroy: unbindService时调用
 *    Service可以被多个活动共用
 * 7. 特殊的service
 *    1) 前台Service: 挂载在通知上, Service可以在Activity退出到后台仍然运行
 *       开启方式 : 新建一个通知 -> startForeground
 *    2) IntentService: 后台进行任务的Service,而且任务进行完后能自动退出
 */
public class ServiceLearnActivity extends AppCompatActivity {

    private static final String TAG = "ServiceLearnActivity";

    UiHandler uiHandler = new UiHandler();

    private MyService.MyBinder myBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_learn);
        Log.d(TAG, "onCreate Thread Name " + Thread.currentThread().getName());
        Button button = findViewById(R.id.btn_thread);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new MyThread().start();
                // new MyRunnable().run();
                new MyAsyncTask().execute();
            }
        });

        Intent intent = new Intent(this, MyService.class);

        Button buttonServiceStart = findViewById(R.id.button_service);
        buttonServiceStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        Button buttonServiceEnd = findViewById(R.id.button_service_end);
        buttonServiceEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        MyConnection myConnection = new MyConnection();
        Button buttonServiceBind = findViewById(R.id.btn_bind_service);
        buttonServiceBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, myConnection, BIND_AUTO_CREATE);
            }
        });

        Button buttonServiceUnbind = findViewById(R.id.btn_unbind_service);
        buttonServiceUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myConnection);
            }
        });

        Button btnIntent = findViewById(R.id.button_intent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceLearnActivity.this, MyIntentService.class));
            }
        });

    }

    class MyThread extends Thread {

        @Override
        public void run() {
            Log.i("moyang99", "MyThread run ....");
            super.run();
        }
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            Log.i("moyang99", "MyRunnable run ....");
//            uiHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Log.i("moyang99", "uiHandler run ...." + Thread.currentThread().getName());
//                }
//            });
            Message message = new Message();
            message.what = 1;
            message.arg1 = 99;
            uiHandler.sendMessage(message);
        }
    }

    class UiHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                Log.i("moyang99", "uiHandler run ...." + msg.arg1+ Thread.currentThread().getName());
            }
            super.handleMessage(msg);
        }
    }

    class MyConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接回调
            myBinder = (MyService.MyBinder) service;
            if (myBinder != null) {
                myBinder.addNum();
            }
            Log.i("moyang99", "onServiceConnected " + Thread.currentThread().getName());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 重新连接service的操作, 并非是unBindService回调
            Log.i("moyang99", "onServiceDisconnected " + Thread.currentThread().getName());
        }
    }
}