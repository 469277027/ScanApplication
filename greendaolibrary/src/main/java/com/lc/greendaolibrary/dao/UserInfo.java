package com.lc.greendaolibrary.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by congl on 2017/6/20.
 */
@Entity
public class UserInfo {

    @Id
    Long id;

    @Unique
    String token;
    String userName;
    String companyName;
    @Generated(hash = 566433592)
    public UserInfo(Long id, String token, String userName, String companyName) {
        this.id = id;
        this.token = token;
        this.userName = userName;
        this.companyName = companyName;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    

}
