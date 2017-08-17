package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by congl on 2017/6/23.
 */
@Entity
public class AdvanceInfo {

    @Id
    private Long id;

    @Unique
    private String name;
    private boolean isChangeable;
    private boolean isDirect;
    private boolean isMoreMethods;
    @Generated(hash = 1185774393)
    public AdvanceInfo(Long id, String name, boolean isChangeable, boolean isDirect,
            boolean isMoreMethods) {
        this.id = id;
        this.name = name;
        this.isChangeable = isChangeable;
        this.isDirect = isDirect;
        this.isMoreMethods = isMoreMethods;
    }
    @Generated(hash = 1922450326)
    public AdvanceInfo() {
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
    public boolean getIsChangeable() {
        return this.isChangeable;
    }
    public void setIsChangeable(boolean isChangeable) {
        this.isChangeable = isChangeable;
    }
    public boolean getIsDirect() {
        return this.isDirect;
    }
    public void setIsDirect(boolean isDirect) {
        this.isDirect = isDirect;
    }
    public boolean getIsMoreMethods() {
        return this.isMoreMethods;
    }
    public void setIsMoreMethods(boolean isMoreMethods) {
        this.isMoreMethods = isMoreMethods;
    }

}
