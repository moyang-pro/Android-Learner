package com.moyang.room.database.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.moyang.room.R;

import java.util.ArrayList;
import java.util.List;

public class RoomLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_learn);

        Button insertBtn = findViewById(R.id.btn_room_insert);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadPoolManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        User user1 = new User();
                        user1.firstName = "liu";
                        user1.lastName = "yang";
                        User user2 = new User();
                        user2.firstName = "li";
                        user2.lastName = "yan";
                        AppDatabase.userDao(getApplication()).insertAll(user1, user2);
                    }
                });
            }
        });

        Button queryBtn = findViewById(R.id.btn_room_query);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThreadPoolManager.getInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        List<User> userList = AppDatabase.userDao(getApplication()).getAll();
                        for (User user : userList) {
                            Log.d("moyang99", "user === " + user);
                        }
                    }
                });
            }
        });


    }
}