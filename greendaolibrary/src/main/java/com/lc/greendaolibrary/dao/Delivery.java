package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 送货方式表
 * Created by congl on 2017/6/12.
 */
@Entity(indexes = {@Index(value = "sort ASC",unique = true)})
public class Delivery {
    @Id(autoincrement = true)
    private Long id;

    private String name;
    @Unique
    private String value;
    private String sort;
    private boolean is_default;
    @Generated(hash = 1912480308)
    public Delivery(Long id, String name, String value, String sort,
            boolean is_default) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.sort = sort;
        this.is_default = is_default;
    }
    @Generated(hash = 182838443)
    public Delivery() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getSort() {
        return this.sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public boolean getIs_default() {
        return this.is_default;
    }
    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }


}
