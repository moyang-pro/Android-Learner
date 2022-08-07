package com.moyang.room.database.room;

import android.content.Context;

import com.moyang.room.RoomApplication;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 20:11
 */
public class AppDatabase {

    public static UserDao userDao(Context context) {
        AppBaseDatabase database = AppBaseDatabase.getInstance(context);
        return database.userDao();
    }
}
