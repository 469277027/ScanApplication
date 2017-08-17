package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by congl on 2017/8/1.
 */
@Entity
public class CreateOrderDefault {

    @Id
    private Long id;

    @Unique
    private String name;
    private String value;
    private boolean ifChange;

    @Generated(hash = 1844101201)
    public CreateOrderDefault(Long id, String name, String value,
                              boolean ifChange) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.ifChange = ifChange;
    }

    @Generated(hash = 1889989219)
    public CreateOrderDefault() {
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

    public boolean getIfChange() {
        return this.ifChange;
    }

    public void setIfChange(boolean ifChange) {
        this.ifChange = ifChange;
    }


}
