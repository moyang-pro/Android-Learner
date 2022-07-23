package com.moyang.room.view.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * @Description:
 * @author: moyang
 * @date: 2022/7/23 13:59
 */
public class MyFragmentPageAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragmentList;

    public MyFragmentPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragmentList) {
        super(fragmentManager, lifecycle);
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (mFragmentList == null) {
            return null;
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
