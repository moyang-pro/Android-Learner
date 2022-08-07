package com.moyang.room.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.moyang.room.RoomApplication;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/8/7 19:50
 */
@Database(entities = {User.class}, version = 2)
public abstract class AppBaseDatabase extends RoomDatabase {

    private static AppBaseDatabase INSTANCE;

    public synchronized static AppBaseDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            return Room.databaseBuilder(context,
                    AppBaseDatabase.class, "user_room").build();
        }
        return INSTANCE;
    }

    // 用户只需要操作dao,我们必须暴露dao对象
    public abstract UserDao userDao();
}
