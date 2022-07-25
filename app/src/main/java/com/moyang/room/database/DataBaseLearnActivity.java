package com.moyang.room.database;

import static com.moyang.room.database.DbOpenHelper.BOOK_COL_AUTHOR;
import static com.moyang.room.database.DbOpenHelper.BOOK_COL_NAME;
import static com.moyang.room.database.DbOpenHelper.BOOK_COL_PAGE;
import static com.moyang.room.database.DbOpenHelper.BOOK_COL_PRICE;
import static com.moyang.room.database.DbOpenHelper.TABLE_BOOK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moyang.room.R;

import java.util.Arrays;

/**
 * 1. SQLiteOpenHelper
 *   onCreate(): 数据库第一次被创建的时候调用
 *   数据库的位置: data/data/pkg/database/xxx.db
 *   onUpgrade(): 数据库版本发生更新时调用
 * 2. 数据库表格的创建:
 *   db.execSQL(CREATE_TABLE_SQL);
 *   注意: 如果在db已经存在的情况下,希望操作db进行添加表格/删除表格/添加列...,不能写在onCreate里,
 *         而是应该写在onUpgrade里,并将版本号增加
 * 3. 数据库的增删改查:
 *    1) 增: insert, 构建 ContentValues
 *    2) 删: delete, where语句
 *    3) 改: update, ContentValues + where语句
 *    4) 查: query, 返回的cursor对象, moveToFirst + moveToNext 方法遍历cursor指向的表格
 *           想取出cursor指定的列的内容的话,使用cursor.getString( cursor.getColumnIndex(列名))
 *
 * 4. 注意事项:
 * 1) 读写数据库/读写文件: IO操作,比较耗时,应放在后台线程去做这些事情
 * 2) 最好使用transaction保证事务的原子性
 */
public class DataBaseLearnActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_learn);

        DbOpenHelper dbOpenHelper = new DbOpenHelper(this, 2);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Button insertButton = findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                count ++;
                values.put(BOOK_COL_NAME, "葵花宝典" + count);
                values.put(BOOK_COL_AUTHOR, "东方不败");
                values.put(BOOK_COL_PAGE, 100);
                values.put(BOOK_COL_PRICE, 10 * count);
                db.insert(TABLE_BOOK, null, values);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(TABLE_BOOK, "price > ?", new String[]{"100"});
            }
        });

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(BOOK_COL_AUTHOR, "我");
                db.update(TABLE_BOOK, values,"price < ?", new String[]{"100"});
            }
        });

        Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor =  db.query(TABLE_BOOK, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        int indexName = cursor.getColumnIndex(BOOK_COL_NAME);
                        String name = cursor.getString(indexName);

                        int indexAuthor = cursor.getColumnIndex(BOOK_COL_AUTHOR);
                        String author = cursor.getString(indexAuthor);

                        int indexPage = cursor.getColumnIndex(BOOK_COL_PAGE);
                        String page = cursor.getString(indexPage);

                        int indexPrice = cursor.getColumnIndex(BOOK_COL_PRICE);
                        String price  = cursor.getString(indexPrice);

                        Log.d("moyang99", "name == " + name + " author == " + author + " page == " + page + " price == " + price);
                    } while (cursor.moveToNext());

                }
                cursor.close();

                // 事务
                db.beginTransaction();
                try {
                    db.setTransactionSuccessful();
                } catch (Exception e) {

                } finally {
                    db.endTransaction();
                }
            }
        });
    }
}