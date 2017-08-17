package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by congl on 2017/6/14.
 */
@Entity
public class ReceiveCountry {

    @Id
    private Long id;

    @Unique
    private String cnName;
    private String pId;
    private String barCodeNumber;

    @Generated(hash = 674370768)
    public ReceiveCountry(Long id, String cnName, String pId,
            String barCodeNumber) {
        this.id = id;
        this.cnName = cnName;
        this.pId = pId;
        this.barCodeNumber = barCodeNumber;
    }

    @Generated(hash = 304036209)
    public ReceiveCountry() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getPId() {
        return this.pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return cnName;
    }

    public String getBarCodeNumber() {
        return this.barCodeNumber;
    }

    public void setBarCodeNumber(String barCodeNumber) {
        this.barCodeNumber = barCodeNumber;
    }
}
