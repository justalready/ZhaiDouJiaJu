package com.first.zhaidoujiaju;

import android.app.Application;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Administrator on 15-10-6.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BitmapHelper.init(this);
        DbHelper.init(this);
    }
}
