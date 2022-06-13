package com.moyang.room.view.textview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/6/12 23:19
 */
public class TitleTextView extends AppCompatTextView {
    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
