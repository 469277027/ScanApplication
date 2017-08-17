package com.lc.greendaolibrary.dao.scan;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 丛龙宇 on 2017/8/17.
 */
@Entity
public class ScanSub {

    /**
     * 扫描子项id
     */
    @Id
    private Long subID;

    /**
     * 扫描主项id，对应ScanMain中的mainID
     */
    private Long mainID;
    /**
     * 扫描类型（装车/卸车）
     */
    private String scanType;
    /**
     * 扫描时间
     */
    private String scanTime;
    /**
     * 条码编号
     */
    private String barCode;
    /**
     * 序号
     */
    private String sn;
    /**
     * 状态（0：正常 1：取消 2：错误）
     */
    private Integer state;
    /**
     * 是否为手工输入
     */
    private boolean manual;
    /**
     * 修改时间
     */
    private String editTime;
    /**
     * 是否上传
     */
    private boolean isUpload;
    @Generated(hash = 1431647671)
    public ScanSub(Long subID, Long mainID, String scanType, String scanTime,
            String barCode, String sn, Integer state, boolean manual,
            String editTime, boolean isUpload) {
        this.subID = subID;
        this.mainID = mainID;
        this.scanType = scanType;
        this.scanTime = scanTime;
        this.barCode = barCode;
        this.sn = sn;
        this.state = state;
        this.manual = manual;
        this.editTime = editTime;
        this.isUpload = isUpload;
    }
    @Generated(hash = 555854695)
    public ScanSub() {
    }
    public Long getSubID() {
        return this.subID;
    }
    public void setSubID(Long subID) {
        this.subID = subID;
    }
    public Long getMainID() {
        return this.mainID;
    }
    public void setMainID(Long mainID) {
        this.mainID = mainID;
    }
    public String getScanType() {
        return this.scanType;
    }
    public void setScanType(String scanType) {
        this.scanType = scanType;
    }
    public String getScanTime() {
        return this.scanTime;
    }
    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }
    public String getBarCode() {
        return this.barCode;
    }
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public Integer getState() {
        return this.state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public boolean getManual() {
        return this.manual;
    }
    public void setManual(boolean manual) {
        this.manual = manual;
    }
    public String getEditTime() {
        return this.editTime;
    }
    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
    public boolean getIsUpload() {
        return this.isUpload;
    }
    public void setIsUpload(boolean isUpload) {
        this.isUpload = isUpload;
    }


    @Override
    public String toString() {
        return "ScanSub{" +
                "subID=" + subID +
                ", mainID=" + mainID +
                ", scanType='" + scanType + '\'' +
                ", scanTime='" + scanTime + '\'' +
                ", barCode='" + barCode + '\'' +
                ", sn='" + sn + '\'' +
                ", state=" + state +
                ", manual=" + manual +
                ", editTime='" + editTime + '\'' +
                ", isUpload=" + isUpload +
                '}';
    }
}
