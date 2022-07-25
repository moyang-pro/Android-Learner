package com.moyang.room.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.moyang.room.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件存储:
 *
 * 1) 保存: openFileOutput(name, mode) 字节流
 * name: 文件名
 * mode: 文件打开方式
 *   --- MODE_PRIVATE 覆写
 *   --- MODE_APPEND 追加
 * 2) 读取: openFileInput(name) 字节流,
 * 套接成BufferedReader对象,使用readLine()方法读取
 *
 *  协议: 我们自己约定的
 */
public class FileLearnActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_learn);

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

    // 读取文件中内容到editText中
    private void readText() {
        BufferedReader reader = null;
        StringBuilder text = new StringBuilder();
        try {
            // openFileOutput 方法获取文件流;
            FileInputStream fileInputStream = openFileInput("edit_text.txt");
            reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = reader.readLine())!= null) {
                text.append(line);
            }
            mEditText.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 保存editText中内容到文件中
    private void saveText() {
        String text = mEditText.getText().toString();
        BufferedWriter writer = null;
        try {
            // openFileOutput 方法获取文件流;
            FileOutputStream fileOutputStream = openFileOutput("edit_text.txt", MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}