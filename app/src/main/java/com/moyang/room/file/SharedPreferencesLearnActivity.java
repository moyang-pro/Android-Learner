package com.moyang.room.file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moyang.room.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 使用 SharedPreferences 进行数据存储 (轻量级数据)
 *
 * --- 保存
 *    getSharedPreferences(name, mode)
 *    name: 默认是activity名字
 * --- 存储
 *
 * 适用: 轻量级数据存储
 * 存储的数据形式: 键-值 对
 * 存储位置: /data/data/packageName/shared_prefs/xxx.xml
 */
public class SharedPreferencesLearnActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_learn);

        mEditText = findViewById(R.id.editText);

        Button button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });

        Button button_read = findViewById(R.id.button_read);
        button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readText();
            }
        });

    }

    // 读取SP文件中内容到editText中
    private void readText() {
        SharedPreferences sp = getSharedPreferences("test_sp", MODE_PRIVATE);
        String text = sp.getString("name", "moyang");
        mEditText.setText(text);
    }

    // 保存editText中内容到SP文件中
    private void saveText() {
        String text = mEditText.getText().toString();
        SharedPreferences sp = getSharedPreferences("test_sp", MODE_PRIVATE);
        sp.edit().putString("name", text).apply();
    }
}