package com.cly.scanlibrary.entity;

/**
 * 扫码相关信息
 * Created by 丛龙宇 on 2017/8/16.
 */

public class ScanDatas {

    private String orderNumber;
    private String scanTime;
    private boolean isTerminusSure = true, isRepeat = false;


    public ScanDatas(String orderNumber, String scanTime) {
        this.orderNumber = orderNumber;
        this.scanTime = scanTime;
    }

    public synchronized void updateTerminus(boolean state) {
        isTerminusSure = state;
    }

    public synchronized void updateRepeat(boolean state) {
        isRepeat = state;
    }

    public synchronized boolean isTerminusSure() {
        return isTerminusSure;
    }

    public synchronized boolean isRepeat() {
        return isRepeat;
    }

    @Override
    public String toString() {
        return "ScanDatas{" +
                "orderNumber='" + orderNumber + '\'' +
                ", scanTime='" + scanTime + '\'' +
                ", isTerminusSure=" + isTerminusSure +
                ", isRepeat=" + isRepeat +
                '}';
    }
}
