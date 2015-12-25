package com.first.zhaidoujiaju.EntityLuo;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Finder;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.lang.annotation.Target;

/**
 * Created by Administrator on 15-10-8.
 */
@Table(name="TypeEntity",execAfterTableCreated = "INSERT INTO TypeEntity(id,name) VALUES(1,'客厅方案'),"+
          "(2,'整体方案');")
public class TypeEntity {
    @Id(column = "id")
    private long id;
    @Column(column = "name")
    private  String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
