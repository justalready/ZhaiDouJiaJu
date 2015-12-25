package com.first.zhaidoujiaju.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;

import com.first.zhaidoujiaju.Adapter.MyPagerAdapter;
import com.first.zhaidoujiaju.EntityLuo.TypeEntity;
import com.first.zhaidoujiaju.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragmentMei extends Fragment {


    private ViewPager pager;
    private TabLayout tab;


    public BlankFragmentMei() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentmei, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        List<TypeEntity> all=null;
        pager = ((ViewPager) view.findViewById(R.id.pagerMei));
        tab = ((TabLayout) view.findViewById(R.id.tab));
        pager.setAdapter(new MyPagerAdapter(getFragmentManager(),all));
        tab.setupWithViewPager(pager);


    }
}
