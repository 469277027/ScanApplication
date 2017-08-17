package com.cly.scanlibrary.tasks;

import com.cly.scanlibrary.entity.ScanDatas;
import com.cly.scanlibrary.utils.ScanQueue;
import com.cly.scanlibrary.utils.VerificationPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static com.cly.scanlibrary.utils.Log.*;

/**
 * 校验操纵者，操纵校验任务来校验扫描到的信息
 * Created by 丛龙宇 on 2017/8/16.
 */

public class Assembler implements Runnable {

    private ScanQueue scanQueue, finishingQueue;
    private ScanDatas scanDatas;
    private CyclicBarrier barrier = new CyclicBarrier(3);
    private VerificationPool pool;

    public Assembler(ScanQueue scanQueue, ScanQueue finishingQueue, VerificationPool pool) {
        this.scanQueue = scanQueue;
        this.finishingQueue = finishingQueue;
        this.pool = pool;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    public ScanDatas scanDatas() {
        return scanDatas;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                scanDatas = scanQueue.take();

                pool.hire(VerificationTerminus.class, this);
                pool.hire(VerificationRepeat.class, this);

                barrier.await();

                finishingQueue.put(scanDatas);
            }
        } catch (InterruptedException e) {
            d("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
        d("Assembler off");
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
