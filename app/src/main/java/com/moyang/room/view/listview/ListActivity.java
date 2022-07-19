package com.moyang.room.view.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moyang.room.R;
import com.moyang.room.view.listview.ListBean;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<ListBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i = 0; i < 100; i++) {
            ListBean bean = new ListBean();
            bean.setDesc("墨阳...." + i);
            mList.add(bean);
        }

        ListView listView = findViewById(R.id.mo_list);
        listView.setAdapter(new MyAdapter(getApplicationContext(), mList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("moyang99", "onItemClick === " + id);
            }
        });
    }
}