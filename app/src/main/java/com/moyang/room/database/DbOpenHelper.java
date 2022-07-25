package com.moyang.room.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/7/25 0:10
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "moyang_room.db";
    private static final String TAG = "DbOpenHelper";

    public final static String TABLE_BOOK = "Book";
    public final static String BOOK_COL_NAME = "name";
    public final static String BOOK_COL_AUTHOR = "author";
    public final static String BOOK_COL_PRICE = "price";
    public final static String BOOK_COL_PAGE = "pageNum";

    private final static String CREATE_TABLE_SQL = "create table " + TABLE_BOOK +
            " ( id integer primary key autoincrement, "
            + BOOK_COL_NAME + " text, "
            + BOOK_COL_AUTHOR + " text, "
            + BOOK_COL_PRICE + " real, "
            + BOOK_COL_PAGE + " integer)";

    public DbOpenHelper(@Nullable Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "DbOpenHelper onCreate ....");
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "DbOpenHelper onUpgrade ....  newVersion === " + newVersion);
        // 新增表格在这里处理, newVersion ++
    }
}
