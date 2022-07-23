package com.moyang.room.view;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.moyang.room.R;
import com.moyang.room.view.listview.ListActivity;
import com.moyang.room.view.listview.RecycleActivity;
import com.moyang.room.view.viewpager.ViewPager2Activity;
import com.moyang.room.view.viewpager.ViewPagerActivity;

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

        Button send_btn = findViewById(R.id.bt_send_notify);
        Button cancel_btn = findViewById(R.id.bt_cancel_notify);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("moyang", "测试",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotifyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new  NotificationCompat.Builder(this, "moyang")
                .setContentTitle("官方通知")
                .setContentText("世界这么大， 我想去走走")
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.saint))
                .setColor(Color.parseColor("#ff0000"))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        send_btn.setOnClickListener(v -> {
            manager.notify(1, notification);
        });

        cancel_btn.setOnClickListener(v -> {
            manager.cancel(1);
        });

        Button btn_alert = findViewById(R.id.bt_alert);
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog_customer, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewLearnActivity.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("今天天气怎么样")
                        .setMessage("今天天气好极了")
                        .setView(dialogView)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("moyang99", "点击了确定按钮");
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("moyang99", "点击了取消按钮");
                            }
                        })
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("moyang99", "点击了OK按钮");
                            }
                        })
                        .create().show();
            }
        });

        Button btn_popup = findViewById(R.id.bt_popup);
        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
                // PopupWindow popupWindow = new PopupWindow(popupView);
                PopupWindow popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAsDropDown(v);
                Log.i("moyang99", "focus: " + ViewLearnActivity.this.getCurrentFocus());
//                popupWindow.showAsDropDown(v, v.getWidth(), -v.getHeight());
            }
        });


        Button btn_list = findViewById(R.id.bt_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewLearnActivity.this, ListActivity.class);
                startActivity(intent1);
            }
        });

        Button btn_recycle_list = findViewById(R.id.bt_recycle_list);
        btn_recycle_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewLearnActivity.this, RecycleActivity.class);
                startActivity(intent1);
            }
        });

        Button btn_vp = findViewById(R.id.bt_view_page);
        btn_vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewLearnActivity.this, ViewPagerActivity.class);
                startActivity(intent1);
            }
        });

        Button btn_vp2 = findViewById(R.id.bt_view_page2);
        btn_vp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewLearnActivity.this, ViewPager2Activity.class);
                startActivity(intent1);
            }
        });
    }
}