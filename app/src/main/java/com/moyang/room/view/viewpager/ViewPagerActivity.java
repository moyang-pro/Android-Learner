package com.moyang.room.view.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.moyang.room.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        View view1 = getLayoutInflater().inflate(R.layout.view_page_1, null);
        View view2 = getLayoutInflater().inflate(R.layout.view_page_2, null);
        View view3 = getLayoutInflater().inflate(R.layout.view_page_3, null);

        List<View> viewList = new ArrayList<>(3);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
        ViewPager viewPager = findViewById(R.id.view_page);
        viewPager.setAdapter(viewPagerAdapter);
    }
}