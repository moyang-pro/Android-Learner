package com.moyang.room.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.Rotate;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.moyang.room.R;

public class GlideLearnActivity extends AppCompatActivity {

    String url = "data:image/jpeg;base64,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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_learn);

        Button button = findViewById(R.id.btn_request);

        RequestOptions options = new RequestOptions()
                // 正在请求图片时展示的图片(默认)
                .placeholder(R.drawable.future)
                // 请求失败时展示的图片 不设置就展示placeholder
                .error(R.drawable.ocean)
                // 如果请求的url/mode为空时展示的图片 不设置就展示placeholder
                .fallback(R.drawable.lake)
                .override(200, 200);

        ImageView imageView = findViewById(R.id.iv_glide);


        DrawableCrossFadeFactory fadeFactory = new DrawableCrossFadeFactory.Builder()
                .setCrossFadeEnabled(true).build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(GlideLearnActivity.this)
//                        // 默认使用drawable
////                        .asBitmap()
//                        .load(url)
//                        .apply(options)
//                        .transition(DrawableTransitionOptions.withCrossFade(2000))
////                        .centerCrop()
//                        // 针对jpeg生效
////                        .transform(new RoundedCorners(50))
//                        .transform(new Rotate(90))
//                        .into(imageView);

                GlideApp.with(GlideLearnActivity.this).load(url).defaultImage().into(imageView);;
            }
        });
    }
}