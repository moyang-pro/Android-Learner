package com.moyang.room.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("moyang99" , "MyIntentService onHandleIntent " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("moyang99" , "MyIntentService onStartCommand " + Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("moyang99" , "MyIntentService onDestroy " + Thread.currentThread().getName());
        super.onDestroy();
    }
}