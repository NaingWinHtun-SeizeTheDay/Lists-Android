package com.seizetheday.lists.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.seizetheday.lists.fragments.AboutFragment;
import com.seizetheday.lists.fragments.ListsFragment;
import com.seizetheday.lists.fragments.RecentListsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Lists";
            case 1:
                return "Recent";
            default:
                return "About";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListsFragment();
            case 1:
                return new RecentListsFragment();
            default:
                return new AboutFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}