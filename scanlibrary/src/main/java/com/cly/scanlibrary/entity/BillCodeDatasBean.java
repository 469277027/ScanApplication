package com.cly.scanlibrary.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据封车码获取信息
 * Created by 丛龙宇 on 2017/8/17.
 */

public class BillCodeDatasBean {


    /**
     * createOperator : 宿同奎
     * transportBeginPlace : 哈尔滨
     * editCompanyName : 哈尔滨
     * editTime : 2017-07-24 10:35:20
     * driverTelephone : 23
     * transportBillType : 装车配载
     * transportBillLine : 哈尔滨-沈阳
     * transportBillCode : fygs201707241014359700785
     * editOperator : 宿同奎
     * receiveCompany : 沈阳
     * isUsed : 0
     * createCompanyName : 哈尔滨
     * carNumber : c2
     * organizationCode : fyrjgs
     * createTime : 2017-07-24 10:13:36
     * iD : 2
     * driverName : 23
     */

    private String createOperator;
    private String transportBeginPlace;
    private String editCompanyName;
    private String editTime;
    private String driverTelephone;
    private String transportBillType;
    private String transportBillLine;
    private String transportBillCode;
    private String editOperator;
    private String receiveCompany;
    private String isUsed;
    private String createCompanyName;
    private String carNumber;
    private String organizationCode;
    private String createTime;
    private int iD;
    private String driverName;

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    public String getTransportBeginPlace() {
        return transportBeginPlace;
    }

    public void setTransportBeginPlace(String transportBeginPlace) {
        this.transportBeginPlace = transportBeginPlace;
    }

    public String getEditCompanyName() {
        return editCompanyName;
    }

    public void setEditCompanyName(String editCompanyName) {
        this.editCompanyName = editCompanyName;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDriverTelephone() {
        return driverTelephone;
    }

    public void setDriverTelephone(String driverTelephone) {
        this.driverTelephone = driverTelephone;
    }

    public String getTransportBillType() {
        return transportBillType;
    }

    public void setTransportBillType(String transportBillType) {
        this.transportBillType = transportBillType;
    }

    public String getTransportBillLine() {
        return transportBillLine;
    }

    public void setTransportBillLine(String transportBillLine) {
        this.transportBillLine = transportBillLine;
    }

    public String getTransportBillCode() {
        return transportBillCode;
    }

    public void setTransportBillCode(String transportBillCode) {
        this.transportBillCode = transportBillCode;
    }

    public String getEditOperator() {
        return editOperator;
    }

    public void setEditOperator(String editOperator) {
        this.editOperator = editOperator;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getCreateCompanyName() {
        return createCompanyName;
    }

    public void setCreateCompanyName(String createCompanyName) {
        this.createCompanyName = createCompanyName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "SettingsBean{" +
                "createOperator='" + createOperator + '\'' +
                ", transportBeginPlace='" + transportBeginPlace + '\'' +
                ", editCompanyName='" + editCompanyName + '\'' +
                ", editTime='" + editTime + '\'' +
                ", driverTelephone='" + driverTelephone + '\'' +
                ", transportBillType='" + transportBillType + '\'' +
                ", transportBillLine='" + transportBillLine + '\'' +
                ", transportBillCode='" + transportBillCode + '\'' +
                ", editOperator='" + editOperator + '\'' +
                ", receiveCompany='" + receiveCompany + '\'' +
                ", isUsed='" + isUsed + '\'' +
                ", createCompanyName='" + createCompanyName + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", iD=" + iD +
                ", driverName='" + driverName + '\'' +
                '}';
    }

}
