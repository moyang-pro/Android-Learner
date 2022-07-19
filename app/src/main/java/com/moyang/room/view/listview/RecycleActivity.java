package com.moyang.room.view.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.moyang.room.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {

    private List<ListBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        for (int i = 8000; i < 20000; i++) {
            if (i % 4 != 0) {
                continue;
            }
            ListBean bean = new ListBean();
            bean.setDesc("墨墨阳...." + i);
            mList.add(bean);
        }

        RecyclerView recyclerView = findViewById(R.id.mo_recycle);
        MyRecycleAdapter myRecycleAdapter = new MyRecycleAdapter(getApplicationContext(), mList);
        recyclerView.setAdapter(myRecycleAdapter);
        myRecycleAdapter.setOnItemCLickListener(new MyRecycleAdapter.OnItemCLickListener() {
            @Override
            public void onItemCLick(int position) {
                Log.i("moyang99", "onItemClick === " + position);
            }
        });

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        recyclerView.setLayoutManager(gridLayoutManager);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }
}