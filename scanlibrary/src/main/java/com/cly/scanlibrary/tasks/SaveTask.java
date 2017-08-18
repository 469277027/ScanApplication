package com.cly.scanlibrary.tasks;

import android.content.Context;

import com.cly.scanlibrary.Scan;
import com.cly.scanlibrary.business.DbBusiness;
import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.ScanQueue;

/**
 * 将校验完成的扫码信息存入数据库
 * Created by 丛龙宇 on 2017/8/18.
 */

public class SaveTask implements Runnable {

    private ScanQueue saveQueue;
    private DbBusiness dbBusiness;

    public SaveTask(ScanQueue saveQueue) {
        this.saveQueue = saveQueue;
        dbBusiness = DbBusiness.getINSTANCE(Scan.context);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ScanDatas data = saveQueue.take();
                data.updateSubState();
                dbBusiness.saveScanSub(data.getData());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
