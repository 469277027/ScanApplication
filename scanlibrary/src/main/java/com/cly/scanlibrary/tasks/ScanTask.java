package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.Scan;
import com.cly.scanlibrary.entity.ScanCommon;
import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.ScanQueue;
import com.lc.greendaolibrary.dao.scan.ScanSub;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * 模拟扫描的任务
 * Created by 丛龙宇 on 2017/8/16.
 */

public class ScanTask implements Runnable {

    /**
     * 扫描队列，存放扫描或者手动输入的数据
     */
    private ScanQueue scanQueue;
    private int count;
    private Random random = new Random(47);


    public ScanTask(ScanQueue scanQueue) {
        this.scanQueue = scanQueue;
    }

//    public void scanDatas(ScanDatas scanDatas) throws InterruptedException {
//        scanQueue.put(scanDatas);
//    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                ScanSub scanSub = new ScanSub();
                scanSub.setSubID(System.currentTimeMillis());
                scanSub.setScanType(ScanCommon.scanType);
                scanSub.setMainID(ScanCommon.curMainID);
                scanSub.setScanTime(new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
                scanSub.setBarCode(String.valueOf(random.nextLong()));
                scanSub.setSn(String.valueOf(random.nextInt(1000)));
                scanSub.setState(0);
                scanSub.setManual(true);
                ScanDatas scanDatas = new ScanDatas(scanSub);
//
                d("created:" + scanDatas);
                scanQueue.put(scanDatas);
            }
        } catch (InterruptedException e) {
            d("Interrupted: ScanTask");
        }
        d("ScanTask off");
    }
}
