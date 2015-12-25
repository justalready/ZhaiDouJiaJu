package com.first.zhaidoujiaju;

import android.support.v4.widget.SwipeRefreshLayout;

import com.lidroid.xutils.view.annotation.event.EventBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 15-10-5.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerType = SwipeRefreshLayout.OnRefreshListener.class,
           listenerSetter ="setOnRefreshListener",
            methodName="onRefresh")
public @interface OnRefresh {
    int [] value();
    int [] parentId() default  0;

}
