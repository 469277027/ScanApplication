package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.ScanQueue;

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
                ScanDatas scanDatas = new ScanDatas(String.valueOf(count++), String.valueOf(System.currentTimeMillis()));
                d("created:" + scanDatas);
                scanQueue.put(scanDatas);
            }
        } catch (InterruptedException e) {
            d("Interrupted: ScanTask");
        }
        d("ScanTask off");
    }
}
