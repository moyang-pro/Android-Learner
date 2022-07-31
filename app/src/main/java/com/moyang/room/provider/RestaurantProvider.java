package com.moyang.room.provider;

import static com.moyang.room.provider.RtDbHelper.TABLE_NAME_FOOD;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * URI 基本形式: content://authority/path
 * path:
 * food
 * food/1
 * food/# #表示任意数字
 * food/* *表示任意字符
 *
 * UriMatcher 判断是否相等
 *
 * MIME值的定义:
 * 1. 以vnd开头
 * 2. food结尾: android.cursor.dir/
 *    food/1结尾: android.cursor.item/
 *
 * 自定义 ContentProvider 步骤:
 * 1. 定义UriMatcher,确认自己关心的uri类型
 * 2. 重写CRUD方法和getType方法
 *    -- 判断DbHelper是否初始化成功
 *    -- 根据 UriMatcher.match(uri)的结果,做不同的事情
 * 3. 写getType方法:
 *    根据 UriMatcher.match(uri)的结果,返回不同的类型
 */
public class RestaurantProvider extends ContentProvider {

    public static final String AUTHORITY = "com.moyang.room";

    private static final int URI_FOOD = 0;

    // cook/#
    private static final int URI_FOOD_ITEM = 1;

    private final UriMatcher mUriMatcher;

    private RtDbHelper mDdbHelper;

    public RestaurantProvider() {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, TABLE_NAME_FOOD, URI_FOOD);
        mUriMatcher.addURI(AUTHORITY, TABLE_NAME_FOOD + "/#", URI_FOOD_ITEM);
    }

    @Override
    public boolean onCreate() {
        if (getContext() == null) {
            return false;
        }
        mDdbHelper = new RtDbHelper(getContext(), 4);
        return true;
    }


    // 返回删除的数据条数
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
         if (mDdbHelper == null) {
             return 0;
         }
         SQLiteDatabase database = mDdbHelper.getWritableDatabase();
         switch (mUriMatcher.match(uri)) {
             case URI_FOOD:
                 return database.delete(TABLE_NAME_FOOD, selection, selectionArgs);
             case URI_FOOD_ITEM:
                 return database.delete(TABLE_NAME_FOOD, "id = ?", new String[]{uri.getPathSegments().get(1)});
             default:
                 return 0;
         }
    }

    // 返回新插入的数据的uri
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (mDdbHelper == null) {
            return null;
        }
        SQLiteDatabase database = mDdbHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case URI_FOOD:
            case URI_FOOD_ITEM:
                long id =  database.insert(TABLE_NAME_FOOD, null, values);
                return Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME_FOOD + "/" + id);
            default:
                return null;
        }
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (mDdbHelper == null) {
            return null;
        }
        SQLiteDatabase database = mDdbHelper.getReadableDatabase();
        switch (mUriMatcher.match(uri)) {
            case URI_FOOD:
                return database.query(TABLE_NAME_FOOD, projection, selection,
                        selectionArgs, null , null, sortOrder);
            case URI_FOOD_ITEM:
                return database.query(TABLE_NAME_FOOD, projection, "id = ?",
                        new String[]{uri.getPathSegments().get(1)}, null, null, sortOrder);
            default:
                return null;
        }
    }

    // 返回更新的数据条数
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (mDdbHelper == null) {
            return 0;
        }
        SQLiteDatabase database = mDdbHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case URI_FOOD:
                return database.update(TABLE_NAME_FOOD, values, selection, selectionArgs);
            case URI_FOOD_ITEM:
                return database.update(TABLE_NAME_FOOD, values,  "id = ?", new String[]{uri.getPathSegments().get(1)});
            default:
                return 0;
        }
    }


    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case URI_FOOD:
                return "vnd.android.cursor.dir/" + "vnd." + AUTHORITY + "." + TABLE_NAME_FOOD ;
            case URI_FOOD_ITEM:
                return "vnd.android.cursor.item/" + "vnd." + AUTHORITY + "." + TABLE_NAME_FOOD ;
            default:
                return null;
        }
    }

}