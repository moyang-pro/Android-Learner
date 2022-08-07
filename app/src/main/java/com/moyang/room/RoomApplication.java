package com.moyang.room;

import android.app.Application;
import android.content.Context;


/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/3 22:27
 */
public class RoomApplication extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    public static Context getContext() {
        return mApplicationContext;
    }
}
