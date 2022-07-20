package com.moyang.room.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moyang.room.R;

public class AnimLearnActivity extends AppCompatActivity {

    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_learn);
        Toast.makeText(this, "动画学习活动正在启动...", Toast.LENGTH_LONG).show();

        ImageView imageView = findViewById(R.id.image_anim);

        Button bt_anim_frame = findViewById(R.id.bt_anim_frame);

        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        bt_anim_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    animationDrawable.start();
                    flag = true;
                } else {
                    animationDrawable.stop();
                    flag = false;
                }
            }
        });

        Button bt_anim_between = findViewById(R.id.bt_anim_between);
        ImageView imageView2 = findViewById(R.id.image_anim_2);
        bt_anim_between.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 透明度
//                Animation animation = AnimationUtils.loadAnimation
//                        (AnimLearnActivity.this, R.anim.alpha);
//                imageView2.startAnimation(animation);

                // 旋转
//                Animation animation = AnimationUtils.loadAnimation
//                        (AnimLearnActivity.this, R.anim.ratate);
//                imageView2.startAnimation(animation);

                // 缩放
//                Animation animation = AnimationUtils.loadAnimation
//                        (AnimLearnActivity.this, R.anim.scale);
//                imageView2.startAnimation(animation);

                // 平移
                Animation animation = AnimationUtils.loadAnimation
                        (AnimLearnActivity.this, R.anim.translate);
                imageView2.startAnimation(animation);
            }
        });

        Button bt_anim_value = findViewById(R.id.bt_anim_value);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();
                Log.i("moyang99", "val:" + val);
            }
        });

        TextView textView = findViewById(R.id.tv_anim);
        ObjectAnimator objectAnimator  = ObjectAnimator.ofFloat
                (textView, "alpha" ,0f, 1f);
        objectAnimator.setDuration(4000);

        bt_anim_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator.start();
                objectAnimator.start();
            }
        });

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 动画开始时调用
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画结束时调用

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 动画取消时调用

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // 动画重复时调用

            }
        });

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });


    }
}