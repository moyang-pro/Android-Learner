package com.moyang.room.provider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/7/31 16:10
 */
public class RtDbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "moyang_room.db";
    private static final String TAG = "RtDbHelper";

    public static final String TABLE_NAME_FOOD = "food";

    public final static String FOOD_COL_NAME = "name";
    public final static String FOOD_COL_PRICE = "price";

    public RtDbHelper(@Nullable Context context, int version) {
        super(context, DB_NAME, null, version);
    }


    private final static String CREATE_TABLE_SQL = "create table " + TABLE_NAME_FOOD +
            " ( id integer primary key autoincrement, "
            + FOOD_COL_NAME + " text, "
            + FOOD_COL_PRICE + " real )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "RtDbHelper onCreate ....");
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "RtDbHelper onUpgrade ....  newVersion === " + newVersion);
        // 新增表格在这里处理, newVersion ++
        db.execSQL(CREATE_TABLE_SQL);
    }
}
