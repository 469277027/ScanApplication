package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.ScanQueue;
import com.cly.scanlibrary.utils.VerificationPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * Created by 丛龙宇 on 2017/8/16.
 */

public class ScanManager implements Runnable {


    private ScanQueue scanQueue = new ScanQueue(), finishingQueue = new ScanQueue();
    private ExecutorService executorService;

    public ScanManager(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void start() {

        d(this + "--> start");

//        ExecutorService executorService = Executors.newCachedThreadPool();
        VerificationPool pool = new VerificationPool();

        executorService.execute(new VerificationTerminus(pool));
        executorService.execute(new VerificationRepeat(pool));

        executorService.execute(new Assembler(scanQueue, finishingQueue, pool));
        executorService.execute(new ScanTask(scanQueue));

//        try {
//            TimeUnit.SECONDS.sleep(7);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        executorService.shutdownNow();

    }

    public void put(ScanDatas scanDatas) throws InterruptedException {
        scanQueue.put(scanDatas);
    }


    @Override
    public String toString() {
        return getClass().getName();
    }

    @Override
    public void run() {
        start();
    }
}
