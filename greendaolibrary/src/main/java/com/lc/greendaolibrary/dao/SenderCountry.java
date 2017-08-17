package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by congl on 2017/6/13.
 */
@Entity
public class SenderCountry {
    @Id
    private Long id;

    @Unique
    private String cnName;
    private String defaultValue;
    private String barCodeNumber;

    @Generated(hash = 83694859)
    public SenderCountry(Long id, String cnName, String defaultValue,
            String barCodeNumber) {
        this.id = id;
        this.cnName = cnName;
        this.defaultValue = defaultValue;
        this.barCodeNumber = barCodeNumber;
    }
    @Generated(hash = 524137617)
    public SenderCountry() {
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
    public String getDefaultValue() {
        return this.defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
