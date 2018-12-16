package com.example.umer.evsandroid;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyViewPagerAdaptor extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    public MyViewPagerAdaptor(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> titles) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void AddFragment(String Title,Fragment fragment)
    {
        titles.add(Title);
        fragments.add(fragment);

    }

    public void AddFragments(ArrayList<Fragment> fragments,ArrayList<String> titles)
    {
        this.fragments=fragments;
        this.titles=titles;

    }
}
