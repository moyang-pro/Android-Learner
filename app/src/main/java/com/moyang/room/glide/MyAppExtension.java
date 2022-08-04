package com.moyang.room.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.moyang.room.R;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/3 23:49
 */
@GlideExtension
public class MyAppExtension {

    private MyAppExtension() {

    }

    @GlideOption
    public static BaseRequestOptions<?> defaultImage(BaseRequestOptions<?> options) {
        return options
                // 正在请求图片时展示的图片(默认)
                .placeholder(R.drawable.future)
                // 请求失败时展示的图片 不设置就展示placeholder
                .error(R.drawable.ocean)
                // 如果请求的url/mode为空时展示的图片 不设置就展示placeholder
                .fallback(R.drawable.lake);
    }
}
