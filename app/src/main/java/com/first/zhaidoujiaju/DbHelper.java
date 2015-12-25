package com.first.zhaidoujiaju;

import android.content.Context;

import com.first.zhaidoujiaju.EntityLuo.EntieyShou;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by Administrator on 15-10-6.
 */
public class DbHelper {
    private  static DbUtils utils;
    public static  void init(Context context)  {
        utils = DbUtils.create(context, "zhaidou.db");
        utils.configAllowTransaction(true);
        utils.configDebug(true);
        try {
            utils.createTableIfNotExist(EntieyShou.class);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }
    public static DbUtils getUtils(){
        return utils;
    }
}
