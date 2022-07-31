package com.moyang.room.provider;

import static com.moyang.room.provider.RestaurantProvider.AUTHORITY;
import static com.moyang.room.provider.RtDbHelper.FOOD_COL_NAME;
import static com.moyang.room.provider.RtDbHelper.FOOD_COL_PRICE;
import static com.moyang.room.provider.RtDbHelper.TABLE_NAME_FOOD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moyang.room.R;

public class ContentProviderLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_learn);

        Button buttonCall = findViewById(R.id.btn_call);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 检查用户权限
                if (ContextCompat.checkSelfPermission(ContentProviderLearnActivity.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    callPhone(Intent.ACTION_CALL);
                } else {
                    // 2. 请求权限
                    ActivityCompat.requestPermissions(ContentProviderLearnActivity.this,  new String[]{Manifest.permission.CALL_PHONE}, 0);
                }
            }
        });

        Button buttonDial = findViewById(R.id.btn_dial);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(Intent.ACTION_DIAL);
            }
        });

        // 1. 检查用户权限
        if (ContextCompat.checkSelfPermission(ContentProviderLearnActivity.this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            readContact();
        } else {
            // 2. 请求权限
            ActivityCompat.requestPermissions(ContentProviderLearnActivity.this,  new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }


        ContentResolver resolver = getContentResolver();
        Button buttonInsert = findViewById(R.id.btn_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME_FOOD);
                ContentValues values = new ContentValues();
                values.put(FOOD_COL_NAME, "宫保鸡丁");
                values.put(FOOD_COL_PRICE, 40);
                resolver.insert(uri, values);

                ContentValues values2 = new ContentValues();
                values2.put(FOOD_COL_NAME, "糖醋排骨");
                values2.put(FOOD_COL_PRICE, 50);
                resolver.insert(uri, values2);
            }
        });

        Button buttonDelete = findViewById(R.id.btn_delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME_FOOD);
                resolver.delete(uri, "name = ?", new String[]{"宫保鸡丁"});
            }
        });

        Button buttonUpdate = findViewById(R.id.btn_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values2 = new ContentValues();
                values2.put(FOOD_COL_PRICE, 100);
                Uri uri = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME_FOOD);
                resolver.update(uri, values2, "name = ?", new String[]{"糖醋排骨"});
            }
        });

        Button buttonQuery = findViewById(R.id.btn_query);
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME_FOOD);

                Cursor query = resolver.query(uri, null, null, null, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        int indexName = query.getColumnIndex(FOOD_COL_NAME);
                        String name = query.getString(indexName);
                        int indexPrice = query.getColumnIndex(FOOD_COL_PRICE);
                        int price = query.getInt(indexPrice);
                        Log.i("moyang99", "food name === " + name + " price === " + price);
                    }
                    query.close();
                }
            }
        });

    }

    private void readContact() {
        // 如何拿到ContentResolver?
        ContentResolver contentResolver = getContentResolver();
        // uri : 协议名称://  + 权限(包名) + path
//        contentResolver.insert();
//        contentResolver.delete();
//        contentResolver.update();
//        contentResolver.query();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null ,null);

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(numberIndex);
            Log.i("moyang99", "联系人姓名: " + name + " 电话号为 " + number);
        }
        cursor.close();
    }

    private void callPhone(String actionCall) {
        Intent intent = new Intent(actionCall);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 3. 授权回调
        if (requestCode == 0 ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone(Intent.ACTION_CALL);
            } else {
                Toast.makeText(this, "拒绝授权, 禁止打电话!", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContact();
            } else {
                Toast.makeText(this, "拒绝授权, 禁止读取联系人!", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}