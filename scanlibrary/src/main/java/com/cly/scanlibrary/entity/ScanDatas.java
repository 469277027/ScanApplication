package com.cly.scanlibrary.entity;

import com.lc.greendaolibrary.dao.scan.ScanSub;

/**
 * 扫码相关信息包装类
 * Created by 丛龙宇 on 2017/8/16.
 */

public class ScanDatas {

    private ScanSub scanSub;

    private boolean isCodeError = false,
            isRepeat = false;

    public ScanDatas(ScanSub scanSub) {
        this.scanSub = scanSub;
    }

    /**
     * 判断条码是否正确
     */
    public synchronized void codeError() {
        isCodeError = true;
    }

    /**
     * 判断条码是否重复
     */
    public synchronized void repeat() {
        isRepeat = true;
    }

    /**
     * 更新条码状态
     */
    public synchronized void updateSubState() {
        scanSub.setState(!isCodeError || !isRepeat ? 0 : 2);
    }

    public synchronized ScanSub getData() {
        return scanSub;
    }


}
