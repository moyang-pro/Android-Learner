package com.moyang.room.service;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/7/26 22:42
 */
public class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

    // Params, Progress, Result
    public MyAsyncTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        Log.d("moyang99", "onPreExecute" + Thread.currentThread().getName());
        // 主线程中
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean o) {
        // 任务结束
        Log.d("moyang99", "onPostExecute" + Thread.currentThread().getName());
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d("moyang99", "onProgressUpdate" + Thread.currentThread().getName());
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Boolean o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(String[] strings) {
        int i = 0;
        while (i < 10) {
            i ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("moyang99", "doInBackground" + Thread.currentThread().getName() +  "  progress === " + i);
            // 触发 onProgressUpdate方法回调
            publishProgress();
        }

        return true;
    }

}
