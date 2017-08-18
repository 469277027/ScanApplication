package com.cly.scanlibrary.entity;

/**
 * Created by 丛龙宇 on 2017/8/18.
 */

public class ScanInitDatas {

    public static final String TYPE_LOADING = "装车";
    public static final String TYPE_UNLOAD = "卸车";

    /**
     * 域名
     */
    private String domainName;
    /**
     * 用户token
     */
    private String token;
    /**
     * 扫描类型
     */
    private String scanType;
    /**
     * 扫描操作员
     */
    private String scanOperator;
    /**
     * 扫描地点
     */
    private String scanCompany;


    public ScanInitDatas(String domainName, String token, String scanType, String scanOperator, String scanCompany) {
        this.domainName = domainName;
        this.token = token;
        this.scanType = scanType;
        this.scanOperator = scanOperator;
        this.scanCompany = scanCompany;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScanType() {
        return scanType;
    }

    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    public String getScanOperator() {
        return scanOperator;
    }

    public void setScanOperator(String scanOperator) {
        this.scanOperator = scanOperator;
    }

    public String getScanCompany() {
        return scanCompany;
    }

    public void setScanCompany(String scanCompany) {
        this.scanCompany = scanCompany;
    }

    @Override
    public String toString() {
        return "ScanInitDatas{" +
                "domainName='" + domainName + '\'' +
                ", token='" + token + '\'' +
                ", scanType='" + scanType + '\'' +
                ", scanOperator='" + scanOperator + '\'' +
                ", scanCompany='" + scanCompany + '\'' +
                '}';
    }
}
