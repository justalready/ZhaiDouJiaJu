package com.first.zhaidoujiaju;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 15-10-4.
 */
public class EntityLuo {
    private String imgs;

    public String getImgs() {
        return imgs;
    }
    @JSONField(name="imgs")
    public void setImgs(String imgs) {
        this.imgs = imgs;
    }
}
