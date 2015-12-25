package com.first.zhaidoujiaju.EntityLuo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 15-10-5.
 */
public class EntieyShou {
    private  String id;
    private String title;
    private String img_url;
    private String reviews;

    public String getId() {
        return id;
    }
    @JSONField(name="id")
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    @JSONField(name="title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }
    @JSONField(name="img_url")
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getReviews() {
        return reviews;
    }
    @JSONField(name="reviews")
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
