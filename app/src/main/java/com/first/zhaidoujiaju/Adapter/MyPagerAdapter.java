package com.first.zhaidoujiaju.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.first.zhaidoujiaju.EntityLuo.TypeEntity;
import com.first.zhaidoujiaju.Fragment.Fragmentcontent3;

import java.util.List;

/**
 * Created by Administrator on 15-10-8.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<TypeEntity> list;

    public MyPagerAdapter(FragmentManager fragmentManager, List<TypeEntity> all) {
       super(fragmentManager);
        this.list=all;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragmentMei = new Fragmentcontent3();

        return fragmentMei;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
