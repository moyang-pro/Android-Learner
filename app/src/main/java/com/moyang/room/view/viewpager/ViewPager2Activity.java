package com.moyang.room.view.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moyang.room.R;
import com.moyang.room.fragment.BlankFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager2 mViewPager2;

    private LinearLayout llChat, llContact, llFriend, llProfile;

    private ImageView ivChat, ivContact, ivFriends, ivProfile, ivCurrentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        initViewPage();
        initTabView();
    }

    private void initTabView() {
        llChat = findViewById(R.id.tab_we_chat);
        llChat.setOnClickListener(this);
        llContact = findViewById(R.id.tab_contract);
        llContact.setOnClickListener(this);
        llFriend = findViewById(R.id.tab_friends);
        llFriend.setOnClickListener(this);
        llProfile = findViewById(R.id.tab_mine);
        llProfile.setOnClickListener(this);

        ivChat = findViewById(R.id.img_chat);
        ivContact = findViewById(R.id.img_contract);
        ivFriends = findViewById(R.id.img_friends);
        ivProfile = findViewById(R.id.img_mine);

        ivChat.setSelected(true);
        ivCurrentActive = ivChat;
    }

    private void initViewPage() {
        mViewPager2 = findViewById(R.id.view_page2);
//        ViewPage2Adapter viewPage2Adapter = new ViewPage2Adapter();
//        mViewPager2.setAdapter(viewPage2Adapter);
        List<Fragment> fragmentList = new ArrayList<>(4);
        fragmentList.add(PageFragment.newInstance("微信聊天"));
        fragmentList.add(PageFragment.newInstance("通讯录"));
        fragmentList.add(PageFragment.newInstance("发现"));
        fragmentList.add(PageFragment.newInstance("我"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        Lifecycle lifecycle = getLifecycle();
        MyFragmentPageAdapter myFragmentPageAdapter = new MyFragmentPageAdapter
                (fragmentManager, lifecycle, fragmentList);
        mViewPager2.setAdapter(myFragmentPageAdapter);
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        ivCurrentActive.setSelected(false);
        switch (position) {
            case 0:
                ivChat.setSelected(true);
                ivCurrentActive = ivChat;
                break;
            case 1:
                ivContact.setSelected(true);
                ivCurrentActive = ivContact;
                break;
            case 2:
                ivFriends.setSelected(true);
                ivCurrentActive = ivFriends;
                break;
            case 3:
                ivProfile.setSelected(true);
                ivCurrentActive = ivProfile;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("moyang99", "onClick" + v.getId());
        switch (v.getId()) {
            case R.id.tab_we_chat:
                changeTab(0);
                mViewPager2.setCurrentItem(0);
                break;
            case R.id.tab_contract:
                changeTab(1);
                mViewPager2.setCurrentItem(1);
                break;
            case R.id.tab_friends:
                changeTab(2);
                mViewPager2.setCurrentItem(2);
                break;
            case R.id.tab_mine:
                changeTab(3);
                mViewPager2.setCurrentItem(3);
                break;
        }

    }
}